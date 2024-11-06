package com.example.client_service.dtos;

import java.util.List;

import lombok.Data;

@Data
public class PageResponseDto<T> {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private long totalElement;
    private List<T> content;
}