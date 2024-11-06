package com.example.maintenance_service.exceptions;

public class MaintenanceNotFoundException extends Exception {
  public MaintenanceNotFoundException(Long maintenanceId) {
    super("Maintenance with ID " + maintenanceId + " not found.");
  }
}
