package com.example.cdc_service.mappers;

import com.commons.dtos.MaintenanceDto;
import com.example.cdc_service.entities.maintenance.Maintenance;
import com.example.cdc_service.entities.maintenance.Operation;

import java.util.stream.Collectors;

public class MaintenanceMapper {

    public static MaintenanceDto toDto(Maintenance maintenance) {
        if (maintenance == null) {
            return null;
        }
        return new MaintenanceDto(
            maintenance.getId(),
            maintenance.getStartTime(),
            maintenance.getPredictedEndTime(),
            maintenance.getEndTime(),
            maintenance.getDescription(),
            maintenance.getStatus(),
            maintenance.getOperations() != null ? 
                maintenance.getOperations()
                    .stream()
                    .map(OperationMapper::toDto)
                    .collect(Collectors.toList()) : null,
            maintenance.isPaid(),
            maintenance.getUpdatedAt()
        );
    }

    public static Maintenance toEntity(MaintenanceDto maintenanceDto) {
        if (maintenanceDto == null) {
            return null;
        }
        Maintenance maintenance = new Maintenance();
        maintenance.setId(maintenanceDto.getId());
        maintenance.setStartTime(maintenanceDto.getStartTime());
        maintenance.setPredictedEndTime(maintenanceDto.getPredictedEndTime());
        maintenance.setEndTime(maintenanceDto.getEndTime());
        maintenance.setDescription(maintenanceDto.getDescription());
        maintenance.setStatus(maintenanceDto.getStatus());
        maintenance.setOperations(
            maintenanceDto.getOperations() != null ?
                maintenanceDto.getOperations()
                    .stream()
                    .map(OperationMapper::toEntity)
                    .collect(Collectors.toList()) : null
        );
        maintenance.setPaid(maintenanceDto.isPaid());
        maintenance.setUpdatedAt(maintenanceDto.getUpdatedAt());
        return maintenance;
    }

    public static class OperationMapper {

        public static MaintenanceDto.OperationDto toDto(Operation operation) {
            if (operation == null) {
                return null;
            }
            return new MaintenanceDto.OperationDto(
                operation.getId(),
                operation.getDescription()
            );
        }

        public static Operation toEntity(MaintenanceDto.OperationDto operationDto) {
            if (operationDto == null) {
                return null;
            }
            Operation operation = new Operation();
            operation.setId(operationDto.getId());
            operation.setDescription(operationDto.getDescription());
            operation.setPrice(operationDto.getPrice());
            operation.setUpdateAt(operationDto.getUpdatedAt());

            return operation;
        }
    }
}
