package com.example.maintenance_service.exceptions;

public class VehiculeNotFoundException extends Exception{
public VehiculeNotFoundException(Long vehicleId) {
        super("Vehicle with ID " + vehicleId + " not found.");
    }

}