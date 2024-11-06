package com.example.maintenance_service.exceptions;

public class OperationNotFoundException extends Exception{
public OperationNotFoundException(Long operationId) {
        super("Operation with ID " + operationId + " not found.");
    }

}