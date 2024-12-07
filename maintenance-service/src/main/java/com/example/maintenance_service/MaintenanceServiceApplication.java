package com.example.maintenance_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MaintenanceServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaintenanceServiceApplication.class, args);
  }
}
