package com.fivedaysincloud.cryptoexchange.dto;

import java.math.BigDecimal;

public interface OrderTotalDTO {
    BigDecimal getPrice();

    BigDecimal getTotal();
}
