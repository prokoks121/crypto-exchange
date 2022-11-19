package com.fivedaysincloud.cryptoexchange.repository;

import com.fivedaysincloud.cryptoexchange.dto.OrderTotalDTO;
import com.fivedaysincloud.cryptoexchange.entity.Order;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.model.OrderStatus;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(int id);

    Set<Order> findAllByOrderStatusAndCurrencyPairAndTypeOrderByPriceDescCreatedDateTimeAsc(OrderStatus orderStatus, CurrencyPair currencyPair, OrderType orderType);

    Set<Order> findAllByOrderStatusAndCurrencyPairAndTypeOrderByPriceAscCreatedDateTimeAsc(OrderStatus orderStatus, CurrencyPair currencyPair, OrderType orderType);

    @Query(value = "SELECT o.\"price\" as price,SUM(cast(o.\"quantity\"  - o.\"filled_quantity\" as decimal(12,2))) as total FROM public.\"order\" as o WHERE o.\"order_status\" = 0 and o.\"type\" = 0 and o.\"currency_pair\" = ? GROUP BY o.\"price\" order by o.\"price\" DESC", nativeQuery = true)
    List<OrderTotalDTO> findBuyOrdersTotal(int currencyPair);

    @Query(value = "SELECT o.\"price\" as price,SUM(cast(o.\"quantity\"  - o.\"filled_quantity\" as decimal(12,2))) as total FROM public.\"order\" as o WHERE o.\"order_status\" = 0 and o.\"type\" = 1 and o.\"currency_pair\" = ? GROUP BY o.\"price\" order by o.\"price\"", nativeQuery = true)
    List<OrderTotalDTO> findSellOrdersTotal(int currencyPair);
}
