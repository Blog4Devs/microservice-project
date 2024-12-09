package com.example.cdc_service;

import com.commons.Main;
import com.commons.enums.Carburant;
import com.commons.enums.MaintenanceStatus;
import com.example.cdc_service.entities.client.Client;
import com.example.cdc_service.entities.invoice.Invoice;
import com.example.cdc_service.entities.maintenance.Maintenance;
import com.example.cdc_service.entities.maintenance.Operation;
import com.example.cdc_service.entities.vehicule.Vehicule;
import com.example.cdc_service.repositories.client.ClientRepository;
import com.example.cdc_service.repositories.invoice.InvoiceRepository;
import com.example.cdc_service.repositories.maintenance.MaintenanceRepository;
import com.example.cdc_service.repositories.maintenance.OperationRepository;
import com.example.cdc_service.repositories.vehicule.VehiculeRepository;
import com.example.cdc_service.services.CdcService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//@Component
public class CdcServiceTester  {

    private final CdcService cdcService;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final OperationRepository operationRepository ;
    private final MaintenanceRepository maintenanceRepository;
    private final InvoiceRepository invoiceRepository;

    public CdcServiceTester(CdcService cdcService, ClientRepository clientRepository,
                            VehiculeRepository vehiculeRepository, MaintenanceRepository maintenanceRepository,
                            InvoiceRepository invoiceRepository, OperationRepository operationRepository) {
        this.cdcService = cdcService;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.invoiceRepository = invoiceRepository;
        this.operationRepository=operationRepository;
    }

//    @Override
//    public void run(String... args) {
//        System.out.println("Starting CDC Service Testing...");
//
//        // Step 1: Create and save a Client
//        Client client = new Client();
//        client.setName("John Doe");
//        client.setAddress("123 Main Street");
//        client.setPhone("1239baaa");
//        client.setEmail("jnzezezzrbzg@example.com");
//        client.setCin("Aevezzzbzzr4");
//        client.setUpdatedAt(Instant.now());
//        clientRepository.save(client);
//
//        // Step 2: Create and save a Vehicule
//        Vehicule vehicule = new Vehicule();
//        vehicule.setVin("1HGCM82633A123456");
//        vehicule.setNumMatriculation("123-ABC");
//        vehicule.setMarque("Toyota");
//        vehicule.setAnnee(2022);
//        vehicule.setColor("Red");
//        vehicule.setKilometrage(12000);
//        vehicule.setCarburant(Carburant.DIESEL); // Enum type for fuel
//        vehicule.setDateAchat(Instant.now().minusSeconds(3600));
//        vehicule.setIdProprietaire(client.getId());
//        vehicule.setDelivered(false);
//        vehicule.setUpdatedAt(Instant.now());
//        vehiculeRepository.save(vehicule);
//
//        Operation operation = new Operation();
//        operation.setDescription("Engine Oil Change");
//        operation.setPrice(100L);
//        operation.setUpdatedAt(Instant.now());
//
//        Maintenance maintenance = new Maintenance();
//        maintenance.setDescription("Regular Maintenance");
//        maintenance.setStartTime(Instant.now());
//        maintenance.setPredictedEndTime(Instant.now().plusSeconds(3600));
//        maintenance.setEndTime(null);
//        maintenance.setStatus(MaintenanceStatus.PROCESSING);
//        maintenance.setVehicleId(vehicule.getIdVehicle());
//        maintenance.setIdProprietaire(client.getId());
//        maintenance.setOperations(List.of(operation));
//        maintenance.setPaid(false);
//        maintenance.setUpdatedAt(Instant.now());
//        maintenanceRepository.save(maintenance);
//
//        // Step 3: Create and save a Maintenance
//
//
//        System.out.println("Entities created and saved.");
//
//        // Step 4: Trigger the synchronization workflow
//        System.out.println("Polling for changes...");
//        cdcService.pollChanges();
//
//        // Step 5: Retrieve and display data
//        List<Invoice> invoices = invoiceRepository.findAll();
//        System.out.println("Invoices:");
//        invoices.forEach(invoice -> {
//            System.out.println("Invoice ID: " + invoice.getId());
//            System.out.println("Client: " + invoice.getClient());
//            System.out.println("Vehicles: " + invoice.getVehicules());
//            System.out.println("Maintenances: " + invoice.getMaintenances());
//        });
//
//        System.out.println("CDC Service Testing Completed.");
//    }
}
