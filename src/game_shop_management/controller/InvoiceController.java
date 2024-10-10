package game_shop_management.controller;

import game_shop_management.model.Invoice;
import game_shop_management.service.invoice.IInvoiceService;
import game_shop_management.service.invoice.InvoiceService;

import java.util.List;
import java.util.Optional;

public class InvoiceController {
    private final IInvoiceService invoiceService;

    public InvoiceController() {
        this.invoiceService = new InvoiceService();
    }

    public void addInvoice(Invoice invoice) {
        invoiceService.addInvoice(invoice);
    }

    public void removeInvoice(String invoiceId) {
        invoiceService.removeInvoice(invoiceId);
    }

    public List<Invoice> getInvoicesByCustomerId(String customerId) {
        return invoiceService.getInvoicesByCustomerId(customerId);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    public Optional<Invoice> findInvoiceById(String invoiceId) {
        return invoiceService.findInvoiceById(invoiceId);
    }
}