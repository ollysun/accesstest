package com.access.invoicetest.controller;

import com.access.invoicetest.dto.PaymentResponse;
import com.access.invoicetest.gateway.PaymentDetails;
import com.access.invoicetest.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process/{invoiceId}")
    public PaymentResponse processPaymentForInvoice(@PathVariable Long invoiceId, @RequestBody PaymentDetails paymentDetails) {
        return paymentService.processPaymentForInvoice(invoiceId, paymentDetails);
    }
}