package com.example.cdc_service.entities.maintenance;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

import com.commons.enums.MaintenanceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "maintenance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private Instant startTime = Instant.now();

    @Column(name = "predicted_end_time")
    private Instant predictedEndTime;

    @Column(name = "end_time")
    private Instant endTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "maintenance")
    List<Operation> operations;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "id_proprietaire")
    private Long idProprietaire;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(nullable = false,name = "updated_at")
    private Instant updatedAt ;
}