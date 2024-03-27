package com.access.invoicetest.gateway;

import com.access.invoicetest.dto.PaymentResponse;
import com.access.invoicetest.entity.Invoice;

public interface PaymentGateway {
    PaymentResponse processPayment(Invoice invoice, PaymentDetails paymentDetails);
    // Other payment-related methods
}