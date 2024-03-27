package com.access.invoicetest.service;

import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.entity.TransactionLink;
import com.access.invoicetest.repository.TransactionLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionLinkService {
    private final TransactionLinkRepository transactionLinkRepository;
    
    // Method to generate unique transaction link
    public TransactionLink generateTransactionLink(Invoice invoice) {
        TransactionLink transactionLink = new TransactionLink();
        // Generate unique link and associate with the customer and invoice
        transactionLink.setLink(generateUniqueLink());
        transactionLink.setInvoice(invoice);
        transactionLink.setUsed(false);
        return transactionLinkRepository.save(transactionLink);
    }

    private String generateUniqueLink() {
        // Using a combination of UUID and current timestamp to create a unique link
        String uniqueID = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        return uniqueID + "-" + timestamp;
    }

    // Method to mark a transaction link as used
    public void markTransactionLinkAsUsed(Long transactionLinkId) {
        Optional<TransactionLink> optionalTransactionLink = transactionLinkRepository.findById(transactionLinkId);
        if (optionalTransactionLink.isPresent()) {
            TransactionLink transactionLink = optionalTransactionLink.get();
            transactionLink.setUsed(true);
            transactionLinkRepository.save(transactionLink);
        } else {
            // Handle case where the transaction link is not found
            throw new RuntimeException("Transaction link not found with ID: " + transactionLinkId);
        }
    }

    // Method to process payment for a specific link
    public boolean processPaymentForLink(Long linkId) {
        Optional<TransactionLink> optionalLink = transactionLinkRepository.findById(linkId);
        if (optionalLink.isPresent()) {
            TransactionLink link = optionalLink.get();

            // Check if the link has already been used for payment
            if (link.isUsed()) {
                throw new IllegalStateException("This link has already been used for payment.");
            }

            // Process the payment using the paymentDetails information
            // Implement payment processing logic here

            // Once payment is successful, update the link status to 'used'
            link.setUsed(true);
            transactionLinkRepository.save(link);

            return true; // Payment processed successfully
        } else {
            throw new RuntimeException("Transaction link not found with ID: " + linkId);
        }
    }

    // Custom methods to handle transaction link-related business logic
}