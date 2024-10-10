package game_shop_management.repository.invoice;

import game_shop_management.model.Invoice;

import java.util.List;

public interface IInvoiceRepository {
    void saveInvoice(Invoice invoice);
    void deleteInvoice(String invoiceId);
    List<Invoice> loadInvoices();
    List<Invoice> findInvoicesByCustomerId(String customerId);
    List<Invoice> getAllInvoices();
}