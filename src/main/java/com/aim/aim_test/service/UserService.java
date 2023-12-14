package com.aim.aim_test.service;

import com.aim.aim_test.dto.SignupRequestDto;
import com.aim.aim_test.entity.User;
import com.aim.aim_test.jwt.JwtUtil;
import com.aim.aim_test.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public ResponseEntity<?> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> existUser = userRepository.findByUsername(username);
        if (existUser.isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디 입니다.");
        }
        User user = new User(username, password);
        userRepository.save(user);
        return ResponseEntity.ok().body("회원가입 성공");
    }

    public ResponseEntity<?> logout(HttpServletResponse res) {
        jwtUtil.removeCookie(res);
        return ResponseEntity.ok().body("로그아웃 성공");
    }
}
