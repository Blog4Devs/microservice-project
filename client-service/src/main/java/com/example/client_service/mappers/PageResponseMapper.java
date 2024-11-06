package com.example.client_service.mappers;

import com.example.client_service.dtos.PageResponseDto;
import org.springframework.data.domain.Page;

public class PageResponseMapper {

    public static <T> PageResponseDto<T> toPageResponseDto(Page<T> page) {
        PageResponseDto<T> response = new PageResponseDto<>();
        response.setCurrentPage(page.getNumber());
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setContent(page.getContent());
        
        return response;
    }
}