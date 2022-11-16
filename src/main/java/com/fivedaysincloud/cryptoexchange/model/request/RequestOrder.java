package com.fivedaysincloud.cryptoexchange.model.request;

import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private int id;
    private CurrencyPair currencyPair;
    private OrderType type;
    private Double price;
    private Double quantity;

    public void setPrice(Double value) {
        this.price = (double) Math.round(value * 100) / 100;
    }

    public void setQuantity(Double value) {
        this.quantity = (double) Math.round(value * 100) / 100;
    }

}
