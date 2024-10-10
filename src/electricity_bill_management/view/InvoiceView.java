package electricity_bill_management.view;

import electricity_bill_management.model.Invoice;

import java.util.List;

public class InvoiceView {
    public void displayAllInvoices(List<Invoice> invoices) {
        if (invoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào.");
            return;
        }

        System.out.println("=== Danh sách hóa đơn ===");
        for (Invoice invoice : invoices) {
            System.out.printf("Mã hóa đơn: %s, Khách hàng: %s, Ngày: %s, Số lượng: %.2f, Đơn giá: %.2f, Thành tiền: %.2f%n",
                    invoice.getInvoiceId(),
                    invoice.getCustomer().getName(),
                    invoice.getInvoiceDate(),
                    invoice.getQuantity(),
                    invoice.getUnitPrice(),
                    invoice.calculateTotalAmount());
        }
    }

    public void displayInvoiceDetails(Invoice invoice) {
        System.out.printf("=== Chi tiết hóa đơn ===%n");
        System.out.printf("Mã hóa đơn: %s%n", invoice.getInvoiceId());
        System.out.printf("Khách hàng: %s%n", invoice.getCustomer().getName());
        System.out.printf("Ngày ra hóa đơn: %s%n", invoice.getInvoiceDate());
        System.out.printf("Số lượng tiêu thụ: %.2f KW%n", invoice.getQuantity());
        System.out.printf("Đơn giá: %.2f%n", invoice.getUnitPrice());
        System.out.printf("Thành tiền: %.2f%n", invoice.calculateTotalAmount());
    }
}