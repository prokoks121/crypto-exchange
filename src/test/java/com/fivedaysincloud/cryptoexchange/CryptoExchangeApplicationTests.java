package com.fivedaysincloud.cryptoexchange;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.entity.Trade;
import com.fivedaysincloud.cryptoexchange.model.*;
import com.fivedaysincloud.cryptoexchange.model.response.ResponseOrder;
import com.fivedaysincloud.cryptoexchange.rest.OrderController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CryptoExchangeApplicationTests {
    ResponseOrder response1;
    ResponseOrder response2;
    ResponseOrder response3;
    ResponseOrder response4;
    Trade trad1;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private OrderController orderController;
    private OrderBook expected = new OrderBook();

    @BeforeEach
    void init() {
        expected = new OrderBook();
        expected.getSellOrders().add(new ResponseOrderType(100.00, 9.00));
        expected.getSellOrders().add(new ResponseOrderType(50.00, 10.00));
        expected.getBuyOrders().add(new ResponseOrderType(250.00, 7.00));
        expected.getBuyOrders().add(new ResponseOrderType(800.00, 6.00));

        response1 = orderController.createOrder(new Order(1, LocalDateTime.now(), CurrencyPair.BTCUSD, OrderType.SELL, 0.0, OrderStatus.OPEN, 9.0, 100.00)).getBody();
        response2 = orderController.createOrder(new Order(2, LocalDateTime.now(), CurrencyPair.BTCUSD, OrderType.SELL, 0.0, OrderStatus.OPEN, 10.0, 50.00)).getBody();
        response3 = orderController.createOrder(new Order(3, LocalDateTime.now(), CurrencyPair.BTCUSD, OrderType.BUY, 0.0, OrderStatus.OPEN, 7.0, 250.00)).getBody();
        response4 = orderController.createOrder(new Order(4, LocalDateTime.now(), CurrencyPair.BTCUSD, OrderType.BUY, 0.0, OrderStatus.OPEN, 6.0, 800.00)).getBody();
        trad1 = new Trade(6, 5, 1, null, 9.0, 50.0);
    }

    @Test
    void test_order_book() {

        OrderBook orderBookResponse = restTemplate.exchange(
                "http://localhost:" + port + "/orderbook/BTCUSD",
                HttpMethod.GET,
                null,
                OrderBook.class
        ).getBody();

        Assertions.assertEquals(orderBookResponse.getBuyOrders().get(0), expected.getBuyOrders().get(0));
        Assertions.assertEquals(orderBookResponse.getBuyOrders().get(1), expected.getBuyOrders().get(1));
        Assertions.assertEquals(orderBookResponse.getSellOrders().get(0), expected.getSellOrders().get(0));
        Assertions.assertEquals(orderBookResponse.getSellOrders().get(1), expected.getSellOrders().get(1));

    }

    @Test
    void test_add_new_element() {
        ResponseOrder responseOrder = restTemplate.exchange(
                "http://localhost:" + port + "/order",
                HttpMethod.POST,
                new HttpEntity<>(new Order(5, LocalDateTime.now(), CurrencyPair.BTCUSD, OrderType.BUY, 0.0, OrderStatus.OPEN, 9.0, 50.00)),
                ResponseOrder.class
        ).getBody();
        trad1.setTimestamp(responseOrder.getTrades().stream().toList().get(0).getTimestamp());
        trad1.setId(responseOrder.getTrades().stream().toList().get(0).getId());
        ArrayList<Trade> trades = new ArrayList<Trade>();
        trades.add(0, trad1);
        Assertions.assertEquals(responseOrder, new ResponseOrder(5, responseOrder.getCreatedDateTime(), CurrencyPair.BTCUSD, OrderType.BUY, 50.0, OrderStatus.CLOSE, 9.0, 50.00, trades));

        OrderBook orderBookResponse = restTemplate.exchange(
                "http://localhost:" + port + "/orderbook",
                HttpMethod.GET,
                null,
                OrderBook.class
        ).getBody();

        Assertions.assertEquals(orderBookResponse.getBuyOrders().get(0), expected.getBuyOrders().get(0));
        Assertions.assertEquals(orderBookResponse.getBuyOrders().get(1), expected.getBuyOrders().get(1));
        Assertions.assertEquals(orderBookResponse.getSellOrders().get(0), new ResponseOrderType(50.0, 9.0));
        Assertions.assertEquals(orderBookResponse.getSellOrders().get(1), expected.getSellOrders().get(1));
    }

}
