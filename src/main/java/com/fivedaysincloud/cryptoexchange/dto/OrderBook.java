package com.fivedaysincloud.cryptoexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBook {
    private List<OrderTotalDTO> buyOrders = new ArrayList<>();
    private List<OrderTotalDTO> sellOrders = new ArrayList<>();
}
