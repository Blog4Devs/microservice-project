package com.example.vehicule_service.dtos;

import com.example.vehicule_service.enums.Carburant;
import com.example.vehicule_service.enums.VehiculeState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculeDTO {
    private String vin;
    private String numMatriculation;
    private String marque;
    private int annee;
    private String color;
    private int kilometrage;
    private Carburant carburant;
    private Date dateAchat;
    private String cin ;
    private Long idProprietaire;
    private boolean isDelivered;
}
