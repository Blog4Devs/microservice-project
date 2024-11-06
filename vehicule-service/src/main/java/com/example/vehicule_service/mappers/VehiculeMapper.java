package com.example.vehicule_service.mappers;

import com.example.vehicule_service.dtos.VehiculeDTO_Responce;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class VehiculeMapper {
    public  <T> VehiculeDTO_Responce<T> toPageResponseDto(Page<T> page) {
        VehiculeDTO_Responce<T> response = new VehiculeDTO_Responce<>();
        response.setCurrentPage(page.getNumber());
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setContent(page.getContent());

        return response;
    }
}
