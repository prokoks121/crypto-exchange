package com.fivedaysincloud.cryptoexchange.repository;

import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.model.OrderStatus;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(int id);

    Set<Order> findAllByOrderStatusAndCurrencyPairAndTypeOrderByPriceDescCreatedDateTimeAsc(OrderStatus orderStatus, CurrencyPair currencyPair, OrderType orderType);

    Set<Order> findAllByOrderStatusAndCurrencyPairAndTypeOrderByPriceAscCreatedDateTimeAsc(OrderStatus orderStatus, CurrencyPair currencyPair, OrderType orderType);

    Set<Order> findAllByOrderStatusAndCurrencyPair(OrderStatus orderStatus, CurrencyPair currencyPair);

}
