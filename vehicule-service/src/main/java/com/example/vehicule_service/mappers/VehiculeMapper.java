package com.example.vehicule_service.mappers;

import com.example.vehicule_service.dtos.VehiculeDTO;
import com.example.vehicule_service.dtos.VehiculeDTOResponce;
import com.example.vehicule_service.entities.Vehicule;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class VehiculeMapper {
    public  <T> VehiculeDTOResponce<T> toPageResponseDto(Page<T> page) {
        VehiculeDTOResponce<T> response = new VehiculeDTOResponce<>();
        response.setCurrentPage(page.getNumber());
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setContent(page.getContent());

        return response;
    }
    public VehiculeDTO from_vehicule_to_vehiculeDTO(Vehicule vehicule){
        VehiculeDTO vehiculeDTO=new VehiculeDTO();
        BeanUtils.copyProperties(vehicule,vehiculeDTO);
        return  vehiculeDTO;
    }
    public Vehicule from_vehiculeDTO_to_vehicule(VehiculeDTO vehiculeDTO){
        Vehicule vehicule=new Vehicule();
        BeanUtils.copyProperties(vehiculeDTO,vehicule);
        return  vehicule;
    }
}
