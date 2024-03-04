package com.masala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MasalaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasalaBackendApplication.class, args);
	}

}
