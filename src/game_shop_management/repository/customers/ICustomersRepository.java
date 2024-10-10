package game_shop_management.repository.customers;

import game_shop_management.model.Customers;

import java.util.List;

public interface ICustomersRepository {
    void saveCustomer(Customers customer);
    void deleteCustomer(String customerId);
    List<Customers> loadCustomers();
    Customers findCustomerById(String customerId);
    List<Customers> getAllCustomers();
}