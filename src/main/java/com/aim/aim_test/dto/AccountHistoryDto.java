package com.aim.aim_test.dto;

import com.aim.aim_test.entity.AccountHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountHistoryDto {
    private String type;
    private String amount;
    private String balance;
    private String createdAt;

    public AccountHistoryDto(AccountHistory accountHistory) {
        this.type = accountHistory.getType().toString();
        this.amount = accountHistory.getAmount().toString();
        this.balance = accountHistory.getBalance().toString();
        this.createdAt = accountHistory.getCreatedAt().toString();
    }

    public static List<AccountHistoryDto> of(List<AccountHistory> allByAccount) {
        return allByAccount.stream()
                .map(AccountHistoryDto::new)
                .toList();
    }
}
