package game_shop_management;

import game_shop_management.view.LoginView;
import game_shop_management.view.InvoicesView;
import game_shop_management.view.ProductsView;
import game_shop_management.view.CustomerView;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        LoginView loginView = new LoginView();
        loginView.displayLoginOrRegister(); // Gọi màn hình đăng nhập hoặc đăng ký

        // Hiển thị menu quản lý sau khi đăng nhập thành công
        while (true) {
            System.out.println("=== Quản Lý Game Shop ===");
            System.out.println("1. Quản lý Hóa Đơn");
            System.out.println("2. Quản lý Sản Phẩm");
            System.out.println("3. Quản lý Khách Hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            switch (choice) {
                case 1:
                    manageInvoices();
                    break;
                case 2:
                    manageProducts();
                    break;
                case 3:
                    manageCustomers();
                    break;
                case 0:
                    System.out.println("Đã thoát khỏi chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void manageInvoices() {
        InvoicesView invoicesView = new InvoicesView();
        invoicesView.manageInvoices();
    }

    private void manageProducts() {
        ProductsView productView = new ProductsView();
        productView.manageProducts();
    }

    private void manageCustomers() {
        CustomerView customerView = new CustomerView();
        customerView.manageCustomers();
    }
}