package com.aim.aim_test.entity;

import com.aim.aim_test.dto.StockRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Stock extends Timestamped{

    @Id
    private Long stockCode;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private BigDecimal stockPrice;


    public Stock(StockRequestDto stockRequestDto) {
        this.stockCode = stockRequestDto.getStockCode();
        this.stockName = stockRequestDto.getStockName();
        this.stockPrice = stockRequestDto.getStockPrice();
    }

    public void updateStockPrice(BigDecimal price) {
        this.stockPrice = price;
    }
}
