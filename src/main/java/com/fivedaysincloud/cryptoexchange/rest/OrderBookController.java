package com.fivedaysincloud.cryptoexchange.rest;

import com.fivedaysincloud.cryptoexchange.model.OrderBook;
import com.fivedaysincloud.cryptoexchange.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderBookController {

    @Autowired
    private OrderBookService orderBookService;

    @GetMapping("/orderbook")
    private ResponseEntity<OrderBook> getOrderBook() {
        return ResponseEntity.ok(orderBookService.createOrderBook());
    }

}
