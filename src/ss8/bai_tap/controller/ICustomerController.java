package ss8.bai_tap.controller;

import ss8.bai_tap.model.Customer;

public interface ICustomerController {
    void addCustomer(Customer customer);

    void updateCustomer(int id, String name, String address, String phone);

    void deleteCustomer(int id);

    void displayCustomers();

    Customer getCustomerById(int id);
}