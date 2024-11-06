package com.example.vehicule_service.mappers;

import com.example.vehicule_service.dtos.VehiculeDTOResponce;
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
}
