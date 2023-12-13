package com.aim.aim_test.controller;

import com.aim.aim_test.dto.LoginRequestDto;
import com.aim.aim_test.dto.SignupRequestDto;
import com.aim.aim_test.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return userService.login(requestDto,res);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse res) {
        return userService.logout(res);
    }
}
