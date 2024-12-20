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
        MaintenanceDto maintenanceDto = new MaintenanceDto();
        maintenanceDto.setId(maintenance.getId());
        maintenanceDto.setStartTime(maintenance.getStartTime());
        maintenanceDto.setPredictedEndTime(maintenance.getPredictedEndTime());
        maintenanceDto.setEndTime(maintenance.getEndTime());
        maintenanceDto.setDescription(maintenance.getDescription());
        maintenanceDto.setStatus(maintenance.getStatus());
//        System.out.println("maintenace operations" + maintenance.getOperations());
        maintenanceDto.setOperations(
            maintenance.getOperations() != null ?
                maintenance.getOperations()
                    .stream()
                    .map(OperationMapper::toDto)
                    .collect(Collectors.toList()) : null
        );
        maintenanceDto.setPaid(maintenance.isPaid());
        maintenanceDto.setIdProprietaire(maintenance.getIdProprietaire());
        maintenanceDto.setVehicleId(maintenance.getVehicleId());
        return maintenanceDto;
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
       
        maintenance.setIdProprietaire(maintenanceDto.getIdProprietaire());
        maintenance.setVehicleId(maintenanceDto.getVehicleId());
        return maintenance;
    }

    public static class OperationMapper {

        public static MaintenanceDto.OperationDto toDto(Operation operation) {
            if (operation == null) {
                return null;
            }
            MaintenanceDto.OperationDto operationDto = new MaintenanceDto.OperationDto();
            operationDto.setId(operation.getId());
            operationDto.setDescription(operation.getDescription());
            operationDto.setPrice(operation.getPrice());
          
            return operationDto;
        }

        public static Operation toEntity(MaintenanceDto.OperationDto operationDto) {
            if (operationDto == null) {
                return null;
            }
            Operation operation = new Operation();
            operation.setId(operationDto.getId());
            operation.setDescription(operationDto.getDescription());
            operation.setPrice(operationDto.getPrice());
           
            return operation;
        }
    }
}
