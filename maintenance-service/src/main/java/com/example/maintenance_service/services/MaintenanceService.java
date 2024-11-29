package com.example.maintenance_service.services;

import com.commons.dtos.PageResponseDto;
import com.example.maintenance_service.entities.Maintenance;
import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.enums.MaintenanceStatus;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MaintenanceService {
    Maintenance scheduleMaintenance(Maintenance maintenance) throws VehiculeNotFoundException;

    Maintenance updateMaintenance(
            Long maintenanceId, String description, com.example.maintenance_service.enums.MaintenanceStatus status, Instant endTime)
            throws MaintenanceNotFoundException;

    Maintenance addOperations(Long maintenanceId, List<Operation> operations)
            throws MaintenanceNotFoundException;

    PageResponseDto<Maintenance> getMaintenanceByVehicleIdAndStatus(
            Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable);

    PageResponseDto<Maintenance> getAllMaintenance(Pageable pageable);
}
