package com.access.invoicetest.entity;

import com.access.invoicetest.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal amount;
    
    private PaymentStatus status; // Enum for payment status (e.g., PENDING, SUCCESS, FAILED)
    
    @ManyToOne
    private Invoice invoice; // If payments are linked to invoices
    
    private LocalDateTime createdDate;
    
    private LocalDateTime modifiedDate;
    
}