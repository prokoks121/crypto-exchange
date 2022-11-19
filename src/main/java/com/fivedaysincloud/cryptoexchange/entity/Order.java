package com.fivedaysincloud.cryptoexchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fivedaysincloud.cryptoexchange.model.CurrencyPair;
import com.fivedaysincloud.cryptoexchange.model.OrderStatus;
import com.fivedaysincloud.cryptoexchange.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private CurrencyPair currencyPair;
    private OrderType type;
    @JsonIgnore
    private Double filledQuantity = 0.0;
    @JsonIgnore
    private OrderStatus orderStatus = OrderStatus.OPEN;
    @Min(value = 0)
    private Double price;
    @Min(value = 0)
    private Double quantity;


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
