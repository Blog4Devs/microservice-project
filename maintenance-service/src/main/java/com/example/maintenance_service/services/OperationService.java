package com.example.maintenance_service.services;

import com.example.maintenance_service.exceptions.OperationNotFoundException;
import com.commons.dtos.MaintenanceDto.OperationDto;

public interface OperationService {

  OperationDto updateOperation(Long id, OperationDto operationDetails) throws OperationNotFoundException;

  void deleteOperation(Long id);
}
