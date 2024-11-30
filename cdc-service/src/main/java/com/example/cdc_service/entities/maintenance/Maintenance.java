package com.example.cdc_service.entities.maintenance;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

import com.commons.enums.MaintenanceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant startTime = Instant.now();

    private Instant predictedEndTime;

    private Instant endTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    List<Operation> operations;

    private Long vehicleId;
    private Long idProprietaire;
    private boolean isPaid;
        
    @Column(nullable = false)
    private Instant updatedAt ;
}