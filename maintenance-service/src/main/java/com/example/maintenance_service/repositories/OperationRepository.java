package com.example.maintenance_service.repositories;

import com.example.maintenance_service.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {}
