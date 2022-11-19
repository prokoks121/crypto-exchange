package com.fivedaysincloud.cryptoexchange.service;

import com.fivedaysincloud.cryptoexchange.dto.OrderBook;
import com.fivedaysincloud.cryptoexchange.dto.OrderTotalDTO;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBookService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderBook createOrderBook(CurrencyPair currencyPair) {
        List<OrderTotalDTO> buyOrder = orderRepository.findBuyOrdersTotal(currencyPair.ordinal());
        List<OrderTotalDTO> sellOrder = orderRepository.findSellOrdersTotal(currencyPair.ordinal());

        OrderBook orderBook = new OrderBook();

        orderBook.setBuyOrders(buyOrder);
        orderBook.setSellOrders(sellOrder);

        return orderBook;
    }

}
