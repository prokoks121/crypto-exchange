package com.fivedaysincloud.cryptoexchange.repository;

import com.fivedaysincloud.cryptoexchange.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
