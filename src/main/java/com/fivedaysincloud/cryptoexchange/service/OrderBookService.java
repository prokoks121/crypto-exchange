package com.fivedaysincloud.cryptoexchange.service;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.mapper.OrderMapper;
import com.fivedaysincloud.cryptoexchange.model.*;
import com.fivedaysincloud.cryptoexchange.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class OrderBookService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderBook createOrderBook(CurrencyPair currencyPair) {
        Collection<Order> orders = orderRepository.findAllByOrderStatusAndCurrencyPair(OrderStatus.OPEN, currencyPair);
        OrderBook orderBook = new OrderBook();
        boolean isAdded = false;
        for (Order order : orders) {

            ResponseOrderType mappedResponseOrder = OrderMapper.mapToResponseOrderType(order);
            if (order.getType() == OrderType.SELL) {
                ArrayList<ResponseOrderType> responseOrder = orderBook.getSellOrders();
                isAdded = false;
                for (int i = 0; i < responseOrder.size(); i++) {
                    if (Objects.equals(order.getPrice(), responseOrder.get(i).getPrice())) {
                        responseOrder.get(i).setQuantity(responseOrder.get(i).getQuantity() + mappedResponseOrder.getQuantity());
                        isAdded = true;
                        break;
                    } else if (order.getPrice() < responseOrder.get(i).getPrice()) {
                        responseOrder.add(i, mappedResponseOrder);
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded) {
                    responseOrder.add(mappedResponseOrder);
                }
            } else {
                ArrayList<ResponseOrderType> responseOrder = orderBook.getBuyOrders();
                isAdded = false;
                for (int i = 0; i < responseOrder.size(); i++) {
                    if (Objects.equals(order.getPrice(), responseOrder.get(i).getPrice())) {
                        responseOrder.get(i).setPrice(responseOrder.get(i).getQuantity() + mappedResponseOrder.getQuantity());
                        isAdded = true;
                        break;
                    } else if (order.getPrice() > responseOrder.get(i).getPrice()) {
                        responseOrder.add(i, mappedResponseOrder);
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded) {
                    responseOrder.add(mappedResponseOrder);
                }
            }
        }
        return orderBook;
    }

}
