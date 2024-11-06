package com.example.vehicule_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeDTO_Responce<T> {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private long totalElement;
    private List<T> content;

}
