package com.example.cdc_service.repositories.maintenance;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdc_service.entities.maintenance.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>{
    List<Maintenance> findByUpdatedAtAfter(LocalDateTime lastSyncTime);
}