package com.aim.aim_test.controller;

import com.aim.aim_test.dto.RequestPortfolioDto;
import com.aim.aim_test.security.UserDetailsImpl;
import com.aim.aim_test.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/portfolios")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping("/request")
    public ResponseEntity<?> createRequestPortfolio(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RequestPortfolioDto requestPortfolioDto) {
        return portfolioService.createRequestPortfolio(userDetails, requestPortfolioDto);
    }

}
