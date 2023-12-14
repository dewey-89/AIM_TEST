package com.aim.aim_test.service;

import com.aim.aim_test.dto.AccountRequestDto;
import com.aim.aim_test.dto.AccountResponseDto;
import com.aim.aim_test.entity.Account;
import com.aim.aim_test.entity.AccountHistory;
import com.aim.aim_test.entity.AccountMessageType;
import com.aim.aim_test.entity.User;
import com.aim.aim_test.repository.AccountHistoryRepository;
import com.aim.aim_test.repository.AccountRepository;
import com.aim.aim_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;

    // 계좌 생성
    public ResponseEntity<?> createAccount(User user) {
        if(accountRepository.findByUserId(user.getId()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 계좌가 존재합니다.");
        }
        Account account = new Account(user);
        accountRepository.save(account);
        return ResponseEntity.ok().body(new AccountResponseDto("계좌 생성 성공", account.getBalance()));
    }

    // 계좌 조회
    public ResponseEntity<?> getAccount(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Account account = getAccountByUserId(userId);
        return ResponseEntity.ok().body(new AccountResponseDto("계좌 조회 성공", account.getBalance()));
    }

    // 계좌 입금
    @Transactional
    public ResponseEntity<?> deposit(UserDetailsImpl userDetails, AccountRequestDto requestDto) {
        Long userId = userDetails.getUser().getId();
        Account account = getAccountByUserId(userId);
        log.info("account: {}", account);
        account.deposit(requestDto.getAmount());
        accountHistoryRepository.save(new AccountHistory(AccountMessageType.DEPOSIT,requestDto.getAmount(), account.getBalance(), account));
        return ResponseEntity.ok().body(new AccountResponseDto("입금 성공", account.getBalance()));
    }

    // 계좌 출금
    @Transactional
    public ResponseEntity<?> withdraw(UserDetailsImpl userDetails, AccountRequestDto requestDto) {
        Long userId = userDetails.getUser().getId();
        Account account = getAccountByUserId(userId);
        if(account.getBalance().compareTo(requestDto.getAmount()) < 0) {
            return ResponseEntity.badRequest().body("잔액이 부족합니다.");
        }
        account.withdraw(requestDto.getAmount());
        accountHistoryRepository.save(new AccountHistory(AccountMessageType.WITHDRAW,requestDto.getAmount(), account.getBalance(), account));
        return ResponseEntity.ok().body(new AccountResponseDto("출금 성공", account.getBalance()));
    }

    // 사용자의 계좌 존재하는지 확인하는 메서드
    private Account getAccountByUserId(Long userId) {
        return accountRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("계좌가 존재하지 않습니다.")
        );
    }

}
