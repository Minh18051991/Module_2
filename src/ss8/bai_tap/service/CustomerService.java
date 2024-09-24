package ss8.bai_tap.service;

import ss8.bai_tap.model.Customer;
import ss8.bai_tap.repository.CustomerRepository;

import java.util.Comparator;
import java.util.List;

public class CustomerService implements ICustomerService {
    private final CustomerRepository repository = new CustomerRepository();


    public void addCustomer(Customer customer) {
        repository.add(customer);
        sortCustomersByName();
    }

    public void updateCustomer(Customer customer) {
        repository.update(customer);
    }

    public void deleteCustomer(int id) {
        repository.delete(id);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(int id) {
        return repository.findById(id);
    }

    public void sortCustomersByName() {
        List<Customer> customers = repository.findAll();
        customers.sort(Comparator.comparing(Customer::getName));
    }
}