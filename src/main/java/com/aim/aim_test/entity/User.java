package com.aim.aim_test.entity;

import com.aim.aim_test.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private BigDecimal balance;

    public User(SignupRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.balance = BigDecimal.ZERO;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal withDraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
        return this.balance;
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }
}
