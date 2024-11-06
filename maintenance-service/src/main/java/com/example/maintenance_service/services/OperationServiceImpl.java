package com.example.maintenance_service.services;

import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.exceptions.OperationNotFoundException;
import com.example.maintenance_service.repositories.OperationRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

  private OperationRepository operationRepository;

  public OperationServiceImpl(OperationRepository operationRepository) {
    this.operationRepository = operationRepository;
  }

  @Override
  public Operation createOperation(Operation operation) {
    return operationRepository.save(operation);
  }

  @Override
  public Operation updateOperation(Long id, Operation operationDetails)
      throws OperationNotFoundException {
    return operationRepository
        .findById(id)
        .map(
            operation -> {
              operation.setDescription(operationDetails.getDescription());
              operation.setPrice(operationDetails.getPrice());
              return operationRepository.save(operation);
            })
        .orElseThrow(() -> new OperationNotFoundException(id));
  }

  @Override
  public void deleteOperation(Long id) {
    operationRepository.deleteById(id);
  }
}
