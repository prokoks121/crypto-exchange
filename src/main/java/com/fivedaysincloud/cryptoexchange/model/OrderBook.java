package com.fivedaysincloud.cryptoexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBook {
    private ArrayList<ResponseOrderType> buyOrders = new ArrayList<>();
    private ArrayList<ResponseOrderType> sellOrders = new ArrayList<>();
}
