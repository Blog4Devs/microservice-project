package com.example.cdc_service.mappers;

import com.commons.dtos.VehiculeDTO;
import com.example.cdc_service.entities.vehicule.Vehicule;

public class VehiculeMapper {

    public static VehiculeDTO toDto(Vehicule vehicule) {
        if (vehicule == null) {
            return null;
        }

        VehiculeDTO dto = new VehiculeDTO();
        dto.setIdVehicle(vehicule.getIdVehicle());
        dto.setVin(vehicule.getVin());
        dto.setNumMatriculation(vehicule.getNumMatriculation());
        dto.setMarque(vehicule.getMarque());
        dto.setAnnee(vehicule.getAnnee());
        dto.setColor(vehicule.getColor());
        dto.setKilometrage(vehicule.getKilometrage());
        dto.setCarburant(vehicule.getCarburant());
        dto.setDateAchat(vehicule.getDateAchat());
        dto.setDelivered(vehicule.isDelivered());
        dto.setUpdatedAt(vehicule.getUpdatedAt());
        return dto;
    }

    public static Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) {
            return null;
        }

        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicle(dto.getIdVehicle());
        vehicule.setVin(dto.getVin());
        vehicule.setNumMatriculation(dto.getNumMatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setAnnee(dto.getAnnee());
        vehicule.setColor(dto.getColor());
        vehicule.setKilometrage(dto.getKilometrage());
        vehicule.setCarburant(dto.getCarburant());
        vehicule.setDateAchat(dto.getDateAchat());
        vehicule.setDelivered(dto.isDelivered());
        vehicule.setUpdatedAt(dto.getUpdatedAt());
        return vehicule;
    }
}
