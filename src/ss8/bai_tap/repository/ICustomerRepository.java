package ss8.bai_tap.repository;

import ss8.bai_tap.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    void delete(int id);

    void update(Customer customer);

    void add(Customer customer);

    List<Customer> findAll();

    Customer findById(int id);

}
