package com.example.maintenance_service.services;

import com.commons.dtos.MaintenanceDto;
import com.commons.dtos.PageResponseDto;
import com.commons.dtos.MaintenanceDto.OperationDto;
import com.commons.mappers.PageResponseMapper;
import com.example.maintenance_service.entities.Maintenance;
import com.commons.enums.MaintenanceStatus;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;
import com.example.maintenance_service.mappers.MaintenanceMapper;
import com.example.maintenance_service.mappers.MaintenanceMapper.OperationMapper;
import com.example.maintenance_service.proxy.VehicleFeignClient;
import com.example.maintenance_service.repositories.MaintenanceRepository;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
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
    public MaintenanceDto scheduleMaintenance(MaintenanceDto maintenance) throws VehiculeNotFoundException {
        Boolean vehiculeExist = vehicleFeignClient.getVehicleById(maintenance.getVehicleId());

        if (!vehiculeExist) {
            throw new VehiculeNotFoundException(maintenance.getVehicleId());
        }

        if (maintenance.getStatus() == null) {
            maintenance.setStatus(MaintenanceStatus.PROCESSING);
            // TODO: send notification
        }
        maintenance.setUpdatedAt(Instant.now());
        maintenanceRepository.save(MaintenanceMapper.toEntity(maintenance));
        return maintenance;
    }

    @Override
    public MaintenanceDto updateMaintenance(
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
                // TODO: send notification
            }
            maintenance.setStatus(status);
        }
        maintenance.setUpdatedAt(Instant.now());
        maintenanceRepository.save(maintenance);
        return MaintenanceMapper.toDto(maintenance);
    }

    @Override
    public MaintenanceDto addOperations(Long maintenanceId, List<OperationDto> operations)
            throws MaintenanceNotFoundException {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance == null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }

        maintenance.getOperations().addAll(operations
                    .stream()
                    .map(OperationMapper::toEntity)
                    .collect(Collectors.toList()));
        maintenance.setUpdatedAt(Instant.now());
        maintenanceRepository.save(maintenance);
        return MaintenanceMapper.toDto(maintenance);
    }

    @Override
    public PageResponseDto<MaintenanceDto> getMaintenanceByVehicleIdAndStatus(
            Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable) {
        Page<Maintenance> maintenances = null;
        if (maintenanceStatus == null) {
            maintenances = maintenanceRepository.findByVehicleId(vehicleId, pageable);
        }
        maintenances = maintenanceRepository.findByVehicleIdAndStatus(vehicleId, maintenanceStatus, pageable);
        return PageResponseMapper.toPageResponseDto(
            maintenances.map(((maintenance) -> MaintenanceMapper.toDto(maintenance)))
        );
    }

    @Override
    public PageResponseDto<MaintenanceDto> getAllMaintenance(Pageable pageable) {
        return PageResponseMapper.toPageResponseDto(
            maintenanceRepository.findAll(pageable).map(maintenance -> MaintenanceMapper.toDto(maintenance))
        );
    }
}
