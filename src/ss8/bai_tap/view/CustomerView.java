package ss8.bai_tap.view;

import ss8.bai_tap.model.Customer;

import java.util.List;

public class CustomerView {

    public void showCustomerDetails(List<Customer> customers) {
        String header = "+-----------------+-----------------+-----------------+-----------------+";
        String title = "|       ID        |      Name       |     Address     |      Phone      |";

        System.out.println(header);
        System.out.println(title);
        System.out.println(header);

        for (Customer customer : customers) {
            System.out.printf("| %-15s | %-15s | %-15s | %-15s |%n",
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPhone());
            System.out.println(header);
        }
    }
}