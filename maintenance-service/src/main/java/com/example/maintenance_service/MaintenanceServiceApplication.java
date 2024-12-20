package com.example.maintenance_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@OpenAPIDefinition(
    info = @Info(
        title = "MAINTENANCE API",
        version = "1.0.0",
        description = "My API v1.0"
    ),
    servers = @Server(url = "/MAINTENANCE-SERVICE", description = "MAINTENANCE Server URL")
)
public class MaintenanceServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaintenanceServiceApplication.class, args);
  }
}
