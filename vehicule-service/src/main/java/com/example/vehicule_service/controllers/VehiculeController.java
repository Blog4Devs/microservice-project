package com.example.vehicule_service.controllers;

import com.example.vehicule_service.dtos.VehiculeDTO;
import com.example.vehicule_service.dtos.VehiculeDTOResponce;
import com.example.vehicule_service.entities.Vehicule;
import com.example.vehicule_service.services.VehiculeService;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<VehiculeDTOResponce<Vehicule>> get_vehicules(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size,
        @RequestParam(name = "keyword", defaultValue = "") Long id_proprietaire
) {
    VehiculeDTOResponce<Vehicule> vehicules = vehiculeService.get_Vehicule(page, size,id_proprietaire );
    return ResponseEntity.ok(vehicules);
}
    @GetMapping("/vehicules/state")
    public void  update_vehicule(Long id_vehicule,String state){
    vehiculeService.update_vehicule_status(id_vehicule, state);

    }



@PostMapping("/vehicules")
    public ResponseEntity<Long> add_vehicule(@RequestBody VehiculeDTO vehiculeDTO){
Long id_vehicule=vehiculeService.add_vehicule(vehiculeDTO);
return  new ResponseEntity<>(id_vehicule, HttpStatus.CREATED);

}



}
