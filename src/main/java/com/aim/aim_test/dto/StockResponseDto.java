package com.aim.aim_test.dto;

import com.aim.aim_test.entity.Stock;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockResponseDto {
    private String message;
    private Long stockCode;
    private String stockName;
    private BigDecimal stockPrice;

    public StockResponseDto(String message, Stock stock) {
        this.message = message;
        this.stockCode = stock.getStockCode();
        this.stockName = stock.getStockName();
        this.stockPrice = stock.getStockPrice();
    }
}
