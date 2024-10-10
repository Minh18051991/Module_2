package game_shop_management.service.customers;

import game_shop_management.model.Customers;

import java.util.List;
import java.util.Optional;

public interface ICustomersService {
    List<Customers> getAllCustomers();
    void addCustomer(Customers customer);
    void updateCustomer(String customerId, Customers updatedCustomer);
    void deleteCustomer(String customerId);
    Optional<Customers> findCustomerById(String customerId);
}