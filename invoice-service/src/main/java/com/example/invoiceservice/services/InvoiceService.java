package com.example.invoiceservice.services;

import com.commons.dtos.PageResponseDto;
import com.example.invoiceservice.entities.Invoice;
import com.example.invoiceservice.exceptions.InvoiceNotFoundException;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Invoice getInvoiceById(Long id) throws InvoiceNotFoundException;

    PageResponseDto<Invoice> getInvoices(Pageable pageable);
}