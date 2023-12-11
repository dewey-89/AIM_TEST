package com.aim.aim_test.service;

import com.aim.aim_test.dto.SignupRequestDto;
import com.aim.aim_test.entity.User;
import com.aim.aim_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MemberRepository memberRepository;

    public ResponseEntity<?> signup(SignupRequestDto requestDto) {
        User existUser = memberRepository.findByUsername(requestDto.getUsername());
        if (existUser != null) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디 입니다.");
        }
        User user = new User(requestDto);
        memberRepository.save(user);
        return ResponseEntity.ok().body("회원가입 성공");
    }
}
