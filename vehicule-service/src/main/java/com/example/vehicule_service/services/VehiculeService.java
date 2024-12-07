package com.example.vehicule_service.services;


import com.commons.dtos.PageResponseDto;
import com.commons.dtos.VehiculeDTO;
import com.example.vehicule_service.entities.Vehicule;
import com.example.vehicule_service.exceptions.VehiculeNotFoundException;
import com.example.vehicule_service.mappers.VehiculeMapper;
import com.example.vehicule_service.repositories.VehiculeRepository;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehiculeService {
    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private VehiculeMapper vehiculeMapper;

    public PageResponseDto<VehiculeDTO> getVehicule(int page, int size, Long clientId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicule> vehiculepage ;
        if (clientId!=null) {
            vehiculepage = vehiculeRepository.findByClientId(clientId, pageable);
        } else {
            vehiculepage = vehiculeRepository.findAll(pageable);
        }
        return vehiculeMapper.toPageResponseDto(vehiculepage);
    }

    public void updateVehiculeStatus(Long vehiculeId,boolean isDelivered) throws VehiculeNotFoundException{
        Vehicule vehicule=vehiculeRepository.findById(vehiculeId).orElseThrow(
            () ->new VehiculeNotFoundException("Vehicule with ID " + vehiculeId + " not found")
        );
        vehicule.setDelivered(isDelivered);
        vehiculeRepository.save(vehicule);
    }

    public VehiculeDTO addVehicule(VehiculeDTO vehiculedto){
        Vehicule vehicule=vehiculeMapper.fromVehiculeDTOToVehicule(vehiculedto);
        vehicule.setUpdatedAt(Instant.now());
        Vehicule vehiculesaved=vehiculeRepository.save(vehicule);
        return vehiculeMapper.fromVehiculeToVehiculeDTO(vehiculesaved);
    }

    public VehiculeDTO updateVehicule(Long vehiculeId, VehiculeDTO updatedVehicule) throws VehiculeNotFoundException{
        Vehicule vehicule =  vehiculeRepository.findById(vehiculeId).orElseThrow(() ->new VehiculeNotFoundException("Vehicule with ID " + vehiculeId + " not found")); 
        if (updatedVehicule.getVin() != null) {
            vehicule.setVin(updatedVehicule.getVin());
        }
        if (updatedVehicule.getNumMatriculation() != null) {
            vehicule.setNumMatriculation(updatedVehicule.getNumMatriculation());
        }
        if (updatedVehicule.getMarque() != null) {
            vehicule.setMarque(updatedVehicule.getMarque());
        }
        if (updatedVehicule.getAnnee() != 0) {
            vehicule.setAnnee(updatedVehicule.getAnnee());
        }
        if (updatedVehicule.getColor() != null) {
            vehicule.setColor(updatedVehicule.getColor());
        }
        if (updatedVehicule.getKilometrage() != 0) {
            vehicule.setKilometrage(updatedVehicule.getKilometrage());
        }
        if (updatedVehicule.getCarburant() != null) {
            vehicule.setCarburant(updatedVehicule.getCarburant());
        }
        if (updatedVehicule.getDateAchat() != null) {
            vehicule.setDateAchat(updatedVehicule.getDateAchat());
        }
        vehicule.setDelivered(updatedVehicule.isDelivered());
        vehicule.setUpdatedAt(Instant.now()); 
        vehiculeRepository.save(vehicule);
        return vehiculeMapper.fromVehiculeToVehiculeDTO(vehicule);  
    }  
}
