package com.access.invoicetest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    
    private boolean success; // Indicates whether the payment was successful
    
    private String message; // Additional message or description related to the payment outcome
    
    // Other fields to capture payment-related details such as transaction ID, authorization code, error code, etc.
    
    // Getters, setters, constructors, and other methods
}