package com.access.invoicetest.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.access.invoicetest.constant.PaymentStatus;
import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.repository.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InvoiceService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class InvoiceServiceTest {
    @MockBean
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceService invoiceService;

    /**
     * Method under test: {@link InvoiceService#createInvoice(Invoice)}
     */
    @Test
    void testCreateInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        when(invoiceRepository.save(Mockito.<Invoice>any())).thenReturn(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1L);
        invoice2.setInvoiceNumber("42");
        invoice2.setPaid(true);
        invoice2.setPaymentStatus(PaymentStatus.PENDING);

        // Act
        Invoice actualCreateInvoiceResult = invoiceService.createInvoice(invoice2);

        // Assert
        verify(invoiceRepository).save(isA(Invoice.class));
        assertSame(invoice, actualCreateInvoiceResult);
    }



    /**
     * Method under test: {@link InvoiceService#getAllInvoices()}
     */
    @Test
    void testGetAllInvoices() {
        // Arrange
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        when(invoiceRepository.findAll()).thenReturn(invoiceList);

        // Act
        List<Invoice> actualAllInvoices = invoiceService.getAllInvoices();

        // Assert
        verify(invoiceRepository).findAll();
        assertTrue(actualAllInvoices.isEmpty());
        assertSame(invoiceList, actualAllInvoices);
    }



    /**
     * Method under test: {@link InvoiceService#getInvoiceByInvoiceNumber(String)}
     */
    @Test
    void testGetInvoiceByInvoiceNumber() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        Optional<Invoice> ofResult = Optional.of(invoice);
        when(invoiceRepository.findByInvoiceNumber(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Optional<Invoice> actualInvoiceByInvoiceNumber = invoiceService.getInvoiceByInvoiceNumber("42");

        // Assert
        verify(invoiceRepository).findByInvoiceNumber(eq("42"));
        assertSame(ofResult, actualInvoiceByInvoiceNumber);
    }



    /**
     * Method under test: {@link InvoiceService#getInvoiceById(Long)}
     */
    @Test
    void testGetInvoiceById() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        Optional<Invoice> ofResult = Optional.of(invoice);
        when(invoiceRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Invoice actualInvoiceById = invoiceService.getInvoiceById(1L);

        // Assert
        verify(invoiceRepository).findById(isA(Long.class));
        assertSame(invoice, actualInvoiceById);
    }


    /**
     * Method under test: {@link InvoiceService#saveInvoice(Invoice)}
     */
    @Test
    void testSaveInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        when(invoiceRepository.save(Mockito.<Invoice>any())).thenReturn(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1L);
        invoice2.setInvoiceNumber("42");
        invoice2.setPaid(true);
        invoice2.setPaymentStatus(PaymentStatus.PENDING);

        // Act
        Invoice actualSaveInvoiceResult = invoiceService.saveInvoice(invoice2);

        // Assert
        verify(invoiceRepository).save(isA(Invoice.class));
        assertSame(invoice, actualSaveInvoiceResult);
    }


    /**
     * Method under test: {@link InvoiceService#updateInvoice(Long, Invoice)}
     */
    @Test
    void testUpdateInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        Optional<Invoice> ofResult = Optional.of(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1L);
        invoice2.setInvoiceNumber("42");
        invoice2.setPaid(true);
        invoice2.setPaymentStatus(PaymentStatus.PENDING);
        when(invoiceRepository.save(Mockito.<Invoice>any())).thenReturn(invoice2);
        when(invoiceRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Invoice updatedInvoice = new Invoice();
        updatedInvoice.setId(1L);
        updatedInvoice.setInvoiceNumber("42");
        updatedInvoice.setPaid(true);
        updatedInvoice.setPaymentStatus(PaymentStatus.PENDING);

        // Act
        Invoice actualUpdateInvoiceResult = invoiceService.updateInvoice(1L, updatedInvoice);

        // Assert
        verify(invoiceRepository).findById(isA(Long.class));
        verify(invoiceRepository).save(isA(Invoice.class));
        assertSame(invoice2, actualUpdateInvoiceResult);
    }


    /**
     * Method under test: {@link InvoiceService#deleteInvoice(Long)}
     */
    @Test
    void testDeleteInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        Optional<Invoice> ofResult = Optional.of(invoice);
        doNothing().when(invoiceRepository).delete(Mockito.<Invoice>any());
        when(invoiceRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        invoiceService.deleteInvoice(1L);

        // Assert that nothing has changed
        verify(invoiceRepository).delete(isA(Invoice.class));
        verify(invoiceRepository).findById(isA(Long.class));
    }


    /**
     * Method under test: {@link InvoiceService#processPayment(Long)}
     */
    @Test
    void testProcessPayment() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber("42");
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        Optional<Invoice> ofResult = Optional.of(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setId(1L);
        invoice2.setInvoiceNumber("42");
        invoice2.setPaid(true);
        invoice2.setPaymentStatus(PaymentStatus.PENDING);
        when(invoiceRepository.save(Mockito.<Invoice>any())).thenReturn(invoice2);
        when(invoiceRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        boolean actualProcessPaymentResult = invoiceService.processPayment(1L);

        // Assert
        verify(invoiceRepository).findById(isA(Long.class));
        verify(invoiceRepository).save(isA(Invoice.class));
        assertTrue(actualProcessPaymentResult);
    }
}
