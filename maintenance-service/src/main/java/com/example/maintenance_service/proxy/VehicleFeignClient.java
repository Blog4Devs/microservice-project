package com.example.maintenance_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicle-service")
public interface VehicleFeignClient {
    @GetMapping("/vehicles/{id}")
    Boolean getVehicleById(@PathVariable Long id);

    @GetMapping("/vehicles/{id}")
    Boolean updateVehiculeStatus(@PathVariable Long id, String status);
}

