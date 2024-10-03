package game_shop_management.view;

import game_shop_management.controller.GameShopController;
import game_shop_management.model.*;

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
            System.out.println("2. Hiển thị sản phẩm");
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
                    displayProducts();
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
        System.out.println("Chọn loại sản phẩm muốn thêm:");
        System.out.println("1. GameDisc");
        System.out.println("2. GameConsole");
        System.out.println("3. GameAccessories");
        System.out.println("4. GamingPC");
        System.out.print("Chọn chức năng: ");
        int productType = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống

        System.out.print("Nhập ID sản phẩm: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Đọc dòng trống
        System.out.print("Nhập nhà sản xuất: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống

        switch (productType) {
            case 1: // GameDisc
                System.out.print("Nhập thể loại: ");
                String genre = scanner.nextLine();
                GameDisc newDisc = new GameDisc(id, name, price, false, manufacturer, null, genre, quantity);
                controller.addProduct(newDisc);
                break;
            case 2: // GameConsole
                System.out.print("Nhập nền tảng: ");
                String platform = scanner.nextLine();
                System.out.print("Nhập dung lượng lưu trữ: ");
                String storage = scanner.nextLine();
                GameConsole newConsole = new GameConsole(id, name, price, false, manufacturer, platform, quantity, storage);
                controller.addProduct(newConsole);
                break;
            case 3: // GameAccessories
                System.out.print("Nhập loại phụ kiện: ");
                String accessoryType = scanner.nextLine();
                GameAccessories newAccessory = new GameAccessories(id, name, price, false, manufacturer, null, accessoryType, quantity);
                controller.addProduct(newAccessory);
                break;
            case 4: // GamingPC
                System.out.print("Nhập GPU: ");
                String gpu = scanner.nextLine();
                System.out.print("Nhập CPU: ");
                String cpu = scanner.nextLine();
                System.out.print("Nhập RAM: ");
                String ram = scanner.nextLine();
                System.out.print("Nhập dung lượng lưu trữ: ");
                String pcStorage = scanner.nextLine();
                String platformPC = "Windows"; // Cố định nền tảng là Windows
                GamingPC newPC = new GamingPC(id, name, price, false, manufacturer, gpu, cpu, ram, pcStorage, platformPC, quantity);
                controller.addProduct(newPC);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return; // Thoát nếu không hợp lệ
        }

        // Lưu sản phẩm vào file CSV tương ứng
        controller.saveProductsToCSV("path/to/your/file.csv"); // Đường dẫn tới file CSV nếu cần
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
            scanner.nextLine();
            productToUpdate.setPrice(newPrice);
            controller.updateProduct(productToUpdate);
            System.out.println("Cập nhật thành công.");
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
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Sản phẩm không tìm thấy để xóa.");
        }
    }

    private void displayProducts() {
        System.out.println("Chọn cách hiển thị sản phẩm:");
        System.out.println("1. Hiển thị tất cả sản phẩm");
        System.out.println("2. Hiển thị theo thể loại");
        System.out.print("Chọn chức năng: ");
        int displayChoice = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống

        switch (displayChoice) {
            case 1:
                displayAllProducts();
                break;
            case 2:
                displayProductsByType();
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
        }
    }

    private void displayProductsByType() {
        System.out.println("Chọn thể loại cần hiển thị:");
        System.out.println("1. GameDisc");
        System.out.println("2. GameConsole");
        System.out.println("3. GameAccessories");
        System.out.println("4. GamingPC");
        System.out.print("Chọn chức năng: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống
        String type;

        switch (choice) {
            case 1:
                type = "GameDisc";
                break;
            case 2:
                type = "GameConsole";
                break;
            case 3:
                type = "GameAccessories";
                break;
            case 4:
                type = "GamingPC";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                return; // Thoát khỏi phương thức nếu lựa chọn không hợp lệ
        }

        List<Product> productsByType = controller.getProductsByType(type);
        if (productsByType.isEmpty()) {
            System.out.println("Không có sản phẩm nào thuộc thể loại này.");
        } else {
            System.out.println("Danh sách sản phẩm thể loại " + type + ":");
            productsByType.forEach(System.out::println);
        }
    }
}