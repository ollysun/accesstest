package com.access.invoicetest.service;

import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    // Method to get all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceByInvoiceNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber);
    }

    // Method to get an invoice by ID
    public Invoice getInvoiceById(Long invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            // Handle case where the invoice is not found
            throw new RuntimeException("Invoice not found with ID: " + invoiceId);
        }
    }

    // Method to save an invoice
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    // Method to update an invoice
    public Invoice updateInvoice(Long invoiceId, Invoice updatedInvoice) {
        Invoice invoice = getInvoiceById(invoiceId);

        // Update invoice details
        invoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
        // Update other invoice details

        return invoiceRepository.save(invoice);
    }

    // Method to delete an invoice
    public void deleteInvoice(Long invoiceId) {
        Invoice invoice = getInvoiceById(invoiceId);
        invoiceRepository.delete(invoice);
    }



    public boolean processPayment(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);

        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setPaid(true);
            invoiceRepository.save(invoice);
            return true; // Payment processed successfully
        } else {
            return false; // Invoice not found
        }
    }
    
    // Methods to handle invoice-related business logic
}

// Customer service
