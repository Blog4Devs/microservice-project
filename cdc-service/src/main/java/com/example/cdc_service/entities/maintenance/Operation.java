package com.example.cdc_service.entities.maintenance;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "operation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Long price;

    @Column(nullable = false,name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    private Maintenance maintenance;
}