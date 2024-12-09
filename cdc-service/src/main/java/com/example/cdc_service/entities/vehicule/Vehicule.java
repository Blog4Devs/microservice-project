package com.example.cdc_service.entities.vehicule;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import com.commons.enums.Carburant;

@Data
@Entity(name = "vehicule")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String vin;

    @Column(name = "num_matriculation")
    private String numMatriculation;
    private String marque;
    private int annee;
    private String color;
    private int kilometrage;
    @Enumerated(EnumType.STRING)
    private Carburant carburant;

    @Column(name = "date_achat")
    private Instant dateAchat;
    @Column(name = "client_id")
    private Long idProprietaire;
    @Column(name = "is_delivered")
    private boolean isDelivered;

    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt ;
}