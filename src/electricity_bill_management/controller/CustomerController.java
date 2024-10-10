package electricity_bill_management.controller;

import electricity_bill_management.model.Customers;
import electricity_bill_management.repository.CustomerRepository;

import java.util.List;

public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customers customer) {
        customerRepository.saveCustomer(customer);
    }

    public List<Customers> getAllCustomers() {
        return customerRepository.loadCustomers();
    }

    public List<Customers> searchCustomersByName(String name) {
        List<Customers> allCustomers = customerRepository.loadCustomers();
        String lowerCaseName = name.toLowerCase();
        return allCustomers.stream()
                .filter(customer -> customer.getName().toLowerCase().contains(lowerCaseName))
                .toList();
    }
}

