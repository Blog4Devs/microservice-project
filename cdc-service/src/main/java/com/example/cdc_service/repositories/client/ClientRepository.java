package com.example.cdc_service.repositories.client;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdc_service.entities.client.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUpdateAtAfter(Instant lastSyncTime);
}
