package com.example.maintenance_service.services;

import com.commons.dtos.MaintenanceDto.OperationDto;
import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.exceptions.OperationNotFoundException;
import com.example.maintenance_service.mappers.MaintenanceMapper.OperationMapper;
import com.example.maintenance_service.repositories.OperationRepository;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

  private OperationRepository operationRepository;

  public OperationServiceImpl(OperationRepository operationRepository) {
    this.operationRepository = operationRepository;
  }

  @Override
  public OperationDto updateOperation(Long id, OperationDto operationDetails)
      throws OperationNotFoundException {
    Operation op = operationRepository
        .findById(id)
        .map(
            operation -> {
              operation.setDescription(operationDetails.getDescription());
              operation.setPrice(operationDetails.getPrice());
              operation.setUpdatedAt(Instant.now());
              operation.getMaintenance().setUpdatedAt(Instant.now());
              return operationRepository.save(operation);
            })
        .orElseThrow(() -> new OperationNotFoundException(id));
    
    return OperationMapper.toDto(op);
  }

  @Override
  public void deleteOperation(Long id) {
    operationRepository.deleteById(id);
  }
}
