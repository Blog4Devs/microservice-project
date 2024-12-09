package com.example.cdc_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CdcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcServiceApplication.class, args);
	}

}
