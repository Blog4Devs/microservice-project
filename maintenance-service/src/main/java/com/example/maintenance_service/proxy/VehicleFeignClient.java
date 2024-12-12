package com.example.maintenance_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.commons.dtos.VehiculeDTO;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicule-service", url = "http://vehicule-service:8081")
public interface VehicleFeignClient {
  @GetMapping("/api/vehicules/{id}")
  VehiculeDTO getVehicleById(@PathVariable Long id);
}