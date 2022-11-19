package com.fivedaysincloud.cryptoexchange.mapper;

import com.fivedaysincloud.cryptoexchange.dto.OrderDto;
import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.entity.User;
import com.fivedaysincloud.cryptoexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private UserRepository userRepository;

    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setUserId(order.getUserId().getId());
        orderDto.setFilledQuantity(order.getFilledQuantity());
        orderDto.setTrades(order.getTrades());
        orderDto.setType(order.getType());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setCreatedDateTime(order.getCreatedDateTime());
        orderDto.setId(order.getId());
        orderDto.setCurrencyPair(order.getCurrencyPair());
        orderDto.setPrice(order.getPrice());
        return orderDto;
    }

    public Order mapToOrder(OrderDto dto) {
        Order order = new Order();
        User user = userRepository.findById(dto.getUserId()).get();
        order.setOrderStatus(dto.getOrderStatus());
        order.setUserId(user);
        order.setFilledQuantity(dto.getFilledQuantity());
        order.setTrades(dto.getTrades());
        order.setType(dto.getType());
        order.setQuantity(dto.getQuantity());
        order.setCreatedDateTime(dto.getCreatedDateTime());
        order.setId(dto.getId());
        order.setCurrencyPair(dto.getCurrencyPair());
        order.setPrice(dto.getPrice());
        return order;
    }

}
