package com.aim.aim_test.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AccountResponseDto {
    private String message;
    private BigDecimal balance;

    public AccountResponseDto(String message, BigDecimal balance) {
        this.message = message;
        this.balance = balance;
    }
}
