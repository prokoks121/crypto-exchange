package com.fivedaysincloud.cryptoexchange.mapper;

import com.fivedaysincloud.cryptoexchange.dto.TradeDto;
import com.fivedaysincloud.cryptoexchange.entity.Trade;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TradeMapper {
    public  TradeDto mapToDto(Trade trade) {
        TradeDto tradeDto = new TradeDto();
        BeanUtils.copyProperties(trade, tradeDto);
        return tradeDto;
    }

    public  Trade mapToOrder(TradeDto dto) {
        Trade trade = new Trade();
        BeanUtils.copyProperties(dto, trade);
        return trade;
    }
}
