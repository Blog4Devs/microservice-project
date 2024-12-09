package com.example.invoiceservice.exceptions;

public class InvoiceNotFoundException extends Exception {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}