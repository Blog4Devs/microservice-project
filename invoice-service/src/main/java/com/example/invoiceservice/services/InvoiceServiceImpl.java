package com.example.invoiceservice.services;

import com.commons.dtos.PageResponseDto;
import com.commons.mappers.PageResponseMapper;
import com.example.invoiceservice.entities.Invoice;
import com.example.invoiceservice.exceptions.InvoiceNotFoundException;
import com.example.invoiceservice.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice getInvoiceById(Long id) throws InvoiceNotFoundException {
        return invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice with ID " + id + " not found"));
    }

    @Override
    public PageResponseDto<Invoice> getInvoices(Pageable pageable) {
        Page<Invoice> invoices = invoiceRepository.findAll(pageable);
        return PageResponseMapper.toPageResponseDto(invoices);
    }
}