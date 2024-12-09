package com.example.maintenance_service.services;

import com.commons.dtos.MaintenanceDto;
import com.commons.dtos.ClientDTO;

import com.commons.dtos.PageResponseDto;
import com.commons.dtos.MaintenanceDto.OperationDto;
import com.commons.mappers.PageResponseMapper;
import com.example.maintenance_service.entities.Maintenance;
import com.commons.enums.MaintenanceStatus;
import com.example.maintenance_service.exceptions.MaintenanceNotFoundException;
import com.example.maintenance_service.exceptions.VehiculeNotFoundException;
import com.example.maintenance_service.mappers.MaintenanceMapper;
import com.example.maintenance_service.mappers.MaintenanceMapper.OperationMapper;
import com.example.maintenance_service.proxy.VehicleFeignClient;
import com.example.maintenance_service.proxy.ClientFeignClient;

import com.example.maintenance_service.repositories.MaintenanceRepository;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleFeignClient vehicleFeignClient;
    private final ClientFeignClient clientFeignClient;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private JavaMailSender mailSender;

    public MaintenanceServiceImpl(
        MaintenanceRepository maintenanceRepository, VehicleFeignClient vehicleFeignClient,ClientFeignClient clientFeignClient,JavaMailSender mailSender) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleFeignClient = vehicleFeignClient;
        this.mailSender=mailSender;
        this.clientFeignClient= clientFeignClient;
    }

    @Override
    public MaintenanceDto scheduleMaintenance(MaintenanceDto maintenance) throws VehiculeNotFoundException {
        Boolean vehiculeExist = vehicleFeignClient.getVehicleById(maintenance.getVehicleId());

        if (!vehiculeExist) {
            throw new VehiculeNotFoundException(maintenance.getVehicleId());
        }

        if (maintenance.getStatus() == null) {
            maintenance.setStatus(MaintenanceStatus.PROCESSING);
            sendEmail(maintenance.getIdProprietaire(),"subject","body");
        }

        Maintenance maintenance2 = MaintenanceMapper.toEntity(maintenance);
        maintenance2.setUpdatedAt(Instant.now());
        maintenanceRepository.save(maintenance2);
        return maintenance;
    }

    @Override
    public MaintenanceDto updateMaintenance(
            Long maintenanceId, String description, MaintenanceStatus status, Instant endTime)
            throws MaintenanceNotFoundException {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance == null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }
        if (description != null) {
            maintenance.setDescription(description);
        }
        if (status != null) {
            // TODO: make it transactional
            if (status == MaintenanceStatus.FINISHED) {
                vehicleFeignClient.updateVehicleStatus(maintenance.getVehicleId(),
                        MaintenanceStatus.FINISHED.toString());

                maintenance.setEndTime(Instant.now());
                
                sendEmail(maintenance.getIdProprietaire(),"subject","body");

            }
            maintenance.setStatus(status);
        }
        maintenance.setUpdatedAt(Instant.now());
        maintenanceRepository.save(maintenance);
        return MaintenanceMapper.toDto(maintenance);
    }

    @Override
    public MaintenanceDto addOperations(Long maintenanceId, List<OperationDto> operations)
            throws MaintenanceNotFoundException {

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);

        if (maintenance == null) {
            throw new MaintenanceNotFoundException(maintenanceId);
        }

        maintenance.getOperations().addAll(operations
                    .stream()
                    .map(OperationMapper::toEntity)
                    .collect(Collectors.toList()));
        maintenance.setUpdatedAt(Instant.now());
        maintenanceRepository.save(maintenance);
        return MaintenanceMapper.toDto(maintenance);
    }

    @Override
    public PageResponseDto<MaintenanceDto> getMaintenanceByVehicleIdAndStatus(
            Long vehicleId, MaintenanceStatus maintenanceStatus, Pageable pageable) {
        Page<Maintenance> maintenances = null;
        if (maintenanceStatus == null) {
            maintenances = maintenanceRepository.findByVehicleId(vehicleId, pageable);
        }
        maintenances = maintenanceRepository.findByVehicleIdAndStatus(vehicleId, maintenanceStatus, pageable);
        return PageResponseMapper.toPageResponseDto(
            maintenances.map(((maintenance) -> MaintenanceMapper.toDto(maintenance)))
        );
    }

    @Override
    public PageResponseDto<MaintenanceDto> getAllMaintenance(Pageable pageable) {
        return PageResponseMapper.toPageResponseDto(
            maintenanceRepository.findAll(pageable).map(maintenance -> MaintenanceMapper.toDto(maintenance))
        );
    }

    public void sendEmail(Long maintenanceId,String subject,String body){
        ClientDTO client = clientFeignClient.getClientById(maintenanceId);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(client.getAddress());
        message.setSubject(subject);
        message.setText(body);

        message.setFrom(fromEmail);

        mailSender.send(message);
    }

}
