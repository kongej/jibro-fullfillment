package com.jibro.fulfill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JibroFulfillApplication {

	public static void main(String[] args) {
		SpringApplication.run(JibroFulfillApplication.class, args);
	}

}
