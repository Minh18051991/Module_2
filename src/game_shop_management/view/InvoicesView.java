package game_shop_management.view;

import game_shop_management.controller.CustomersController;
import game_shop_management.controller.InvoiceController;
import game_shop_management.model.Customers;
import game_shop_management.model.Invoice;
import game_shop_management.model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InvoicesView {
    private final InvoiceController invoiceController;
    private final Scanner scanner;
    private final CustomersController customersController ;

    public InvoicesView() {
        this.invoiceController = new InvoiceController();
        this.scanner = new Scanner(System.in);
        this.customersController = new CustomersController();
    }

    public void manageInvoices() {
        while (true) {
            System.out.println("=== Quản Lý Hóa Đơn ===");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Cập nhật hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Tìm kiếm hóa đơn theo ID");
            System.out.println("5. Hiển thị tất cả hóa đơn");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            switch (choice) {
                case 1:
                    addInvoice();
                    break;
                case 2:
                    updateInvoice();
                    break;
                case 3:
                    deleteInvoice();
                    break;
                case 4:
                    searchInvoice();
                    break;
                case 5:
                    displayAllInvoices();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void addInvoice() {
        System.out.print("Nhập ID hóa đơn: ");
        String id = scanner.nextLine();
        System.out.print("Nhập ngày hóa đơn (dd/MM/yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Nhập ID khách hàng: ");
        String customerId = scanner.nextLine();

        // Tìm kiếm khách hàng dựa trên ID

        Optional<Customers> customerOpt = customersController.findCustomerById(customerId);
        if (customerOpt.isPresent()) {
            Customers customer = customerOpt.get();
            // Chưa có sản phẩm, bạn có thể tạo một danh sách sản phẩm rỗng hoặc thêm sản phẩm sau
            List<Products> products = new ArrayList<>(); // Hoặc lấy danh sách sản phẩm từ người dùng
            Invoice invoice = new Invoice(id, customer, date, products);
            invoiceController.addInvoice(invoice);
            System.out.println("Hóa đơn đã được thêm thành công.");
        } else {
            System.out.println("Không tìm thấy khách hàng với ID: " + customerId);
        }
    }

    private void updateInvoice() {
        System.out.print("Nhập ID hóa đơn cần cập nhật: ");
        String id = scanner.nextLine();
        Optional<Invoice> existingInvoice = invoiceController.findInvoiceById(id);

        if (existingInvoice.isPresent()) {
            System.out.print("Nhập ngày mới (hiện tại: " + existingInvoice.get().getInvoiceDate() + "): ");
            String newDate = scanner.nextLine();
            System.out.print("Nhập ID khách hàng mới (hiện tại: " + existingInvoice.get().getCustomer().getId() + "): ");
            String newCustomerId = scanner.nextLine();

            // Cập nhật hóa đơn với thông tin mới
            Invoice updatedInvoice = new Invoice(id, existingInvoice.get().getCustomer(), newDate, existingInvoice.get().getProducts());
            invoiceController.addInvoice(updatedInvoice); // Hoặc sử dụng một phương thức update nếu có
            System.out.println("Hóa đơn đã được cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với ID: " + id);
        }
    }

    private void deleteInvoice() {
        System.out.print("Nhập ID hóa đơn cần xóa: ");
        String id = scanner.nextLine();
        invoiceController.removeInvoice(id);
        System.out.println("Hóa đơn đã được xóa.");
    }

    private void searchInvoice() {
        System.out.print("Nhập ID hóa đơn cần tìm: ");
        String id = scanner.nextLine();
        Optional<Invoice> invoice = invoiceController.findInvoiceById(id);
        invoice.ifPresentOrElse(
                inv -> System.out.println("Hóa đơn tìm thấy: ID: " + inv.getInvoiceId() + ", Ngày: " + inv.getInvoiceDate() + ", ID Khách hàng: " + inv.getCustomer().getId()),
                () -> System.out.println("Không tìm thấy hóa đơn.")
        );
    }

    private void displayAllInvoices() {
        List<Invoice> invoices = invoiceController.getAllInvoices();
        if (invoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào.");
        } else {
            invoices.forEach(inv -> System.out.println("ID: " + inv.getInvoiceId() + ", Ngày: " + inv.getInvoiceDate() + ", ID Khách hàng: " + inv.getCustomer().getId()));
        }
    }
}