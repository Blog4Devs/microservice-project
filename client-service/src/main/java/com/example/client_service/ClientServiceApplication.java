package com.example.client_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "CLIENT API",
        version = "1.0.0",
        description = "My API v1.0"
    ),
    servers = @Server(url = "/CLIENT-SERVICE", description = "CLIENT DEFAULT Server URL")
)
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

}
