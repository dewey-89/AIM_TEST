package com.aim.aim_test.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class RequestPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;

    private String portfolioType;
    private boolean isResolved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public RequestPortfolio(BigDecimal balance, String portfolioType, User user) {
        this.balance = balance;
        this.portfolioType = portfolioType;
        this.user = user;
        this.isResolved = false;
    }

}
