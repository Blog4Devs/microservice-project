package com.example.cdc_service.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

@Service
public class CdcServiceTest {

    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final InvoiceRepository invoiceRepository;

    private Instant lastSyncTime = Instant.now().minus(5, ChronoUnit.MINUTES);

    public CdcServiceTest(ClientRepository clientRepository, VehiculeRepository vehiculeRepository,
                      MaintenanceRepository maintenanceRepository, InvoiceRepository invoiceRepository) {
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.invoiceRepository = invoiceRepository;
        // TODO: get lastSyncTime from the file
    }

    @Scheduled(fixedRate = 5000)
    public void pollChanges() {
        pollClients();
        pollVehicules();
        pollMaintenances();
        lastSyncTime = Instant.now(); // TODO: to be stored in a file
    }

    
    public void pollClients() {
        List<Client> changes = clientRepository.findByUpdatedAtAfter(lastSyncTime);
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
        List<VehiculeDTO> vehiculesDTO=invoice.getVehicules();
        vehiculesDTO.add(VehiculeMapper.toDto(vehicule)) ;
        invoice.setVehicules(vehiculesDTO);
        return invoice;
    }

    private Invoice mapMaintenanceToInvoice(Maintenance maintenance) {
        Invoice invoice = invoiceRepository.findById(maintenance.getIdProprietaire()).get();
        invoice.getMaintenances().add(MaintenanceMapper.toDto(maintenance));
        return invoice;
    }
}