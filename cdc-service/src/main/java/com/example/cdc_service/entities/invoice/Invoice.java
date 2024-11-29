package com.example.cdc_service.entities.invoice;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "invoices")
@Data 
public class Invoice {
    @Id
    private String id;

    private String clientId;
    private String cin;
    private String vehicleId;
    private String maintenanceId;
    private LocalDateTime timestamp;
    
}