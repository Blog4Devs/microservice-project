package com.example.maintenance_service.controllers;

import com.commons.dtos.MaintenanceDto;
import com.commons.dtos.PageResponseDto;
import com.commons.dtos.MaintenanceDto.OperationDto;
import com.commons.enums.MaintenanceStatus;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;
import com.example.maintenance_service.services.MaintenanceService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<MaintenanceDto> scheduleMaintenance(@RequestBody MaintenanceDto maintenance) {
        try {
            MaintenanceDto scheduledMaintenance = maintenanceService.scheduleMaintenance(maintenance);
            return ResponseEntity.status(HttpStatus.CREATED).body(scheduledMaintenance);
        } catch (VehiculeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceDto> updateMaintenance(
            @PathVariable Long maintenanceId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MaintenanceStatus status,
            @RequestParam(required = false) Instant endTime) {
        try {
            MaintenanceDto updatedMaintenance = maintenanceService.updateMaintenance(maintenanceId, description, status, endTime);
            return ResponseEntity.ok(updatedMaintenance);
        } catch (MaintenanceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{maintenanceId}/operations")
    public ResponseEntity<MaintenanceDto> addOperations(
            @PathVariable Long maintenanceId, @RequestBody List<OperationDto> operations) {
        try {
            MaintenanceDto updatedMaintenance = maintenanceService.addOperations(maintenanceId, operations);
            return ResponseEntity.ok(updatedMaintenance);
        } catch (MaintenanceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/vehicles/{vehicleId}")
    public ResponseEntity<PageResponseDto<MaintenanceDto>> getMaintenanceByVehicleIdAndStatus(
            @PathVariable Long vehicleId,
            @RequestParam(required = false) MaintenanceStatus status,
            Pageable pageable) {
        PageResponseDto<MaintenanceDto> maintenances = maintenanceService.getMaintenanceByVehicleIdAndStatus(vehicleId, status, pageable);
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping
    public ResponseEntity<PageResponseDto<MaintenanceDto>> getAllMaintenance(Pageable pageable) {
        PageResponseDto<MaintenanceDto> maintenances = maintenanceService.getAllMaintenance(pageable);
        return ResponseEntity.ok(maintenances);
    }
}
