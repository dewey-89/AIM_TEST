package com.aim.aim_test.service;

import com.aim.aim_test.dto.RequestPortfolioDto;
import com.aim.aim_test.entity.Account;
import com.aim.aim_test.entity.RequestPortfolio;
import com.aim.aim_test.repository.AccountRepository;
import com.aim.aim_test.repository.RequestPortfolioRepository;
import com.aim.aim_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final RequestPortfolioRepository requestPortfolioRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<?> createRequestPortfolio(UserDetailsImpl userDetails, RequestPortfolioDto requestPortfolioDto) {
        Optional<RequestPortfolio> existRequest = requestPortfolioRepository.findByUserId(userDetails.getUser().getId());
        if (existRequest.isPresent()) {
            throw new IllegalArgumentException("이미 요청한 포트폴리오가 존재합니다.");
        }

        Optional<Account> account = accountRepository.findByUserId(userDetails.getUser().getId());
        if (account.isEmpty()) {
            throw new IllegalArgumentException("계좌가 존재하지 않습니다.");
        }

        BigDecimal amount = setRequestPortfolioAmount(account.get().getBalance(), requestPortfolioDto);
        RequestPortfolio requestPortfolio = new RequestPortfolio(amount, requestPortfolioDto.getPortfolioType(), userDetails.getUser());
        requestPortfolioRepository.save(requestPortfolio);

        return ResponseEntity.ok().body("포트폴리오 요청 성공");
    }

    private BigDecimal setRequestPortfolioAmount(BigDecimal balance, RequestPortfolioDto requestPortfolioDto) {
        switch (requestPortfolioDto.getPortfolioType()) {
            case "highRisk" -> {
                return balance.multiply(new BigDecimal("1.0"));
            }
            case "lowRisk" -> {
                return balance.multiply(new BigDecimal("0.5"));
            }
            default -> {
                if(balance.compareTo(requestPortfolioDto.getAmount()) < 0) {
                    throw new IllegalArgumentException("잔액이 부족합니다.");
                }
                return requestPortfolioDto.getAmount();
            }
        }

    }
}
