package com.fivedaysincloud.cryptoexchange.service;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.model.OrderStatus;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import com.fivedaysincloud.cryptoexchange.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradeService tradeService;

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    public Order getOrder(int id) {
        return orderRepository.findOrderById(id);
    }

    public Order addOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        Collection<Order> orders;

        if (newOrder.getType() == OrderType.BUY) {
            orders = orderRepository.findAllByOrderStatusAndCurrencyPairAndTypeOrderByPriceAscCreatedDateTimeAsc(OrderStatus.OPEN, newOrder.getCurrencyPair(), OrderType.SELL);
            orderProcess(orders, newOrder);
        } else {
            orders = orderRepository.findAllByOrderStatusAndCurrencyPairAndTypeOrderByPriceDescCreatedDateTimeAsc(OrderStatus.OPEN, newOrder.getCurrencyPair(), OrderType.BUY);
            orderProcess(orders, newOrder);
        }
        return newOrder;
    }

    private void orderProcess(Collection<Order> orders, Order newOrder) {
        for (Order order : orders) {
            if ((newOrder.getType() == OrderType.SELL && order.getPrice() < newOrder.getPrice()) || (newOrder.getType() == OrderType.BUY && order.getPrice() > newOrder.getPrice())) {
                continue;
            }

            Double availableQuantity = calculateQuantity(order);
            Double requiredQuantity = calculateQuantity(newOrder);

            if (availableQuantity >= requiredQuantity) {
                if (availableQuantity.equals(requiredQuantity)) {
                    order.setOrderStatus(OrderStatus.CLOSE);
                }

                order.setFilledQuantity(order.getFilledQuantity() + requiredQuantity);
                orderRepository.save(order);

                newOrder.setOrderStatus(OrderStatus.CLOSE);
                newOrder.setFilledQuantity(newOrder.getQuantity());
                orderRepository.save(newOrder);

                tradeService.createTrade(order, newOrder, requiredQuantity);
                return;
            } else {
                order.setOrderStatus(OrderStatus.CLOSE);
                order.setFilledQuantity(order.getQuantity());
                orderRepository.save(order);

                newOrder.setFilledQuantity(newOrder.getFilledQuantity() + availableQuantity);
                orderRepository.save(newOrder);

                tradeService.createTrade(order, newOrder, availableQuantity);
            }
        }
    }

    public Double calculateQuantity(Order order) {
        return order.getQuantity() - order.getFilledQuantity();
    }
}
