package com.example.cdc_service.repositories.maintenance;
import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdc_service.entities.maintenance.Maintenance;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>{
    List<Maintenance> findByUpdatedAtAfter(Instant lastSyncTime);
}