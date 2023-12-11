package com.aim.aim_test.controller;

import com.aim.aim_test.dto.SignupRequestDto;
import com.aim.aim_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }
}
