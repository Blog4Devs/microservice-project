package com.example.maintenance_service.services;

import com.commons.dtos.MaintenanceDto;
import com.commons.dtos.PageResponseDto;
import com.commons.enums.MaintenanceStatus;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.commons.dtos.MaintenanceDto.OperationDto;

public interface MaintenanceService {
    MaintenanceDto scheduleMaintenance(MaintenanceDto maintenance) throws VehiculeNotFoundException;

    MaintenanceDto updateMaintenance(
            Long maintenanceId, String description, MaintenanceStatus status, Instant endTime)
            throws MaintenanceNotFoundException;

     MaintenanceDto addOperations(Long maintenanceId, List<OperationDto> operations)
            throws MaintenanceNotFoundException;

    PageResponseDto<MaintenanceDto> getMaintenanceByVehicleIdAndStatus(
            Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable);

    PageResponseDto<MaintenanceDto> getAllMaintenance(Pageable pageable);
}
