package ss8.bai_tap.service;

import ss8.bai_tap.model.Customer;

import java.util.List;

public interface ICustomerService {
    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);


    void sortCustomersByName();


    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();


}
