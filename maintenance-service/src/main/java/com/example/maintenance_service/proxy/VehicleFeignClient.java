package com.example.maintenance_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicule-service", url = "http://vehicule-service:8089")
public interface VehicleFeignClient {
  @GetMapping("/vehicles/{id}")
  Boolean getVehicleById(@PathVariable Long id);

  @GetMapping("/vehicles/{id}")
  Boolean updateVehicleStatus(@PathVariable Long id, String status);
}
