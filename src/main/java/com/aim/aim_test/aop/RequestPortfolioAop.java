package com.aim.aim_test.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RequestPortfolioAop {
    @Pointcut("execution(* com.aim.aim_test.controller.PortfolioController.createRequestPortfolio(..))")
    public void requestPortfolio() {
    }

    @AfterReturning("requestPortfolio()")
    public void afterRequestPortfolio() {
        //메일이나 문자 전송으로 관리자에게 알림 보냃 수 있겠다.
        log.info("포트폴리오를 요청했습니다.");
    }

}
