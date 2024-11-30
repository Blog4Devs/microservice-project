package com.example.vehicule_service.mappers;

import com.commons.dtos.PageResponseDto;
import com.commons.dtos.VehiculeDTO;
import com.commons.mappers.PageResponseMapper;
import com.example.vehicule_service.entities.Vehicule;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class VehiculeMapper {
    public PageResponseDto<VehiculeDTO> toPageResponseDto(Page<Vehicule> page) {
        return PageResponseMapper.toPageResponseDto(page.map(vehicule -> fromVehiculeToVehiculeDTO(vehicule)));
    }

    public VehiculeDTO fromVehiculeToVehiculeDTO(Vehicule vehicule){
        VehiculeDTO vehiculeDTO= new VehiculeDTO();
        BeanUtils.copyProperties(vehicule,vehiculeDTO);
        return  vehiculeDTO;
    }
    
    public Vehicule fromVehiculeDTOToVehicule(VehiculeDTO vehiculeDTO){
        Vehicule vehicule= new Vehicule();
        BeanUtils.copyProperties(vehiculeDTO,vehicule);
        return  vehicule;
    }
}
