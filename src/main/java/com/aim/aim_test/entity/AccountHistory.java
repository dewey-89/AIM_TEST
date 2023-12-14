package com.aim.aim_test.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class AccountHistory extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountMessageType type;
    private BigDecimal amount;
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public AccountHistory(AccountMessageType type, BigDecimal amount, BigDecimal balance, Account account) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }

}
