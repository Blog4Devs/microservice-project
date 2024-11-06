package com.example.maintenance_service.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maintenance_service.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    
}

