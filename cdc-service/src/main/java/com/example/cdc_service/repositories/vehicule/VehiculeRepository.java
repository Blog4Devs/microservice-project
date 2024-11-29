package com.example.cdc_service.repositories.vehicule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cdc_service.entities.vehicule.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{
    List<Vehicule> findByUpdatedAtAfter(LocalDateTime lastSyncTime);
}