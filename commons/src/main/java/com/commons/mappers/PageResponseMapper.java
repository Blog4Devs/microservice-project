package com.commons.mappers;

import org.springframework.data.domain.Page;
import com.commons.dtos.PageResponseDto;

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