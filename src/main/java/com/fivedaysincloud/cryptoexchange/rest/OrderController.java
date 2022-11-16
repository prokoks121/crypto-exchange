package com.fivedaysincloud.cryptoexchange.rest;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.mapper.OrderMapper;
import com.fivedaysincloud.cryptoexchange.model.response.ResponseOrder;
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

    @PostMapping("/order")
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.addOrder(order);

        ResponseOrder responseOrder = OrderMapper.mapToResponseOrder(newOrder);
        tradeService.setTrades(responseOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @DeleteMapping("/order/all")
    @ResponseStatus(code = HttpStatus.OK)
    public void clearAllRecords() {
        orderService.deleteAllOrders();
        tradeService.deleteAllTrades();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseOrder> getOrder(@PathVariable("id") int id) {
        Order order = orderService.getOrder(id);
        ResponseOrder responseOrder = OrderMapper.mapToResponseOrder(order);
        tradeService.setTrades(responseOrder);
        return ResponseEntity.ok(responseOrder);
    }

}
