package com.example.vehicule_service.controllers;

import com.example.vehicule_service.dtos.VehiculeDTO_Responce;
import com.example.vehicule_service.entities.Vehicule;
import com.example.vehicule_service.services.VehiculeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin("*")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

@GetMapping("/vehicules")
    public ResponseEntity< VehiculeDTO_Responce<Vehicule> > get_vehicules(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size,
        @RequestParam(name = "keyword", defaultValue = "") Long id_proprietaire
) {
    VehiculeDTO_Responce<Vehicule>  vehicules = vehiculeService.get_Vehicule(page, size,id_proprietaire );
    return ResponseEntity.ok(vehicules);
}


@PostMapping("/vehicules")
    public ResponseEntity add_vehicule(@RequestBody Vehicule vehicule){
return null;
}



}
