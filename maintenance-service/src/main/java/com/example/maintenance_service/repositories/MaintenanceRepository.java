package com.example.maintenance_service.repositories;

import com.example.maintenance_service.entities.Maintenance;
import com.example.maintenance_service.entities.MaintenanceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
  Page<Maintenance> findByVehicleId(Long vehicleId, Pageable pageable);

  Page<Maintenance> findByVehicleIdAndStatus(
      Long vehicleId, MaintenanceStatus status, Pageable pageable);
}
