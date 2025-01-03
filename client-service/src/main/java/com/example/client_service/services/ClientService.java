package com.example.client_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import com.commons.dtos.PageResponseDto;
import com.commons.mappers.PageResponseMapper;
import com.example.client_service.entities.Client;
import com.example.client_service.exceptions.ClientNotFoundException;
import com.example.client_service.repositories.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        client.setUpdatedAt(Instant.now());
        return clientRepository.save(client);
    }


    public PageResponseDto<Client> getAllClients(int page, int size, String kw) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clientsPage;
        if (!kw.equals("")) {
            clientsPage = clientRepository.findByNameContainingIgnoreCase(kw, pageable);
        } else {
            clientsPage = clientRepository.findAll(pageable);
        }

        return PageResponseMapper.toPageResponseDto(clientsPage);
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with ID " + id + " not found"));
        
        if (updatedClient.getName() != null) {
            existingClient.setName(updatedClient.getName());
        }
        if (updatedClient.getAddress() != null) {
            existingClient.setAddress(updatedClient.getAddress());
        }
        if (updatedClient.getPhone() != null) {
            existingClient.setPhone(updatedClient.getPhone());
        }
        if (updatedClient.getEmail() != null) {
            existingClient.setEmail(updatedClient.getEmail());
        }
        
        existingClient.setUpdatedAt(Instant.now());
        return clientRepository.save(existingClient);
    }


    public Optional<Client> getClientById(Long id) {
        return Optional.of(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with ID " + id + " not found")));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
