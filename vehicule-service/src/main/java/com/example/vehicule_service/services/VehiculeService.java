package com.example.vehicule_service.services;

import com.example.vehicule_service.dtos.VehiculeDTO;
import com.example.vehicule_service.dtos.VehiculeDTOResponce;
import com.example.vehicule_service.entities.Vehicule;
import com.example.vehicule_service.enums.VehiculeState;
import com.example.vehicule_service.mappers.VehiculeMapper;
import com.example.vehicule_service.repositories.VehiculeRepository;
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


    public VehiculeDTOResponce<Vehicule> get_Vehicule(int page, int size, Long id_proprietaire) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicule> vehiculepage ;
        if (id_proprietaire!=null) {
            vehiculepage = vehiculeRepository.findByIdproprietaire(id_proprietaire, pageable);
        } else {
            vehiculepage = vehiculeRepository.findAll(pageable);
        }

        return vehiculeMapper.toPageResponseDto(vehiculepage);
    }

public void update_vehicule_status(Long id_vehicule,String state){
        Vehicule vehicule=vehiculeRepository.findByIdvehicule(id_vehicule);
        vehicule.setVehiculeState(VehiculeState.valueOf(state));
        vehiculeRepository.save(vehicule);

}
public Long add_vehicule(VehiculeDTO vehiculedto){
Vehicule vehicule=vehiculeMapper.from_vehiculeDTO_to_vehicule(vehiculedto);
Vehicule vehiculesaved=vehiculeRepository.save(vehicule);
    return vehiculesaved.getIdvehicule();
    }


}
