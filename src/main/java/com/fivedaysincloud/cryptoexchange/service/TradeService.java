package com.fivedaysincloud.cryptoexchange.service;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.entity.Trade;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import com.fivedaysincloud.cryptoexchange.model.response.ResponseOrder;
import com.fivedaysincloud.cryptoexchange.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public void deleteAllTrades() {
        tradeRepository.deleteAll();
    }

    public void createTrade(Order order, Order newOrder, Double tradeQuantity) {
        Trade trade = new Trade();
        if (order.getType() == OrderType.BUY) {
            trade.setBuyOrderId(order.getId());
            trade.setSellOrderId(newOrder.getId());
        } else {
            trade.setBuyOrderId(newOrder.getId());
            trade.setSellOrderId(order.getId());
        }
        trade.setPrice(order.getPrice());
        trade.setQuantity(tradeQuantity);
        tradeRepository.save(trade);
    }

    public void setTrades(ResponseOrder responseOrder) {
        if (responseOrder.getType() == OrderType.BUY) {
            responseOrder.setTrades(tradeRepository.findAllByBuyOrderId(responseOrder.getId()));
        } else {
            responseOrder.setTrades(tradeRepository.findAllBySellOrderId(responseOrder.getId()));
        }
    }
}
