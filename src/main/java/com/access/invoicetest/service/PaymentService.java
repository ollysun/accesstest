package com.access.invoicetest.service;

import com.access.invoicetest.constant.PaymentStatus;
import com.access.invoicetest.dto.PaymentResponse;
import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.entity.Payment;
import com.access.invoicetest.exception.ResourceNotFoundException;
import com.access.invoicetest.gateway.PaymentDetails;
import com.access.invoicetest.gateway.PaymentGateway;
import com.access.invoicetest.repository.InvoiceRepository;
import com.access.invoicetest.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentGateway paymentGateway; // Assume this is the interface for your selected payment gateway SDK
    
    private final InvoiceRepository invoiceRepository;

    private final PaymentRepository paymentRepository;

    public PaymentResponse processPaymentForInvoice(Long invoiceId, PaymentDetails paymentDetails) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + invoiceId));

        // Use the payment gateway to initiate the payment process
        PaymentResponse paymentResponse = paymentGateway.processPayment(invoice, paymentDetails);

        if (paymentResponse.isSuccess()) {
            // If the payment is successful, create a record of the payment
            Payment payment = new Payment();
            payment.setAmount(BigDecimal.valueOf(1000.00));
            // Set other payment-related fields
            paymentRepository.save(payment);

            invoice.setPaymentStatus(PaymentStatus.SUCCESS);
            invoiceRepository.save(invoice);
        } else {
            invoice.setPaymentStatus(PaymentStatus.FAILED);
            invoiceRepository.save(invoice);
        }

        return paymentResponse;
    }
}
