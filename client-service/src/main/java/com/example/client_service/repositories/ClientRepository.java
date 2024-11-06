package com.example.client_service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.client_service.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
