package ss8.bai_tap.controller;

import ss8.bai_tap.model.Customer;
import ss8.bai_tap.service.CustomerService;
import ss8.bai_tap.view.CustomerView;

import java.util.List;

public class CustomerController implements ICustomerController {
    private final CustomerService service = new CustomerService();
    private final CustomerView view = new CustomerView();

    public void addCustomer(Customer customer) {
        service.addCustomer(customer);
    }

    public void updateCustomer(int id, String name, String address, String phone) {
        Customer customer = new Customer(id, name, address, phone);
        service.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        service.deleteCustomer(id);
    }

    public void displayCustomers() {
        List<Customer> customers = service.getAllCustomers();
        view.showCustomerDetails(customers);
    }

    public Customer getCustomerById(int id) {
        return service.getCustomerById(id);
    }
}