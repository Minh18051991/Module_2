package electricity_bill_management;

import electricity_bill_management.controller.CustomerController;
import electricity_bill_management.controller.InvoiceController;
import electricity_bill_management.model.Customers;
import electricity_bill_management.model.ForeignCustomers;
import electricity_bill_management.model.Invoice;
import electricity_bill_management.model.VNCustomers;
import electricity_bill_management.repository.CustomerRepository;
import electricity_bill_management.repository.InvoiceRepository;
import electricity_bill_management.view.CustomerView;
import electricity_bill_management.view.InvoiceView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo CustomerRepository
        CustomerRepository customerRepository = new CustomerRepository("src/electricity_bill_management/data/Customers.csv.csv");
        // Khởi tạo InvoiceRepository với CustomerRepository
        InvoiceRepository invoiceRepository = new InvoiceRepository("src/electricity_bill_management/data/Invoice.csv", customerRepository);

        CustomerController customerController = new CustomerController(customerRepository);
        InvoiceController invoiceController = new InvoiceController(invoiceRepository);

        CustomerView customerView = new CustomerView();
        InvoiceView invoiceView = new InvoiceView();

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int choice;

        do {
            System.out.println("=== Menu Quản Lý Hóa Đơn ===");
            System.out.println("1. Thêm mới khách hàng");
            System.out.println("2. Hiện thị thông tin khách hàng");
            System.out.println("3. Tìm kiếm khách hàng");
            System.out.println("4. Thêm mới hóa đơn");
            System.out.println("5. Chỉnh sửa hóa đơn");
            System.out.println("6. Hiện thị chi tiết hóa đơn");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewCustomer(scanner, customerController);
                    break;

                case 2:
                    customerDisplay(customerController, customerView);
                    break;

                case 3:
                    searchCustomer(customerController, invoiceController, invoiceView, scanner);
                    break;

                case 4:
                    creatNewInvoice(customerController, scanner, dateFormat, invoiceController);
                    continue; // Quay lại vòng lặp để nhập thông tin lại

                case 5:
                    updateInvoice(invoiceController, customerController, invoiceView, scanner, dateFormat);
                    break; // Kết thúc case nếu không có hóa đơn

                case 6:
                    invoiceDisplay(customerController, invoiceController, invoiceView);
                    break;

                case 7:
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void addNewCustomer(Scanner scanner, CustomerController customerController) {
        System.out.println("Chọn loại khách hàng:");
        System.out.println("1. Khách hàng Việt Nam");
        System.out.println("2. Khách hàng nước ngoài");
        int customerType = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng còn lại

        String id;
        if (customerType == 1) {
            id = generateCustomerId("VN", customerController.getAllCustomers());
            System.out.println("Mã khách hàng: " + id); // Hiển thị mã khách hàng
        } else {
            id = generateCustomerId("NN", customerController.getAllCustomers());
            System.out.println("Mã khách hàng: " + id); // Hiển thị mã khách hàng
        }

        System.out.print("Họ tên: ");
        String name = scanner.nextLine();

        if (customerType == 1) {
            List<String> customerTypes = readCustomerTypesFromFile();
            System.out.println("Danh sách loại khách hàng:");
            for (int i = 0; i < customerTypes.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, customerTypes.get(i));
            }

            System.out.print("Chọn loại khách hàng (nhập số thứ tự): ");
            int selectedTypeIndex = scanner.nextInt() - 1;
            scanner.nextLine();

            if (selectedTypeIndex < 0 || selectedTypeIndex >= customerTypes.size()) {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }
            String selectedType = customerTypes.get(selectedTypeIndex); // Lấy loại khách hàng được chọn

            System.out.print("Định mức tiêu thụ: ");
            double consumptionLimit = scanner.nextDouble();
            scanner.nextLine();

            VNCustomers vnCustomer = new VNCustomers(id, name, selectedType, consumptionLimit);
            customerController.addCustomer(vnCustomer);
        } else if (customerType == 2) {
            System.out.print("Quốc tịch: ");
            String nationality = scanner.nextLine();

            ForeignCustomers foreignCustomer = new ForeignCustomers(id, name, nationality);
            customerController.addCustomer(foreignCustomer);
        }
    }

    private static void customerDisplay(CustomerController customerController, CustomerView customerView) {
        List<Customers> customers = customerController.getAllCustomers();
        customerView.displayAllCustomers(customers);
    }

    private static void creatNewInvoice(CustomerController customerController, Scanner scanner, SimpleDateFormat dateFormat, InvoiceController invoiceController) {
        // Thêm mới hóa đơn
        List<Customers> allCustomers = customerController.getAllCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("Không có khách hàng nào để thêm hóa đơn.");
            return;
        }

        // Hiển thị danh sách khách hàng với mã và chỉ số
        System.out.println("Danh sách khách hàng:");
        for (int i = 0; i < allCustomers.size(); i++) {
            System.out.printf("%d. Mã: %s, Tên: %s\n", i + 1, allCustomers.get(i).getId(), allCustomers.get(i).getName());
        }

        System.out.print("Nhập mã hóa đơn: ");
        String invoiceId = scanner.nextLine();

        System.out.print("Ngày ra hóa đơn (dd/MM/yyyy) (nhấn Enter để sử dụng ngày hiện tại): ");
        String dateInput = scanner.nextLine();
        Date invoiceDate;
        if (dateInput.trim().isEmpty()) {
            invoiceDate = new Date();
        } else {
            try {
                invoiceDate = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Ngày không hợp lệ. Sử dụng ngày hiện tại.");
                invoiceDate = new Date(); // Sử dụng ngày hiện tại nếu ngày không hợp lệ
            }
        }

        System.out.print("Số lượng (KW tiêu thụ): ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Chọn khách hàng (nhập số thứ tự hoặc mã khách hàng): ");
        String customerInput = scanner.nextLine();

        // Kiểm tra nếu người dùng nhập chỉ số
        int customerIndex = -1;
        try {
            customerIndex = Integer.parseInt(customerInput) - 1; // Chuyển đổi chỉ số từ người dùng
            if (customerIndex < 0 || customerIndex >= allCustomers.size()) {
                System.out.println("Chỉ số khách hàng không hợp lệ. Vui lòng nhập lại.");
                return;
            }
        } catch (NumberFormatException e) {
            for (int i = 0; i < allCustomers.size(); i++) {
                if (allCustomers.get(i).getId().equals(customerInput)) {
                    customerIndex = i;
                    break;
                }
            }
            if (customerIndex == -1) {
                System.out.println("Mã khách hàng không hợp lệ. Vui lòng nhập lại.");
                return;
            }
        }

        // Lấy đơn giá từ khách hàng đã chọn
        double unitPrice;
        Customers selectedCustomer = allCustomers.get(customerIndex);

        // Kiểm tra nếu là VNCustomers
        if (selectedCustomer instanceof VNCustomers) {
            // Hỏi loại khách hàng
            System.out.println("Chọn loại khách hàng:");
            System.out.println("1. Khách hàng sinh hoạt");
            System.out.println("2. Khách hàng kinh doanh");
            System.out.println("3. Khách hàng sản xuất");
            int customerCategory = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            unitPrice = switch (customerCategory) {
                case 1 -> 1000;
                case 2 -> 1200;
                case 3 -> 800;
                default -> {
                    System.out.println("Lựa chọn không hợp lệ. Đặt đơn giá mặc định là 1000.");
                    yield 1000;
                }
            };
        } else {
            unitPrice = 1000; // Đơn giá cố định cho khách hàng nước ngoài
        }

        // Tạo đối tượng hóa đơn
        Invoice invoice = new Invoice(invoiceId, selectedCustomer, invoiceDate, quantity, unitPrice);
        invoiceController.addInvoice(invoice);

        // Tính toán và hiển thị thành tiền
        double totalAmount = invoice.calculateTotalAmount();
        System.out.printf("Hóa đơn đã được thêm thành công. Thành tiền: %.2f\n", totalAmount);
    }

    private static void updateInvoice(InvoiceController invoiceController, CustomerController customerController, InvoiceView invoiceView, Scanner scanner, SimpleDateFormat dateFormat) {
        // Chỉnh sửa hóa đơn
        List<Invoice> existingInvoices = invoiceController.getAllInvoices(customerController.getAllCustomers());
        if (existingInvoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào để chỉnh sửa.");
            return;
        }

        // Hiển thị danh sách hóa đơn hiện tại
        invoiceView.displayAllInvoices(existingInvoices);

        System.out.print("Nhập mã hóa đơn cần chỉnh sửa: ");
        String invoiceIdToEdit = scanner.nextLine();

        // Tìm hóa đơn theo mã
        Invoice invoiceToEdit = null;
        for (Invoice inv : existingInvoices) {
            if (inv.getInvoiceId().equals(invoiceIdToEdit)) {
                invoiceToEdit = inv;
                break;
            }
        }

        if (invoiceToEdit == null) {
            System.out.println("Không tìm thấy hóa đơn với mã đã nhập.");
            return;
        }


        System.out.println("Thông tin hóa đơn hiện tại:");
        System.out.println("Mã hóa đơn: " + invoiceToEdit.getInvoiceId());
        System.out.println("Khách hàng: " + invoiceToEdit.getCustomer().getName());
        System.out.println("Ngày ra hóa đơn: " + dateFormat.format(invoiceToEdit.getInvoiceDate()));
        System.out.println("Số lượng: " + invoiceToEdit.getQuantity());
        System.out.println("Đơn giá: " + invoiceToEdit.getUnitPrice());
        // Chỉnh sửa thông tin
        System.out.print("Nhập ngày ra hóa đơn mới (dd/MM/yyyy) hoặc để trống để giữ nguyên: ");
        String newDateInput = scanner.nextLine();
        if (!newDateInput.trim().isEmpty()) {
            try {
                Date newInvoiceDate = dateFormat.parse(newDateInput);
                invoiceToEdit.setInvoiceDate(newInvoiceDate);
            } catch (ParseException e) {
                System.out.println("Ngày không hợp lệ. Giữ nguyên ngày hiện tại.");
            }
        }

        System.out.print("Nhập số lượng mới (hoặc để trống để giữ nguyên): ");
        String newQuantityInput = scanner.nextLine();
        if (!newQuantityInput.trim().isEmpty()) {
            invoiceToEdit.setQuantity(Double.parseDouble(newQuantityInput));
        }

        System.out.print("Nhập đơn giá mới (hoặc để trống để giữ nguyên): ");
        String newUnitPriceInput = scanner.nextLine();
        if (!newUnitPriceInput.trim().isEmpty()) {
            invoiceToEdit.setUnitPrice(Double.parseDouble(newUnitPriceInput));
        }
        System.out.println("Hóa đơn đã được chỉnh sửa thành công.");
    }

    private static void invoiceDisplay(CustomerController customerController, InvoiceController invoiceController, InvoiceView invoiceView) {
        List<Customers> allCustomersForInvoices = customerController.getAllCustomers();
        if (allCustomersForInvoices.isEmpty()) {
            System.out.println("Không có khách hàng nào để hiển thị hóa đơn.");
            return;
        }

        // Lấy danh sách hóa đơn
        List<Invoice> invoices = invoiceController.getAllInvoices(allCustomersForInvoices);
        if (invoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào để hiển thị.");
        } else {
            invoiceView.displayAllInvoices(invoices);
        }
    }

    private static void searchCustomer(CustomerController customerController, InvoiceController invoiceController, InvoiceView invoiceView, Scanner scanner) {
        System.out.print("Nhập tên khách hàng cần tìm kiếm: ");
        String customerName = scanner.nextLine();

        List<Customers> matchingCustomers = customerController.searchCustomersByName(customerName);
        if (matchingCustomers.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào với tên bạn đã nhập.");
        } else {
            System.out.println("Danh sách khách hàng phù hợp với tên bạn đã nhập:");
            for (Customers customer : matchingCustomers) {
                System.out.println("Khách hàng: " + customer.getName());
                List<Invoice> invoices = invoiceController.getInvoiceByCustomer(customer);
                if (invoices.isEmpty()) {
                    System.out.println("Khách hàng không có hóa đơn nào.");
                } else {
                    System.out.println("Hóa đơn của khách hàng:");
                    for (int i = 0; i < invoices.size(); i++) {
                        System.out.printf("%d. ", i + 1);
                        invoiceView.displayInvoiceDetails(invoices.get(i));
                    }

                    // Cho phép chọn hóa đơn để chỉnh sửa hoặc xóa
                    System.out.print("Nhập số thứ tự hóa đơn muốn chỉnh sửa/Thanh toán (hoặc 0 để quay lại): ");
                    int invoiceChoice = scanner.nextInt();
                    scanner.nextLine(); // Đọc dòng còn lại

                    if (invoiceChoice > 0 && invoiceChoice <= invoices.size()) {
                        Invoice selectedInvoice = invoices.get(invoiceChoice - 1);

                        // Hiển thị các lựa chọn
                        System.out.println("Chọn hành động cho hóa đơn " + selectedInvoice.getInvoiceId() + ":");
                        System.out.println("1. Chỉnh sửa hóa đơn");
                        System.out.println("2. Thanh toán hóa đơn");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        int actionChoice = scanner.nextInt();
                        scanner.nextLine(); // Đọc dòng còn lại

                        switch (actionChoice) {
                            case 1:

                                System.out.print("Nhập số lượng mới: ");
                                double newQuantity = scanner.nextDouble();
                                scanner.nextLine();

                                System.out.print("Nhập đơn giá mới: ");
                                double newUnitPrice = scanner.nextDouble();
                                scanner.nextLine();

                                selectedInvoice.setQuantity(newQuantity);
                                selectedInvoice.setUnitPrice(newUnitPrice);
                                invoiceController.updateInvoice(selectedInvoice);
                                System.out.println("Hóa đơn đã được cập nhật thành công.");
                                break;
                            case 2:
                                deleteInvoice(invoiceController, scanner, selectedInvoice);
                                System.out.println("Hóa đơn đã được thanh toán");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                    } else if (invoiceChoice == 0) {
                        continue;
                    } else {
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    }
                }
                System.out.println();
            }
        }
    }

    private static String generateCustomerId(String customerType, List<Customers> existingCustomers) {
        int maxId = 0;
        String prefix = customerType.equals("VN") ? "KHVN-" : "KHNN-";

        for (Customers customer : existingCustomers) {
            if (customer.getId().startsWith(prefix)) {
                String numPart = customer.getId().substring(prefix.length());
                maxId = Math.max(maxId, Integer.parseInt(numPart));
            }
        }

        return prefix + String.format("%05d", maxId + 1); // Tăng giá trị lên 1 và định dạng thành 5 chữ số
    }

    private static List<String> readCustomerTypesFromFile() {
        List<String> customerTypes = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/electricity_bill_management/data/CustomerType.csv"));
            for (String line : lines) {
                customerTypes.add(line.trim()); // Thêm loại khách hàng vào danh sách
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return customerTypes;
    }

    private static void deleteInvoice(InvoiceController invoiceController, Scanner scanner, Invoice selectedInvoice) {
        System.out.print("Bạn có xác nhận thanh toán hóa đơn " + selectedInvoice.getInvoiceId() + " không?  ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        String confirmation = scanner.nextLine();

        switch (confirmation.toLowerCase()) {
            case "1":
                invoiceController.deleteInvoice(selectedInvoice.getInvoiceId());
                System.out.println("Hóa đơn " + selectedInvoice.getInvoiceId() + " đã được thanh toán thành công.");
                break;
            case "2":
                System.out.println("Đã hủy thanh toán hóa đơn.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập '1' hoặc '2'.");
                break;
        }
    }
}