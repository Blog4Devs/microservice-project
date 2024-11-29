package com.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import com.commons.enums.Carburant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeDTO {
    private Long idVehicle;
    private String vin;
    private String numMatriculation;
    private String marque;
    private int annee;
    private String color;
    private int kilometrage;
    private Carburant carburant;
    private Instant dateAchat;
    private boolean isDelivered;
    private Instant updatedAt;
}
