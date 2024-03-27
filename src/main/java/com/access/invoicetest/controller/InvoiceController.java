package com.access.invoicetest.controller;

import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceDetailByInvoiceNumber(@PathVariable String invoiceNumber) {
        Optional<Invoice> invoice = invoiceService.getInvoiceByInvoiceNumber(invoiceNumber);
        
        return invoice.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{invoiceId}/payment")
    public ResponseEntity<String> processPayment(@PathVariable Long invoiceId) {
        boolean paymentProcessed = invoiceService.processPayment(invoiceId);

        if (paymentProcessed) {
            return ResponseEntity.ok("Payment processed successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
