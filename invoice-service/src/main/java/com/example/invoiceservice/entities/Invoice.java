package com.example.invoiceservice.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.commons.dtos.VehiculeDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.commons.dtos.ClientDTO;
import com.commons.dtos.MaintenanceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    private Long id;
    private ClientDTO client;
    private List<VehiculeDTO> vehicules = new ArrayList<>();
    private List<MaintenanceDto> maintenances = new ArrayList<>();
    private Instant timestamp;
    
}