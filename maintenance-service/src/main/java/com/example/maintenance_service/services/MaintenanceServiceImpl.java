package com.example.maintenance_service.services;

import com.commons.dtos.PageResponseDto;
import com.commons.mappers.PageResponseMapper;
import com.example.maintenance_service.entities.Maintenance;
import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.enums.MaintenanceStatus;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;
import com.example.maintenance_service.proxy.VehicleFeignClient;
import com.example.maintenance_service.repositories.MaintenanceRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleFeignClient vehicleFeignClient;

    public MaintenanceServiceImpl(
            MaintenanceRepository maintenanceRepository, VehicleFeignClient vehicleFeignClient) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleFeignClient = vehicleFeignClient;
    }

    @Override
    public Maintenance scheduleMaintenance(Maintenance maintenance) throws VehiculeNotFoundException {
        Boolean vehiculeExist = vehicleFeignClient.getVehicleById(maintenance.getVehicleId());

        if (!vehiculeExist) {
            throw new VehiculeNotFoundException(maintenance.getVehicleId());
        }

        if (maintenance.getStatus() == null) {
            maintenance.setStatus(MaintenanceStatus.PROCESSING);
            // TODO: send notification
        }

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(
            Long maintenanceId, String description, MaintenanceStatus status, Instant endTime)
            throws MaintenanceNotFoundException {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance == null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }
        if (description != null) {
            maintenance.setDescription(description);
        }
        if (status != null) {
            // TODO: make it transactional
            if (status == MaintenanceStatus.FINISHED) {
                vehicleFeignClient.updateVehicleStatus(maintenance.getVehicleId(),
                        MaintenanceStatus.FINISHED.toString());

                maintenance.setEndTime(Instant.now());
                // send notification
            }
            maintenance.setStatus(status);
        }

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance addOperations(Long maintenanceId, List<Operation> operations)
            throws MaintenanceNotFoundException {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance == null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }
        maintenance.getOperations().addAll(operations);

        return maintenanceRepository.save(maintenance);
    }

    @Override
    public PageResponseDto<Maintenance> getMaintenanceByVehicleIdAndStatus(
            Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable) {
        if (maintenanceStatus == null) {
            return PageResponseMapper.toPageResponseDto(maintenanceRepository.findByVehicleId(vehicleId, pageable));
        }

        return PageResponseMapper.toPageResponseDto(
                maintenanceRepository.findByVehicleIdAndStatus(vehicleId, maintenanceStatus, pageable));
    }

    @Override
    public PageResponseDto<Maintenance> getAllMaintenance(Pageable pageable) {
        return PageResponseMapper.toPageResponseDto(maintenanceRepository.findAll(pageable));
    }
}
