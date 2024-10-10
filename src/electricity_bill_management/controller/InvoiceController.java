package electricity_bill_management.controller;

import electricity_bill_management.model.Customers;
import electricity_bill_management.model.Invoice;
import electricity_bill_management.repository.InvoiceRepository;

import java.util.List;

public class InvoiceController {
    private final InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Thêm hóa đơn
    public void addInvoice(Invoice invoice) {
        // Kiểm tra tính hợp lệ của hóa đơn trước khi thêm
        if (invoice != null) {
            invoiceRepository.saveInvoice(invoice);
        } else {
            System.out.println("Hóa đơn không hợp lệ.");
        }
    }

    // Lấy tất cả hóa đơn liên quan đến danh sách khách hàng
    public List<Invoice> getAllInvoices(List<Customers> customers) {
        if (customers == null || customers.isEmpty()) {
            System.out.println("Danh sách khách hàng rỗng.");
            return List.of(); // Trả về danh sách rỗng
        }
        return invoiceRepository.loadInvoices();
    }

    // Lấy hóa đơn theo khách hàng
    public List<Invoice> getInvoiceByCustomer(Customers customer) {
        if (customer == null) {
            System.out.println("Khách hàng không hợp lệ.");
            return List.of(); // Trả về danh sách rỗng
        }
        return invoiceRepository.getInvoiceByCustomer(customer);
    }

    public void deleteInvoice(String invoiceId) {
        invoiceRepository.deleteInvoice(invoiceId);
    }

    public void updateInvoice(Invoice invoice) {
        if (invoice != null) {
            invoiceRepository.updateInvoice(invoice);
        } else {
            System.out.println("Hóa đơn không h��p lệ.");
        }
    }
}