package com.aim.aim_test.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class StockRequestDto {
    private Long stockCode;
    private String stockName;
    private BigDecimal stockPrice;
}
