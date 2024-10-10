package game_shop_management.repository.customers;

import game_shop_management.model.Customers;
import game_shop_management.util.EmailUtils;
import game_shop_management.util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomersRepository implements ICustomersRepository {
    private static final String CUSTOMER_FILE = "src/game_shop_management/data/Customers.csv";
    private List<Customers> customers;

    public CustomersRepository() {
        this.customers = loadCustomers();
    }

    @Override
    public void saveCustomer(Customers customer) {
        customers.add(customer);
        FileHandler.saveToFile(customers, CUSTOMER_FILE, "customerId,name,email,phone");
    }

    @Override
    public void deleteCustomer(String customerId) {
        customers.removeIf(customer -> customer.getId().equals(customerId));
        FileHandler.saveToFile(customers, CUSTOMER_FILE, "customerId,name,email,phone");
    }

    @Override
    public List<Customers> loadCustomers() {
        List<Customers> allCustomers = FileHandler.loadFromFile(CUSTOMER_FILE, line -> {
            String[] values = line.split(",");
            // Kiểm tra số lượng trường
            if (values.length < 4) {
                System.out.println("Dữ liệu không hợp lệ: " + line);
                return null; // Hoặc xử lý khác
            }

            String customerId = values[0];
            String name = values[1];
            String email = values[2];
            String phone = values[3];


            // Tạo đối tượng Customers và trả về
            return new Customers(customerId, name, email, phone);
        });

        return allCustomers;
    }

    @Override
    public Customers findCustomerById(String customerId) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customers> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}