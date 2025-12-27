package com.focus.focused;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FocusedApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocusedApplication.class, args);
	}

}
