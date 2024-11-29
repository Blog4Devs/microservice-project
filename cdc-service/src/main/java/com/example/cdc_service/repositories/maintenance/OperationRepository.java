package com.example.cdc_service.repositories.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cdc_service.entities.maintenance.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    
}
