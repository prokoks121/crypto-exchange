package com.fivedaysincloud.cryptoexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradeDto {

    private int id;
    private int buyOrderId;
    private int sellOrderId;
    private Timestamp createdDateTime = new Timestamp(System.currentTimeMillis());
    private Double price;
    private Double quantity;

    public void setPrice(Double value) {
        this.price = (double) Math.round(value * 100) / 100;
    }

    public void setQuantity(Double value) {
        this.quantity = (double) Math.round(value * 100) / 100;
    }
}
