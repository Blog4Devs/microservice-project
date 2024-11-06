package com.example.maintenance_service.services;

import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.exceptions.OperationNotFoundException;

public interface OperationService {

  Operation createOperation(Operation operation);

  Operation updateOperation(Long id, Operation operationDetails) throws OperationNotFoundException;

  void deleteOperation(Long id);
}
