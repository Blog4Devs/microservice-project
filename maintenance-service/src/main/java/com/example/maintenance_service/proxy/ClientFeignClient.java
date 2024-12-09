package com.example.maintenance_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import com.commons.dtos.ClientDTO;

@FeignClient(name = "client-service", url = "http://client-service:8080")
public interface ClientFeignClient {

  @GetMapping("/api/clients/{id}")
  ClientDTO getClientById(@PathVariable Long id);

}