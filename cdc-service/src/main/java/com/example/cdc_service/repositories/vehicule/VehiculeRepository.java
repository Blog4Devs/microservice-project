package com.example.cdc_service.repositories.vehicule;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cdc_service.entities.vehicule.Vehicule;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{
    List<Vehicule> findByUpdatedAtAfter(Instant lastSyncTime);
}