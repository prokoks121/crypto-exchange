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
import java.util.ArrayList;
import java.util.List;

@Table(name = "`Order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @OneToMany(targetEntity = Trade.class, mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Trade> trades = new ArrayList<Trade>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", nullable = false)
    private User userId;
    private LocalDateTime createdDateTime;
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
