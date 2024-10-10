package game_shop_management.service.customers;

import game_shop_management.model.Customers;
import game_shop_management.repository.customers.CustomersRepository;
import game_shop_management.repository.customers.ICustomersRepository;
import game_shop_management.util.EmailUtils;
import game_shop_management.validate.InvalidEmailException;

import java.util.List;
import java.util.Optional;

public class CustomersService implements ICustomersService {
    private final ICustomersRepository customersRepository;

    public CustomersService() {
        this.customersRepository = new CustomersRepository();
    }

    @Override
    public List<Customers> getAllCustomers() {
        return customersRepository.getAllCustomers();
    }

    @Override
    public void addCustomer(Customers customer) {
        try {
            if (validateCustomer(customer) && EmailUtils.isValidEmail(customer.getEmailAddress())) {
                customersRepository.saveCustomer(customer);
                System.out.println("Khách hàng đã được thêm thành công.");
            }
        } catch (InvalidEmailException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi không xác định: " + e.getMessage());
        }
    }

    @Override
    public void updateCustomer(String customerId, Customers updatedCustomer) {
        if (findCustomerById(customerId).isPresent() && validateCustomer(updatedCustomer)) {
            customersRepository.deleteCustomer(customerId); // Xóa khách hàng cũ
            customersRepository.saveCustomer(updatedCustomer); // Lưu khách hàng mới
            System.out.println("Khách hàng đã được cập nhật thành công.");
        } else {
            System.out.println("Khách hàng không tồn tại hoặc dữ liệu không hợp lệ!");
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        if (findCustomerById(customerId).isPresent()) {
            customersRepository.deleteCustomer(customerId);
            System.out.println("Khách hàng đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy khách hàng với ID: " + customerId);
        }
    }

    @Override
    public Optional<Customers> findCustomerById(String customerId) {
        return Optional.ofNullable(customersRepository.findCustomerById(customerId));
    }

    // Xác thực dữ liệu khách hàng
    private boolean validateCustomer(Customers customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) return false;
        if (customer.getEmailAddress() == null || !customer.getEmailAddress().contains("@")) return false;
        return customer.getPhoneNumber() != null && !customer.getPhoneNumber().isEmpty();
    }
}