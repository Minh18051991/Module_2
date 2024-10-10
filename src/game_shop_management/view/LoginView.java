package game_shop_management.view;

import game_shop_management.controller.AuthController;

import java.util.Scanner;

public class LoginView {
    private AuthController authController;
    private Scanner scanner;

    public LoginView() {
        authController = new AuthController();
        scanner = new Scanner(System.in);
    }

    public void displayLoginOrRegister() {
        while (true) {
            System.out.println("=== Đăng Nhập / Đăng Ký ===");
            System.out.println("1. Đăng Nhập");
            System.out.println("2. Đăng Ký");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng còn lại

            switch (choice) {
                case 1:
                    if (displayLogin()) {
                        return; // Đăng nhập thành công
                    }
                    break;
                case 2:
                    displayRegister();
                    break;
                case 0:
                    System.exit(0); // Thoát chương trình
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private boolean displayLogin() {
        System.out.println("=== Đăng Nhập ===");
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        if (authController.login(username, password)) {
            System.out.println("Đăng nhập thành công!");
            return true; // Đăng nhập thành công
        } else {
            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
            return false; // Đăng nhập không thành công
        }
    }

    private void displayRegister() {
        System.out.println("=== Đăng Ký ===");
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();

        if (authController.register(username, password, email)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký không thành công. Tên đăng nhập đã tồn tại.");
        }
    }
}