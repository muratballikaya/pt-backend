package com.softtech.personalitytest.ptbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PtBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtBackendApplication.class, args);
	}

}
