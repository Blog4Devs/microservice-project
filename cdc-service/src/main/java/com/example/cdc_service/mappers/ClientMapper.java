package com.example.cdc_service.mappers;

import com.commons.dtos.ClientDTO;
import com.example.cdc_service.entities.client.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setCin(client.getCin());
        
        return clientDTO;
    }

    public static Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setEmail(clientDTO.getEmail());
        client.setCin(clientDTO.getCin());
        
        return client;
    }
}