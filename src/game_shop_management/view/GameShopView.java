package game_shop_management.view;

import game_shop_management.controller.GameShopController;
import game_shop_management.model.GameDisc;
import game_shop_management.model.Product;

import java.util.List;
import java.util.Scanner;

public class GameShopView {
    private GameShopController controller;
    private Scanner scanner;

    public GameShopView(GameShopController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n--- Quản lý Cửa hàng Game ---");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị tất cả sản phẩm");
            System.out.println("3. Tìm sản phẩm theo tên");
            System.out.println("4. Cập nhật sản phẩm");
            System.out.println("5. Xóa sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    findProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
        scanner.close();
    }

    private void addProduct() {
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Đọc dòng trống
        System.out.print("Nhập nhà sản xuất: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Nhập nền tảng: ");
        String platform = scanner.nextLine();
        System.out.print("Nhập thể loại (nếu là GameDisc): ");
        String genre = scanner.nextLine();

        GameDisc newDisc = new GameDisc(name, price, manufacturer, platform, genre);
        controller.addProduct(newDisc);
    }

    private void displayAllProducts() {
        System.out.println("Danh sách sản phẩm:");
        List<Product> products = controller.getAllProducts();
        products.forEach(System.out::println);
    }

    private void findProduct() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String searchName = scanner.nextLine();
        Product foundProduct = controller.findProductByName(searchName);
        if (foundProduct != null) {
            System.out.println("Sản phẩm tìm thấy: " + foundProduct);
        } else {
            System.out.println("Không tìm thấy sản phẩm.");
        }
    }

    private void updateProduct() {
        System.out.print("Nhập tên sản phẩm cần cập nhật: ");
        String updateName = scanner.nextLine();
        Product productToUpdate = controller.findProductByName(updateName);
        if (productToUpdate != null) {
            System.out.print("Nhập giá mới: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Đọc dòng trống
            productToUpdate.setPrice(newPrice);
            controller.updateProduct(productToUpdate);
        } else {
            System.out.println("Sản phẩm không tìm thấy để cập nhật.");
        }
    }

    private void deleteProduct() {
        System.out.print("Nhập tên sản phẩm cần xóa: ");
        String deleteName = scanner.nextLine();
        Product productToDelete = controller.findProductByName(deleteName);
        if (productToDelete != null) {
            controller.deleteProduct(productToDelete);
        } else {
            System.out.println("Sản phẩm không tìm thấy để xóa.");
        }
    }
}