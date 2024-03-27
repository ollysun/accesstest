package com.access.invoicetest.controller;

import com.access.invoicetest.service.TransactionLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction-links")
public class TransactionLinkController {
    private final TransactionLinkService transactionLinkService;
    
    @GetMapping("/{linkId}")
    public ResponseEntity<?> getLinkDetails(@PathVariable Long linkId) {
        // Retrieve and return link details
        return null;
    }
    
//    @PostMapping("/{linkId}/pay")
//    public ResponseEntity<?> processPayment(@PathVariable Long linkId, @RequestBody PaymentDetails paymentDetails) {
//        // Process payment for the invoice associated with the link
//    }
    
    // Other endpoints for managing transaction links
}