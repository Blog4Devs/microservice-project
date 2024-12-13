package com.example.vehicule_service.controllers;


import com.commons.dtos.PageResponseDto;
import com.commons.dtos.VehiculeDTO;
import com.example.vehicule_service.exceptions.VehiculeNotFoundException;
import com.example.vehicule_service.services.VehiculeService;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import com.example.vehicule_service.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicules")
@Slf4j
@CrossOrigin("*")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping
    public ResponseEntity<PageResponseDto<VehiculeDTO>> get_vehicules(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size,
        @RequestParam(name = "keyword", defaultValue = "") Long clientId
    ) 
    {
        PageResponseDto<VehiculeDTO> vehicules = vehiculeService.getVehicule(page, size,clientId);
        return ResponseEntity.ok(vehicules);
    }
    
    @PatchMapping("/{id}")
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        try {
            VehiculeDTO vehiculeDTO=vehiculeService.getVehiculeById(id);
            return ResponseEntity.ok(vehiculeDTO);
        } catch (VehiculeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PutMapping("/{id}")
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

    @PostMapping
    public ResponseEntity<?> addVehicule(@RequestBody VehiculeDTO vehiculeDTO){
        try{
            VehiculeDTO vehiculeDto=vehiculeService.addVehicule(vehiculeDTO);
            return new ResponseEntity<>(vehiculeDto, HttpStatus.CREATED);
        }
        catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
