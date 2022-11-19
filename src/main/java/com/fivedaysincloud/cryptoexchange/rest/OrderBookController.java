package com.fivedaysincloud.cryptoexchange.rest;

import com.fivedaysincloud.cryptoexchange.dto.OrderBook;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderBookController {

    @Autowired
    private OrderBookService orderBookService;

    @GetMapping("/orderbook/{currencyPair}")
    private ResponseEntity<OrderBook> getOrderBook(@PathVariable("currencyPair") CurrencyPair currencyPair) {
        return ResponseEntity.ok(orderBookService.createOrderBook(currencyPair));
    }

}
