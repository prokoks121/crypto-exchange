package com.fivedaysincloud.cryptoexchange.mapper;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.model.ResponseOrderType;
import com.fivedaysincloud.cryptoexchange.model.response.ResponseOrder;

public class OrderMapper {
    public static ResponseOrder mapToResponseOrder(Order order) {
        ResponseOrder responseOrder = new ResponseOrder();
        responseOrder.setOrderStatus(order.getOrderStatus());
        responseOrder.setCurrencyPair(order.getCurrencyPair());
        responseOrder.setCreatedDateTime(order.getCreatedDateTime());
        responseOrder.setFilledQuantity(order.getFilledQuantity());
        responseOrder.setId(order.getId());
        responseOrder.setPrice(order.getPrice());
        responseOrder.setQuantity(order.getQuantity());
        responseOrder.setType(order.getType());
        return responseOrder;
    }

    public static ResponseOrderType mapToResponseOrderType(Order order) {
        return new ResponseOrderType(order.getQuantity() - order.getFilledQuantity(), order.getPrice());
    }
}
