package com.example.maintenance_service.services;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.maintenance_service.entities.Maintenance;
import com.example.maintenance_service.entities.MaintenanceStatus;
import com.example.maintenance_service.entities.Operation;
import com.example.maintenance_service.proxy.VehicleFeignClient;
import com.example.maintenance_service.repositories.MaintenanceRepository;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleFeignClient vehicleFeignClient;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository,
                              VehicleFeignClient vehicleFeignClient
                              ) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleFeignClient = vehicleFeignClient;
    }

    @Override
    public Maintenance scheduleMaintenance(Maintenance maintenance) throws VehiculeNotFoundException{
        Boolean vehiculeExist = vehicleFeignClient.getVehicleById(maintenance.getVehicleId());

        if (!vehiculeExist) {
            throw new VehiculeNotFoundException(maintenance.getVehicleId());
        }

        if(maintenance.getStatus()==null){
            maintenance.setStatus(MaintenanceStatus.PROCESSING);
        }
        
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(Long maintenanceId, String description,MaintenanceStatus status, Instant endTime ) throws MaintenanceNotFoundException {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance==null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }

        if(description!=null){
            maintenance.setDescription(description);
        }

        if(status!=null){
            maintenance.setStatus(status);
        }

        if(endTime!=null){
            maintenance.setEndTime(endTime);
        }
        
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance addOperations(Long maintenanceId, List<Operation> operations) throws MaintenanceNotFoundException{
        
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance==null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }
        
        maintenance.getOperations().addAll(operations);
        
        return maintenanceRepository.save(maintenance);
    }
    
    @Override
    public Page<Maintenance> getMaintenanceByVehicleIdAndStatus(Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable) {
        if (maintenanceStatus == null) {
            return maintenanceRepository.findByVehicleId(vehicleId, pageable);
        }
        return maintenanceRepository.findByVehicleIdAndStatus(vehicleId, maintenanceStatus, pageable);
    }

    @Override
    public Page<Maintenance> getAllMaintenance(Pageable pageable) {
        return maintenanceRepository.findAll(pageable);
    }

}

