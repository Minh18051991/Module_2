package game_shop_management.service.invoice;

import game_shop_management.model.Invoice;
import game_shop_management.repository.invoice.IInvoiceRepository;
import game_shop_management.repository.invoice.InvoiceRepository;

import java.util.List;
import java.util.Optional;

public class InvoiceService implements IInvoiceService {
    private final IInvoiceRepository invoiceRepository;

    public InvoiceService() {
        this.invoiceRepository = new InvoiceRepository();
    }

    @Override
    public void addInvoice(Invoice invoice) {
        if (validateInvoice(invoice)) {
            invoiceRepository.saveInvoice(invoice);
            System.out.println("Hóa đơn đã được thêm thành công.");
        } else {
            System.out.println("Dữ liệu hóa đơn không hợp lệ!");
        }
    }

    @Override
    public void removeInvoice(String invoiceId) {
        if (findInvoiceById(invoiceId).isPresent()) {
            invoiceRepository.deleteInvoice(invoiceId);
            System.out.println("Hóa đơn đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với ID: " + invoiceId);
        }
    }

    @Override
    public List<Invoice> getInvoicesByCustomerId(String customerId) {
        return invoiceRepository.findInvoicesByCustomerId(customerId);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }

    @Override
    public Optional<Invoice> findInvoiceById(String invoiceId) {
        return invoiceRepository.getAllInvoices().stream()
                .filter(invoice -> invoice.getInvoiceId().equals(invoiceId))
                .findFirst();
    }

    // Xác thực dữ liệu hóa đơn
    private boolean validateInvoice(Invoice invoice) {
        if (invoice.getInvoiceId() == null || invoice.getInvoiceId().isEmpty()) return false;
        if (invoice.getCustomer() == null || invoice.getCustomer().getName().isEmpty()) return false;
        if (invoice.getInvoiceDate() == null || invoice.getInvoiceDate().isEmpty()) return false;
        return !invoice.getProducts().isEmpty(); // Đảm bảo có ít nhất một sản phẩm
    }
}