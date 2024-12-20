package com.commons.dtos;


import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String cin;
}