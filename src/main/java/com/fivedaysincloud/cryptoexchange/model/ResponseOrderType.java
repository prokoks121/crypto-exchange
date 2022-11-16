package com.fivedaysincloud.cryptoexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderType {
    private Double quantity;
    private Double price;

    public void setPrice(Double value) {
        this.price = (double) Math.round(value * 100) / 100;
    }

    public void setQuantity(Double value) {
        this.quantity = (double) Math.round(value * 100) / 100;
    }

}
