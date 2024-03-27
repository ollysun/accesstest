package com.access.invoicetest.controller;

import static org.mockito.Mockito.when;

import com.access.invoicetest.constant.PaymentStatus;
import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {InvoiceController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class InvoiceControllerTest {
    @Autowired
    private InvoiceController invoiceController;

    @MockBean
    private InvoiceService invoiceService;

    /**
     * Method under test: {@link InvoiceController#createInvoice(Invoice)}
     */
    @Test
    void testCreateInvoice() throws Exception {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        when(invoiceService.createInvoice(Mockito.<Invoice>any())).thenReturn(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1L);
        invoice2.setInvoiceNumber("42");
        invoice2.setPaid(true);
        invoice2.setPaymentStatus(PaymentStatus.PENDING);
        String content = (new ObjectMapper()).writeValueAsString(invoice2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"invoiceNumber\":\"42\",\"paid\":true,\"paymentStatus\":\"PENDING\"}"));
    }

    /**
     * Method under test: {@link InvoiceController#processPayment(Long)}
     */
    @Test
    void testProcessPayment() throws Exception {
        // Arrange
        when(invoiceService.processPayment(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/invoices/{invoiceId}/payment", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Payment processed successfully"));
    }

    /**
     * Method under test: {@link InvoiceController#processPayment(Long)}
     */
    @Test
    void testProcessPayment2() throws Exception {
        // Arrange
        when(invoiceService.processPayment(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/invoices/{invoiceId}/payment", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test:
     * {@link InvoiceController#getInvoiceDetailByInvoiceNumber(String)}
     */
    @Test
    void testGetInvoiceDetailByInvoiceNumber() throws Exception {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        Optional<Invoice> ofResult = Optional.of(invoice);
        when(invoiceService.getInvoiceByInvoiceNumber(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/invoices/{invoiceNumber}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(invoiceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"invoiceNumber\":\"42\",\"paid\":true,\"paymentStatus\":\"PENDING\"}"));
    }


}
