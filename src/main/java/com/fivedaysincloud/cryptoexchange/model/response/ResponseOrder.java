package com.fivedaysincloud.cryptoexchange.model.response;

import com.fivedaysincloud.cryptoexchange.entity.Trade;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.model.OrderStatus;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    private int id;
    private LocalDateTime createdDateTime;
    private CurrencyPair currencyPair;
    private OrderType type;
    private Double filledQuantity;
    private OrderStatus orderStatus;
    private Double price;
    private Double quantity;
    private Collection<Trade> trades;

    public void setPrice(Double value) {
        this.price = (double) Math.round(value * 100) / 100;
    }

    public void setQuantity(Double value) {
        this.quantity = (double) Math.round(value * 100) / 100;
    }

    public void setFilledQuantity(Double value) {
        this.filledQuantity = (double) Math.round(value * 100) / 100;
    }
}
