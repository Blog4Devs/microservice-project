package com.example.vehicule_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "VEHICULE API",
        version = "1.0.0",
        description = "My API v1.0"
    ),
    servers = @Server(url = "/VEHICULE-SERVICE", description = "VEHICULE Server URL")
)
@EnableFeignClients
public class VehiculeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiculeServiceApplication.class, args);
	}

}
