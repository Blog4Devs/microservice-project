package com.example.maintenance_service.services;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.maintenance_service.entities.Maintenance;
import com.example.maintenance_service.entities.MaintenanceStatus;
import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;

public interface MaintenanceService {
Maintenance scheduleMaintenance(Maintenance maintenance) throws VehiculeNotFoundException;
    Maintenance updateMaintenance(Long maintenanceId, String description, MaintenanceStatus status, Instant endTime)
            throws MaintenanceNotFoundException;
    Maintenance addOperations(Long maintenanceId, List<Operation> operations) throws MaintenanceNotFoundException;

    Page<Maintenance> getMaintenanceByVehicleIdAndStatus(Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable);
    Page<Maintenance> getAllMaintenance(Pageable pageable);

}