// package com.example.cdc_service.services;

// import java.time.Instant;
// import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;
// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import com.example.cdc_service.entities.client.Client;
// import com.example.cdc_service.entities.invoice.Invoice;
// import com.example.cdc_service.entities.maintenance.Maintenance;
// import com.example.cdc_service.entities.vehicule.Vehicule;
// import com.example.cdc_service.repositories.client.ClientRepository;
// import com.example.cdc_service.repositories.invoice.InvoiceRepository;
// import com.example.cdc_service.repositories.maintenance.MaintenanceRepository;
// import com.example.cdc_service.repositories.vehicule.VehiculeRepository;

// @Service
// public class CdcService {

//     private final ClientRepository clientRepository;
//     private final VehiculeRepository vehiculeRepository;
//     private final MaintenanceRepository maintenanceRepository;
//     private final InvoiceRepository invoiceRepository;

//     private Instant lastSyncTime = Instant.now().minus(5, ChronoUnit.MINUTES);

//     public CdcService(ClientRepository clientRepository, VehiculeRepository vehiculeRepository,
//             MaintenanceRepository maintenanceRepository, InvoiceRepository invoiceRepository) {
//         this.clientRepository = clientRepository;
//         this.vehiculeRepository = vehiculeRepository;
//         this.maintenanceRepository = maintenanceRepository;
//         this.invoiceRepository = invoiceRepository;
//     }

//     @Scheduled(fixedRate = 5000) // Run every 5 seconds
//     public void pollChanges() {
//         pollClients();
//         pollVehicules();
//         pollMaintenances();
//     }

//     public void pollClients() {
//         List<Client> changes = clientRepository.findByUpdateAtAfter(lastSyncTime);
//         changes.forEach(change -> {
//             Invoice invoice = mapClientToInvoice(change);
//             invoiceRepository.save(invoice);
//         });
//         lastSyncTime = Instant.now();
//     }

//     public void pollVehicules() {
//         List<Vehicule> changes = vehiculeRepository.findByUpdateAtAfter(lastSyncTime);
//         changes.forEach(change -> {
//             Invoice invoice = mapVehiculeToInvoice(change);
//             invoiceRepository.save(invoice);
//         });
//         lastSyncTime = Instant.now();
//     }

//     public void pollMaintenances() {
//         List<Maintenance> changes = maintenanceRepository.findByUpdateAtAfter(lastSyncTime);
//         changes.forEach(change -> {
//             Invoice invoice = mapMaintenanceToInvoice(change);
//             invoiceRepository.save(invoice);
//         });
//         lastSyncTime = Instant.now();
//     }

//     private Invoice mapToInvoice(Object sourceEntity, String sourceType) {
//         Invoice invoice = new Invoice();
//         // Mapping logic based on sourceType
//         return invoice;
//     }
// }