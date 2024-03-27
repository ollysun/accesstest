package com.access.invoicetest.repository;

import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
