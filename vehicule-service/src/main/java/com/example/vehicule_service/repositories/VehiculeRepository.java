package com.example.vehicule_service.repositories;

import com.example.vehicule_service.entities.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository  extends JpaRepository<Vehicule,Long> {
   Page<Vehicule>   findByIdproprietaire(Long id, Pageable pageable);
}
