package ss8.bai_tap;

import ss8.bai_tap.controller.CustomerController;
import ss8.bai_tap.model.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.println("\n ++++++ QUẢN LÝ KHÁCH HÀNG ++++++");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Cập nhập khách hàng");
            System.out.println("3. Xoá Khách hàng ");
            System.out.println("4. Hiển thị danh sách khánh hàng");
            System.out.println("0. Thoát khỏi chương trình");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("nhập ID");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Nhập tên Khách hàng");
                        String name = sc.nextLine();
                        System.out.println(" Nhập số điện thoại khách hàng ");
                        String phone = sc.nextLine();
                        System.out.println(" Nhập địa chỉ khách hàng");
                        String address = sc.nextLine();
                        customerController.addCustomer(new Customer(id, name, phone, address));
                        System.out.println("Thêm Khách hàng thành công !!!!!");
                        break;
                    case 2:
                        System.out.println(" Điền id khách hàng cần thêm : ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Chọn phần muốn thay đổi");
                        Customer customerToUpdate = customerController.getCustomerById(updateId);
                        if (customerToUpdate != null) {
                            System.out.println("Thông tin khách hàng hiện tại : ");
                            System.out.println("Id : " + customerToUpdate.getId());
                            System.out.println("Name : " + customerToUpdate.getName());
                            System.out.println("Phone : " + customerToUpdate.getPhone());
                            System.out.println("Address : " + customerToUpdate.getAddress());
                            System.out.println("Các thuộc tính muốn thay đổi : ");
                            System.out.println("1. Tên ");
                            System.out.println("2. Số điện thoại ");
                            System.out.println("3. Địa chỉ ");
                            int propertyChoice = sc.nextInt();
                            sc.nextLine();
                            switch (propertyChoice) {
                                case 1:
                                    System.out.println("Nhập tên mới ");
                                    String newName = sc.nextLine();
                                    customerController.updateCustomer(updateId, newName, customerToUpdate.getAddress(), customerToUpdate.getPhone());
                                    System.out.println("Cập nhật thành công");
                                    break;
                                case 2:
                                    System.out.println("Nhập số điện thoại mới ");
                                    String newPhone = sc.nextLine();
                                    customerController.updateCustomer(updateId, customerToUpdate.getName(), newPhone, customerToUpdate.getAddress());
                                    System.out.println("Cập nhật thành công");
                                    break;
                                case 3:
                                    System.out.println("Nhập địa chỉ mới ");
                                    String newAddress = sc.nextLine();
                                    customerController.updateCustomer(updateId, customerToUpdate.getName(), customerToUpdate.getPhone(), newAddress);
                                    System.out.println("Cập nhật thành công");
                                    break;
                                default:
                                    System.out.println("chọn không hợp le");
                            }
                        } else {
                            System.out.println("không tìm thấy khách hàng với id : " + updateId);
                        }
                        break;

                    case 3:
                        System.out.println("Nhâp id khách hàng cần xoá : ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();
                        customerController.deleteCustomer(deleteId);
                        System.out.println("Xoá thành công !!!!!! ");
                        break;
                    case 4:
                        System.out.println("DANH SÁCH KHÁCH HÀNG : ");
                        customerController.displayCustomers();
                        break;
                    case 0:
                        System.out.println("THANK YOU !!!!!");
                        break;
                    default:
                        System.out.println("Không hợp lệ chọn các danh mục (0-4)");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Nhập không hợp lệ! Vui lòng nhập số.");
                sc.nextLine();
            }
        } while (choice != 0);
        sc.close();
    }
}
