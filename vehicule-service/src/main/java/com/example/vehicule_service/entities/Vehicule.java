package com.example.vehicule_service.entities;

import com.example.vehicule_service.enums.Carburant;
import com.example.vehicule_service.enums.VehiculeState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;
    private String vin;
    private String numMatriculation;
    private String marque;
    private int annee;
    private String color;
    private int kilometrage;
    @Enumerated(EnumType.STRING)
    private Carburant carburant;
    private Date dateAchat;
    private Long idProprietaire;
    private boolean isDelivered;

    @Column(nullable = false)
    private Instant updatedAt;
}
