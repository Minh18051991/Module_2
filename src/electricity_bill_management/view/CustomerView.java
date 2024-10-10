package electricity_bill_management.view;

import electricity_bill_management.model.Customers;
import electricity_bill_management.model.ForeignCustomers;
import electricity_bill_management.model.VNCustomers;

import java.util.List;

public class CustomerView {
    public void displayAllCustomers(List<Customers> customers) {
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào để hiển thị.");
            return;
        }

        System.out.printf("%-15s %-30s %-20s\n", "Mã khách hàng", "Họ tên", "Loại khách hàng");
        System.out.println("-------------------------------------------------------------");
        for (Customers customer : customers) {
            String customerType = customer instanceof VNCustomers ? "Việt Nam" : "Nước ngoài";
            String additionalInfo = customerType.equals("Việt Nam")
                    ? "Loại: " + ((VNCustomers) customer).getCustomerType() +
                    ", Định mức tiêu thụ: " + ((VNCustomers) customer).getConsumptionLimit()
                    : "Quốc tịch: " + ((ForeignCustomers) customer).getNationality();

            System.out.printf("%-15s %-30s %-20s %s\n",
                    customer.getId(),
                    customer.getName(),
                    customerType,
                    additionalInfo);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}