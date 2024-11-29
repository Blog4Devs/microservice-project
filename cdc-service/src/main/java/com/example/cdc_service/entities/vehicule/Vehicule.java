package com.example.cdc_service.entities.vehicule;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import com.example.cdc_service.enums.Carburant;

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
    private Instant updatedAt ;
}