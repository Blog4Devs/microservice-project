package com.example.maintenance_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicule-service", url = "http://vehicule-service:8081")
public interface VehicleFeignClient {
  @GetMapping("/api/vehicules/{id}")
  Boolean getVehicleById(@PathVariable Long id);

  @PatchMapping("/api/vehicules/{id}")
  Boolean updateVehicleStatus(@PathVariable Long id, String status);

}