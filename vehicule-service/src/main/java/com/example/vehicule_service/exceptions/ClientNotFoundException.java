package com.example.vehicule_service.exceptions;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(Long clientId) {
        super("Client with ID " + clientId + " not found.");
    }
}

