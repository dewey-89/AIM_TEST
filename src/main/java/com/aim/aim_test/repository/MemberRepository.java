package com.aim.aim_test.repository;

import com.aim.aim_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
