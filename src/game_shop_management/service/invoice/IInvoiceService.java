package game_shop_management.service.invoice;

import game_shop_management.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    void addInvoice(Invoice invoice);
    void removeInvoice(String invoiceId);
    List<Invoice> getInvoicesByCustomerId(String customerId);
    List<Invoice> getAllInvoices();
    Optional<Invoice> findInvoiceById(String invoiceId);
}