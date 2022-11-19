package com.fivedaysincloud.cryptoexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fivedaysincloud.cryptoexchange.entity.Trade;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.model.OrderStatus;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private int id;
    private int userId;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private CurrencyPair currencyPair;
    private OrderType type;
    private Double filledQuantity = 0.0;
    private OrderStatus orderStatus = OrderStatus.OPEN;
    @Min(value = 0)
    private Double price;
    @Min(value = 0)
    private Double quantity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Trade> trades = new ArrayList<Trade>();

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
