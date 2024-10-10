package game_shop_management.controller;

import game_shop_management.model.Customers;
import game_shop_management.service.customers.ICustomersService;
import game_shop_management.service.customers.CustomersService;

import java.util.List;
import java.util.Optional;

public class CustomersController  {
    private final ICustomersService customersService;

    public CustomersController() {
        this.customersService = new CustomersService();
    }

    public List<Customers> getAllCustomers() {
        return customersService.getAllCustomers();
    }

    public void addCustomer(Customers customer) {
        customersService.addCustomer(customer);
    }

    public void updateCustomer(String customerId, Customers updatedCustomer) {
        customersService.updateCustomer(customerId, updatedCustomer);
    }

    public void deleteCustomer(String customerId) {
        customersService.deleteCustomer(customerId);
    }

    public Optional<Customers> findCustomerById(String customerId) {
        return customersService.findCustomerById(customerId);
    }
}