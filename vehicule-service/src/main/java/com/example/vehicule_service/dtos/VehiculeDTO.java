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
    private String num_matriculation;
    private String marque;
    private int annee;
    private String color;
    private int kilometrage;

    private Carburant carburant;
    private Date date_achat;
    private Long idproprietaire;

    private VehiculeState vehiculeState;
}
