package com.example.vehicule_service.entities;

import com.commons.enums.Carburant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vin;
    private String numMatriculation;
    private String marque;
    private int annee;
    private String color;
    private int kilometrage;
    @Enumerated(EnumType.STRING)
    private Carburant carburant;
    private Instant dateAchat;
    private Long clientId;
    private boolean isDelivered;
    @Column(nullable = false)
    private Instant updatedAt;
}
