package com.example.invoiceservice.controllers;

import com.example.invoiceservice.entities.Invoice;
import com.example.invoiceservice.exceptions.InvoiceNotFoundException;
import com.example.invoiceservice.services.InvoiceService;
import com.commons.dtos.PageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @QueryMapping
    public Invoice getInvoiceById(@Argument Long id) throws InvoiceNotFoundException{
        Invoice invoice = invoiceService.getInvoiceById(id);
        return invoice;
    }

    @QueryMapping
    public PageResponseDto<Invoice> getInvoices(@Argument int page, @Argument int size) {
        return invoiceService.getInvoices(PageRequest.of(page, size));
    }
}