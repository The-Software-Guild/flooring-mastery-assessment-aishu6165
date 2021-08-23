package com.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "com.game.*")
public class GuessTheNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessTheNumberApplication.class, args);
	}

}
