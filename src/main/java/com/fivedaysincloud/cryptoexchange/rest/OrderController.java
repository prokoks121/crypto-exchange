package com.fivedaysincloud.cryptoexchange.rest;

import com.fivedaysincloud.cryptoexchange.dto.OrderDto;
import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.mapper.OrderMapper;
import com.fivedaysincloud.cryptoexchange.repository.UserRepository;
import com.fivedaysincloud.cryptoexchange.service.OrderService;
import com.fivedaysincloud.cryptoexchange.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.addOrder(orderMapper.mapToOrder(orderDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.mapToDto(order));
    }

    @DeleteMapping("/order/all")
    @ResponseStatus(code = HttpStatus.OK)
    public void clearAllRecords() {
        try {
            orderService.deleteAllOrders();
            userRepository.deleteAll();
            tradeService.deleteAllTrades();

        }catch (Exception e){}
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") int id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(orderMapper.mapToDto(order));
    }

}
