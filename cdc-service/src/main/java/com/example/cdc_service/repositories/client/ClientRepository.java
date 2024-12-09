package com.example.cdc_service.repositories.client;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cdc_service.entities.client.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUpdatedAtAfter(Instant lastSyncTime);
}
