package com.aim.aim_test.security;


import com.aim.aim_test.dto.LoginRequestDto;
import com.aim.aim_test.entity.LoginHistory;
import com.aim.aim_test.jwt.JwtUtil;
import com.aim.aim_test.repository.LoginHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final LoginHistoryRepository loginHistoryRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, LoginHistoryRepository loginHistoryRepository) {
        this.jwtUtil = jwtUtil;
        this.loginHistoryRepository = loginHistoryRepository;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult){
        log.info("로그인 성공 및 JWT 생성");
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();

        // JWT 생성 및 Cookie 저장
        String token = jwtUtil.createToken(username);
        jwtUtil.addJwtToCookie(token, response);

        // 로그인 기록 저장
        LoginHistory loginHistory = new LoginHistory(username);
        loginHistoryRepository.save(loginHistory);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        log.info("로그인 실패");
        response.setStatus(401);
    }
}
