package com.mercadolibre.mlgalaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class to run the system with spring-boot.
 */
@SpringBootApplication
@EnableScheduling
public class MlgalaxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlgalaxyApplication.class, args);
	}
}
