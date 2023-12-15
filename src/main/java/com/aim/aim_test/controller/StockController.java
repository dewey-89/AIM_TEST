package com.aim.aim_test.controller;

import com.aim.aim_test.dto.StockRequestDto;
import com.aim.aim_test.security.UserDetailsImpl;
import com.aim.aim_test.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<?> createStock(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody StockRequestDto stockRequestDto) {
        return stockService.createStock(userDetails, stockRequestDto);
    }

    @PatchMapping
    public ResponseEntity<?> updateStock(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Long stockCode,
            @RequestParam BigDecimal price) {
        return stockService.updateStock(userDetails, stockCode, price);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteStock(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Long stockCode) {
        return stockService.deleteStock(userDetails, stockCode);
    }
}
