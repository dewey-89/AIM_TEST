package com.aim.aim_test.repository;

import com.aim.aim_test.entity.RequestPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestPortfolioRepository extends JpaRepository<RequestPortfolio, Long> {
    Optional<RequestPortfolio> findByUserId(Long id);
}
