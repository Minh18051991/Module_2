package game_shop_management.view;

import game_shop_management.controller.CustomersController;
import game_shop_management.controller.InvoiceController;
import game_shop_management.model.Customers;
import game_shop_management.model.Invoice;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerView {
    private CustomersController customersController;
    private InvoiceController invoiceController;
    private Scanner scanner;

    public CustomerView() {
        customersController = new CustomersController();
        invoiceController = new InvoiceController();
        scanner = new Scanner(System.in);
    }

    public void manageCustomers() {
        while (true) {
            System.out.println("=== Quản Lý Khách Hàng ===");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Cập nhật khách hàng");
            System.out.println("3. Xóa khách hàng");
            System.out.println("4. Tìm kiếm khách hàng theo tên");
            System.out.println("5. Hiển thị tất cả khách hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    searchCustomerByName();
                    break;
                case 5:
                    displayAllCustomers();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Nhập ID khách hàng: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();
        System.out.print("Nhập email khách hàng: ");
        String email = scanner.nextLine();
        System.out.print("Nhập số điện thoại khách hàng(Nhập theo kiểu XXX-XXXX-XXXX: ");
        String phone = scanner.nextLine();

        Customers customer = new Customers(id, name, email, phone);
        customersController.addCustomer(customer);
        System.out.println("Khách hàng đã được thêm thành công.");
    }

    private void updateCustomer() {
        System.out.print("Nhập tên khách hàng cần cập nhật: ");
        String nameQuery = scanner.nextLine().toLowerCase();

        List<Customers> matchingCustomers = customersController.getAllCustomers().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(nameQuery))
                .collect(Collectors.toList());

        if (matchingCustomers.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            matchingCustomers.forEach(c -> System.out.println("ID: " + c.getId() + ", Tên: " + c.getName()));
            System.out.print("Nhập ID khách hàng muốn cập nhật: ");
            String idToUpdate = scanner.nextLine();

            Optional<Customers> existingCustomer = matchingCustomers.stream()
                    .filter(c -> c.getId().equals(idToUpdate))
                    .findFirst();

            if (existingCustomer.isPresent()) {
                System.out.print("Nhập tên mới (hiện tại: " + existingCustomer.get().getName() + "): ");
                String name = scanner.nextLine();
                System.out.print("Nhập email mới (hiện tại: " + existingCustomer.get().getEmailAddress() + "): ");
                String email = scanner.nextLine();
                System.out.print("Nhập số điện thoại mới (hiện tại: " + existingCustomer.get().getPhoneNumber() + "): ");
                String phone = scanner.nextLine();

                Customers updatedCustomer = new Customers(idToUpdate, name, email, phone);
                customersController.updateCustomer(idToUpdate, updatedCustomer);
                System.out.println("Khách hàng đã được cập nhật thành công.");
            } else {
                System.out.println("Không tìm thấy khách hàng với ID: " + idToUpdate);
            }
        }
    }

    private void deleteCustomer() {
        System.out.print("Nhập tên khách hàng cần xóa: ");
        String nameQuery = scanner.nextLine().toLowerCase();

        List<Customers> matchingCustomers = customersController.getAllCustomers().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(nameQuery))
                .collect(Collectors.toList());

        if (matchingCustomers.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            matchingCustomers.forEach(c -> System.out.println("ID: " + c.getId() + ", Tên: " + c.getName()));
            System.out.print("Nhập ID khách hàng muốn xóa: ");
            String idToDelete = scanner.nextLine();

            Optional<Customers> customerToDelete = matchingCustomers.stream()
                    .filter(c -> c.getId().equals(idToDelete))
                    .findFirst();

            if (customerToDelete.isPresent()) {
                customersController.deleteCustomer(idToDelete);
                System.out.println("Khách hàng với ID " + idToDelete + " đã được xóa.");
            } else {
                System.out.println("Không tìm thấy khách hàng với ID: " + idToDelete);
            }
        }
    }

    private void searchCustomerByName() {
        System.out.print("Nhập tên khách hàng cần tìm: ");
        String nameQuery = scanner.nextLine().toLowerCase();

        List<Customers> matchingCustomers = customersController.getAllCustomers().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(nameQuery))
                .collect(Collectors.toList());

        if (matchingCustomers.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            matchingCustomers.forEach(c -> {
                System.out.println("ID: " + c.getId() + ", Tên: " + c.getName());
                // Hiển thị hóa đơn tương ứng với khách hàng
                List<Invoice> invoices = invoiceController.getInvoicesByCustomerId(c.getId());
                if (invoices.isEmpty()) {
                    System.out.println("Không có hóa đơn nào cho khách hàng này.");
                } else {
                    System.out.println("Hóa đơn:");
                    invoices.forEach(i -> System.out.println("  - ID: " + i.getInvoiceId() + ", Ngày: " + i.getInvoiceDate()));
                }
            });
        }
    }

    private void displayAllCustomers() {
        List<Customers> customers = customersController.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào.");
        } else {
            System.out.println("Danh sách khách hàng:");
            customers.forEach(c -> {
                System.out.println("ID: " + c.getId());
                System.out.println("Tên: " + c.getName());
                System.out.println("Email: " + c.getEmailAddress());
                System.out.println("Số điện thoại: " + c.getPhoneNumber());
                System.out.println("---------------------"); // Để dễ phân biệt giữa các khách hàng
            });
        }
    }
}