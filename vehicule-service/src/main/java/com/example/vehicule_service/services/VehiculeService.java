package com.example.vehicule_service.services;

import com.example.vehicule_service.dtos.VehiculeDTO_Responce;
import com.example.vehicule_service.entities.Vehicule;
import com.example.vehicule_service.mappers.VehiculeMapper;
import com.example.vehicule_service.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {
    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private VehiculeMapper vehiculeMapper;


    public VehiculeDTO_Responce<Vehicule> get_Vehicule(int page, int size, Long id_proprietaire) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicule> vehiculepage ;
        if (id_proprietaire!=null) {
            vehiculepage = vehiculeRepository.findByIdproprietaire(id_proprietaire, pageable);
        } else {
            vehiculepage = vehiculeRepository.findAll(pageable);
        }

        return vehiculeMapper.toPageResponseDto(vehiculepage);
    }




}
