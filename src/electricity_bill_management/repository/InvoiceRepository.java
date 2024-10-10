package electricity_bill_management.repository;

import electricity_bill_management.model.Customers;
import electricity_bill_management.model.Invoice;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceRepository {
    private final String filePath;
    private final SimpleDateFormat dateFormat;
    private final CustomerRepository customerRepository;

    public InvoiceRepository(String filePath, CustomerRepository customerRepository) {
        this.filePath = filePath;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.customerRepository = customerRepository; // Khởi tạo CustomerRepository
    }

    // Đọc danh sách hóa đơn từ tệp
    public List<Invoice> loadInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        List<Customers> allCustomers = customerRepository.loadCustomers(); // Tải danh sách khách hàng

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Bỏ qua dòng đầu tiên
                    continue; // Nhảy qua dòng tiêu đề
                }

                String[] values = line.split(",");
                if (values.length == 6) {
                    String invoiceId = values[0];
                    String customerId = values[1];
                    Date invoiceDate = dateFormat.parse(values[2]); // Đảm bảo giá trị đúng định dạng
                    double quantity = Double.parseDouble(values[3]);
                    double unitPrice = Double.parseDouble(values[4]);
                    double amount = Double.parseDouble(values[5]); // Có thể sử dụng nếu cần

                    // Tìm khách hàng từ customerId
                    Customers customer = findCustomerById(allCustomers, customerId);
                    if (customer != null) {
                        Invoice invoice = new Invoice(invoiceId, customer, invoiceDate, quantity, unitPrice);
                        invoices.add(invoice);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace(); // In ra lỗi để biết nguyên nhân
        }
        return invoices;
    }

    // Lưu hóa đơn vào tệp
    public void saveInvoice(Invoice invoice) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format("%s,%s,%s,%.2f,%.2f,%.2f",
                    invoice.getInvoiceId(),
                    invoice.getCustomer().getId(),
                    dateFormat.format(invoice.getInvoiceDate()),
                    invoice.getQuantity(),
                    invoice.getUnitPrice(),
                    invoice.calculateTotalAmount()); // Ghi amount từ phương thức tính toán
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }

    // Tìm khách hàng theo ID
    private Customers findCustomerById(List<Customers> allCustomers, String customerId) {
        for (Customers customer : allCustomers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    // Lấy tất cả hóa đơn của một khách hàng
    public List<Invoice> getInvoiceByCustomer(Customers customer) {
        List<Invoice> customerInvoices = new ArrayList<>();
        List<Invoice> allInvoices = loadInvoices(); // Tải tất cả hóa đơn

        for (Invoice invoice : allInvoices) {
            if (invoice.getCustomer().equals(customer)) {
                customerInvoices.add(invoice);
            }
        }
        return customerInvoices; // Trả về danh sách hóa đơn của khách hàng
    }

    // Xóa hóa đơn theo ID
    public void deleteInvoice(String invoiceId) {
        List<Invoice> invoices = loadInvoices(); // Tải tất cả hóa đơn
        boolean removed = invoices.removeIf(invoice -> invoice.getInvoiceId().equals(invoiceId)); // Xóa hóa đơn theo ID

        if (removed) {
            saveInvoices(invoices); // Ghi lại danh sách hóa đơn đã cập nhật vào tệp
            System.out.println("Hóa đơn " + invoiceId + " đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy hóa đơn với ID: " + invoiceId);
        }
    }

    // Lưu tất cả hóa đơn trở lại tệp
    private void saveInvoices(List<Invoice> invoices) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) { // Ghi đè lên tệp
            // Ghi tiêu đề
            bw.write("InvoiceId,CustomerId,InvoiceDate,Quantity,UnitPrice,TotalAmount");
            bw.newLine();
            for (Invoice invoice : invoices) {
                String line = String.format("%s,%s,%s,%.2f,%.2f,%.2f",
                        invoice.getInvoiceId(),
                        invoice.getCustomer().getId(),
                        dateFormat.format(invoice.getInvoiceDate()),
                        invoice.getQuantity(),
                        invoice.getUnitPrice(),
                        invoice.calculateTotalAmount());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateInvoice(Invoice updatedInvoice) {
        List<Invoice> invoices = loadInvoices(); // Tải danh sách hóa đơn hiện có
        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getInvoiceId().equals(updatedInvoice.getInvoiceId())) {
                invoices.set(i, updatedInvoice); // Cập nhật hóa đơn
                break;
            }
        }
        saveInvoices(invoices); // Ghi lại danh sách hóa đơn đã cập nhật vào tệp
    }
}