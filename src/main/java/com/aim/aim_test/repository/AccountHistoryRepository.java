package com.aim.aim_test.repository;

import com.aim.aim_test.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
