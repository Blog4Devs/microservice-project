package com.example.vehicule_service.controllers;


import com.commons.dtos.PageResponseDto;
import com.commons.dtos.VehiculeDTO;
import com.example.vehicule_service.exceptions.VehiculeNotFoundException;
import com.example.vehicule_service.services.VehiculeService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin("*")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping("/vehicules")
    public ResponseEntity<PageResponseDto<VehiculeDTO>> get_ehicules(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size,
        @RequestParam(name = "keyword", defaultValue = "") Long clientId
    ) 
    {
        PageResponseDto<VehiculeDTO> vehicules = vehiculeService.getVehicule(page, size,clientId );
        return ResponseEntity.ok(vehicules);
    }
    
    @PatchMapping("/vehicules/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,@RequestParam boolean isDelivered){
        try{
            vehiculeService.updateVehiculeStatus(id, isDelivered);
            return ResponseEntity.ok(Map.of("message","updated"));
        }
        catch (VehiculeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PutMapping("/vehicules/{id}")
    public ResponseEntity<?> updateVehicule(
            @PathVariable Long id,
            @RequestBody VehiculeDTO updatedVehicule) {
        try {
            VehiculeDTO updatedEntity = vehiculeService.updateVehicule(id, updatedVehicule);
            return ResponseEntity.ok(updatedEntity);
        } catch (VehiculeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PostMapping("/vehicules")
    public ResponseEntity<VehiculeDTO> addVehicule(@RequestBody  VehiculeDTO vehiculeDTO){
        VehiculeDTO vehiculeDto=vehiculeService.addVehicule(vehiculeDTO);
        return  new ResponseEntity<>(vehiculeDto, HttpStatus.CREATED);
    }
}
