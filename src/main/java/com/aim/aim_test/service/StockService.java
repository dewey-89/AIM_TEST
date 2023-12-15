package com.aim.aim_test.service;

import com.aim.aim_test.dto.StockRequestDto;
import com.aim.aim_test.dto.StockResponseDto;
import com.aim.aim_test.entity.Stock;
import com.aim.aim_test.entity.UserRoleEnum;
import com.aim.aim_test.repository.StockRepository;
import com.aim.aim_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    //증권 등록
    public ResponseEntity<?> createStock(UserDetailsImpl userDetails, StockRequestDto stockRequestDto) {
        isAdmin(userDetails);
        Optional<Stock> existStock = stockRepository.findById(stockRequestDto.getStockCode());
        if (existStock.isPresent()) {
            return ResponseEntity.badRequest().body("이미 등록된 주식입니다.");
        }
        Stock stock = new Stock(stockRequestDto);
        stockRepository.save(stock);
        return ResponseEntity.ok().body(new StockResponseDto("주식 등록 성공", stock));
    }

    @Transactional
    public ResponseEntity<?> updateStock(UserDetailsImpl userDetails, Long stockCode, BigDecimal price) {
        isAdmin(userDetails);
        Stock stock = findStockByStockCode(stockCode);
        stock.updateStockPrice(price);
        return ResponseEntity.ok().body(new StockResponseDto("주식 가격 업데이트 성공", stock));
    }

    public ResponseEntity<?> deleteStock(UserDetailsImpl userDetails, Long stockCode) {
        isAdmin(userDetails);
        Stock stock = findStockByStockCode(stockCode);
        stockRepository.delete(stock);
        return ResponseEntity.ok().body(new StockResponseDto("주식 삭제 성공", stock));
    }

    private void isAdmin(UserDetailsImpl userDetails){
        if(!userDetails.getUser().getRole().equals(UserRoleEnum.ADMIN)){
            throw new IllegalArgumentException("관리자만 등록 가능합니다.");
        }
    }

    private Stock findStockByStockCode(Long stockCode){
        return stockRepository.findById(stockCode).orElseThrow(
                () -> new IllegalArgumentException("해당 주식이 존재하지 않습니다.")
        );
    }
}
