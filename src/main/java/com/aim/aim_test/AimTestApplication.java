package com.aim.aim_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AimTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AimTestApplication.class, args);
	}

}
