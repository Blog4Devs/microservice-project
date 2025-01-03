package com.example.cdc_service.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.commons.dtos.MaintenanceDto;
import com.commons.dtos.VehiculeDTO;
import com.example.cdc_service.entities.client.Client;
import com.example.cdc_service.entities.invoice.Invoice;
import com.example.cdc_service.entities.maintenance.Maintenance;
import com.example.cdc_service.entities.vehicule.Vehicule;
import com.example.cdc_service.mappers.ClientMapper;
import com.example.cdc_service.mappers.MaintenanceMapper;
import com.example.cdc_service.mappers.VehiculeMapper;
import com.example.cdc_service.repositories.client.ClientRepository;
import com.example.cdc_service.repositories.invoice.InvoiceRepository;
import com.example.cdc_service.repositories.maintenance.MaintenanceRepository;
import com.example.cdc_service.repositories.vehicule.VehiculeRepository;


import java.io.*;
import java.nio.file.*;
import java.time.format.DateTimeParseException;

import org.springframework.core.io.Resource;

@Service
public class CdcService {

    private static final String SYNC_TIME_FILE = "/home/lastSyncTime.txt";
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final MaintenanceRepository maintenanceRepository; 
    private final InvoiceRepository invoiceRepository;

    private Instant lastSyncTime;

    public CdcService(ClientRepository clientRepository, VehiculeRepository vehiculeRepository,
                      MaintenanceRepository maintenanceRepository, InvoiceRepository invoiceRepository) {
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.invoiceRepository = invoiceRepository;
        this.lastSyncTime = readLastSyncTime();
    }

    @Scheduled(fixedRate = 5000)
    public void pollChanges() {
        pollClients();
        pollVehicules();
        pollMaintenances();
       lastSyncTime = Instant.now();
        writeLastSyncTime(lastSyncTime);
    }

    private void writeLastSyncTime(Instant time) {
        try {
            // Create directories if they don't exist
            File file = new File(SYNC_TIME_FILE);
            // Write to the file
            Files.writeString(file.toPath(), time.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Instant readLastSyncTime() {
        try {
            File file = new File(SYNC_TIME_FILE);
            if (!file.exists()) {
                System.out.println("i'm here hahahahahahahahaha");
                file.createNewFile();

                return Instant.now().minus(50, ChronoUnit.MINUTES);
            }
            String content = Files.readString(file.toPath()).trim();
            System.out.println("content "+content);
            return Instant.parse(content);
        } catch (IOException | DateTimeParseException e) {
            return Instant.now().minus(5, ChronoUnit.MINUTES);
        }
    }
//    private Instant readLastSyncTime() {
//        System.out.println("--------------------------------------------------------------");
//        System.out.println("yssssssssssssssssssssssssssssssssssssssro");
//        System.out.println("--------------------------------------------------------------");
//
//        try {
//            Resource resource = new ClassPathResource(SYNC_TIME_FILE);
//            if (!resource.exists()) {
//                return Instant.now().minus(5, ChronoUnit.MINUTES);
//            }
//            Path path = resource.getFile().toPath();
//            String content = Files.readString(path).trim();
//            return Instant.parse(content);
//        } catch (IOException | DateTimeParseException e) {
//            return Instant.now().minus(5, ChronoUnit.MINUTES);
//        }
//
//    }
//
//    private void writeLastSyncTime(Instant time) {
//        System.out.println("--------------------------------------------------------------");
//        System.out.println(time);
//        System.out.println("--------------------------------------------------------------");
//        try {
//            Resource resource = new ClassPathResource(SYNC_TIME_FILE);
//            Path path = resource.getFile().toPath();
//            System.out.println("--------------------------------------------------------------");
//            System.out.println(path.getFileName());
//            System.out.println("--------------------------------------------------------------");
//
//            try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
//                System.out.println(Arrays.toString(time.toString().getBytes()));
//                outputStream.write(time.toString().getBytes());
//                System.out.println("////////////////////////////////////////////////////");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void pollClients() {
        List<Client> changes = clientRepository.findByUpdatedAtAfter(lastSyncTime);
        System.out.println("changes "+changes+" "+lastSyncTime);
        List<Invoice> invoices = new ArrayList<>();
        changes.forEach(change -> {
            Invoice invoice = mapClientToInvoice(change);
            invoices.add(invoice);
        });
        invoiceRepository.saveAll(invoices);
    }

    public void pollVehicules() {
        List<Vehicule> changes = vehiculeRepository.findByUpdatedAtAfter(lastSyncTime);
        List<Invoice> invoices = new ArrayList<>();
        changes.forEach(change -> {
            Invoice invoice = mapVehiculeToInvoice(change);
            invoices.add(invoice);
        });
        invoiceRepository.saveAll(invoices);
    }

    public void pollMaintenances() {
        List<Maintenance> changes = maintenanceRepository.findByUpdatedAtAfter(lastSyncTime);
        List<Invoice> invoices = new ArrayList<>();
        changes.forEach(change -> {
            Invoice invoice = mapMaintenanceToInvoice(change);
            invoices.add(invoice);
        });
        invoiceRepository.saveAll(invoices);
    }

    private Invoice mapClientToInvoice(Client client) {
        Invoice invoice = invoiceRepository.findById(client.getId()).orElse(null);
        if (invoice == null) {
            invoice = new Invoice();
            invoice.setId(client.getId());
        }
        invoice.setClient(ClientMapper.toDTO(client));
        return invoice;
    }

    private Invoice mapVehiculeToInvoice(Vehicule vehicule) {
        Invoice invoice = invoiceRepository.findById(vehicule.getIdProprietaire()).get();
    
        List<VehiculeDTO> vehiculesDTO = invoice.getVehicules();
        boolean exists = vehiculesDTO.stream()
            .anyMatch(v -> v.getId().equals(vehicule.getId()));
        
        if (exists) {
            vehiculesDTO = vehiculesDTO.stream()
                .map((v) -> v.getId().equals(vehicule.getId()) ? VehiculeMapper.toDto(vehicule) : v)
                .collect(Collectors.toList());
        } else {
            vehiculesDTO.add(VehiculeMapper.toDto(vehicule));
        }
        invoice.setVehicules(vehiculesDTO);
    
    return invoice;
}


    private Invoice mapMaintenanceToInvoice(Maintenance maintenance) {
        Invoice invoice = invoiceRepository.findById(maintenance.getIdProprietaire()).get();
        List<MaintenanceDto> maintenanceDtos = invoice.getMaintenances();
        boolean exists = maintenanceDtos.stream()
            .anyMatch(m -> m.getId().equals(maintenance.getId()));

        if (exists) {
            maintenanceDtos = maintenanceDtos.stream()
                .map(m -> m.getId().equals(maintenance.getId()) ? MaintenanceMapper.toDto(maintenance) : m)
                .collect(Collectors.toList());
        } else {
            maintenanceDtos.add(MaintenanceMapper.toDto(maintenance));
        }

        invoice.setMaintenances(maintenanceDtos);
        return invoice;
    }

}