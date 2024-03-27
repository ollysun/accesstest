package com.access.invoicetest.gateway;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDetails {
    private BigDecimal amount;
    private String bankname;
}
