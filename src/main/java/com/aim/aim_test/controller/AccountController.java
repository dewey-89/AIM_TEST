package com.aim.aim_test.controller;

import com.aim.aim_test.dto.AccountHistoryDto;
import com.aim.aim_test.dto.AccountRequestDto;
import com.aim.aim_test.security.UserDetailsImpl;
import com.aim.aim_test.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping// 계좌 생성
    public ResponseEntity<?> createAccount(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.createAccount(userDetails.getUser());
    }

    @GetMapping// 계좌 조회
    public ResponseEntity<?> getAccount(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.getAccount(userDetails);
    }

    @PatchMapping("/deposit") // 계좌 입금
    public ResponseEntity<?> deposit(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody AccountRequestDto requestDto) {
        return accountService.deposit(userDetails, requestDto);
    }

    @PatchMapping("/withdraw") // 계좌 출금
    public ResponseEntity<?> withdraw(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody AccountRequestDto requestDto) {
        return accountService.withdraw(userDetails, requestDto);
    }

    @GetMapping("/history") // 계좌 입출금 내역 조회
    public List<AccountHistoryDto> getAccountHistory(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return accountService.getAccountHistory(userDetails);
    }


}
