package com.aim.aim_test.repository;

import com.aim.aim_test.entity.Account;
import com.aim.aim_test.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
    List<AccountHistory> findAllByAccount(Account account);
}
