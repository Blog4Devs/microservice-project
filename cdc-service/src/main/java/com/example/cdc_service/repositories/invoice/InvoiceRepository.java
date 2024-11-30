package com.example.cdc_service.repositories.invoice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.cdc_service.entities.invoice.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, Long> {
}