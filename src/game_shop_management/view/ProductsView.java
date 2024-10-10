package game_shop_management.view;

import game_shop_management.controller.ProductsController;
import game_shop_management.model.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductsView {
    private final ProductsController productsController;
    private final Scanner scanner;

    public ProductsView() {
        this.productsController = new ProductsController();
        this.scanner = new Scanner(System.in);
    }

    public void manageProducts() {
        while (true) {
            System.out.println("=== Quản Lý Sản Phẩm ===");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Cập nhật sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Tìm kiếm sản phẩm theo ID");
            System.out.println("5. Hiển thị tất cả sản phẩm");
            System.out.println("6. Tìm kiếm sản phẩm theo tên");
            System.out.println("7. Lọc sản phẩm theo giá");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");

            int choice = -1;
            while (choice < 0) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 0) {
                        System.out.println("Vui lòng nhập số từ 0 đến 7.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                }
            }

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    searchProductById();
                    break;
                case 5:
                    displayAllProducts();
                    break;
                case 6:
                    searchProductsByName();
                    break;
                case 7:
                    filterProductsByPrice();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void addProduct() {
        System.out.println("=== Thêm Sản Phẩm ===");
        System.out.println("Chọn loại sản phẩm:");
        System.out.println("1. Gaming PC");
        System.out.println("2. Gaming Console");
        System.out.println("3. Gaming Disc");
        System.out.println("4. Gaming Accessories");
        System.out.print("Chọn loại sản phẩm (1-4): ");

        int productType = -1;
        while (productType < 1 || productType > 4) {
            try {
                productType = Integer.parseInt(scanner.nextLine());
                if (productType < 1 || productType > 4) {
                    System.out.println("Vui lòng nhập số từ 1 đến 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        String id, name, manufacturer, platform;
        double price = 0;
        int stock = 0;

        // Nhập ID sản phẩm
        do {
            System.out.print("Nhập ID sản phẩm: ");
            id = scanner.nextLine().trim();
        } while (id.isEmpty());

        // Nhập tên sản phẩm
        do {
            System.out.print("Nhập tên sản phẩm: ");
            name = scanner.nextLine().trim();
        } while (name.isEmpty());

        // Nhập giá sản phẩm
        while (price <= 0) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("Giá phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập số lượng tồn kho
        while (stock < 0) {
            try {
                System.out.print("Nhập số lượng tồn kho: ");
                stock = Integer.parseInt(scanner.nextLine());
                if (stock < 0) {
                    System.out.println("Số lượng tồn kho không thể âm.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập nhà sản xuất
        do {
            System.out.print("Nhập nhà sản xuất: ");
            manufacturer = scanner.nextLine().trim();
        } while (manufacturer.isEmpty());

        // Nhập nền tảng sản phẩm
        do {
            System.out.print("Nhập nền tảng sản phẩm: ");
            platform = scanner.nextLine().trim();
        } while (platform.isEmpty());

        Products product;

        switch (productType) {
            case 1: // Gaming PC
                System.out.print("Nhập loại sản phẩm: ");
                String pcType = scanner.nextLine().trim();
                System.out.print("Nhập CPU: ");
                String cpu = scanner.nextLine().trim();
                System.out.print("Nhập GPU: ");
                String gpu = scanner.nextLine().trim();
                System.out.print("Nhập RAM: ");
                String ram = scanner.nextLine().trim();
                System.out.print("Nhập dung lượng lưu trữ: ");
                String storage = scanner.nextLine().trim();
                product = new GamingPC(id, name, price, manufacturer, platform, stock, pcType, cpu, gpu, ram, storage);
                break;

            case 2: // Gaming Console
                System.out.print("Nhập loại sản phẩm: ");
                String consoleType = scanner.nextLine().trim();
                System.out.print("Nhập dung lượng lưu trữ: ");
                String storageCapacity = scanner.nextLine().trim();
                product = new GamingConsole(id, name, price, manufacturer, platform, stock, consoleType, storageCapacity);
                break;

            case 3: // Gaming Disc
                System.out.print("Nhập loại sản phẩm: ");
                String discType = scanner.nextLine().trim();
                System.out.print("Nhập thể loại: ");
                String genre = scanner.nextLine().trim();
                product = new GamingDisc(id, name, price, manufacturer, platform, stock, discType, genre);
                break;

            case 4: // Gaming Accessories
                System.out.print("Nhập loại sản phẩm: ");
                String accessoryType = scanner.nextLine().trim();
                System.out.print("Nhập mô tả: ");
                String description = scanner.nextLine().trim();
                product = new GamingAccessories(id, name, price, manufacturer, platform, stock, accessoryType, description);
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        productsController.addProduct(product);
        System.out.println("Sản phẩm đã được thêm thành công.");

        // Ghi dữ liệu vào file AllProducts và file của loại sản phẩm tương ứng
        productsController.saveAllProducts(); // Ghi vào file AllProducts
        productsController.saveIndividualFiles(); // Ghi vào file riêng của từng loại sản phẩm
    }

    private void updateProduct() {
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        String id = scanner.nextLine().trim();
        Optional<Products> existingProduct = productsController.findProductById(id);

        if (existingProduct.isPresent()) {
            System.out.print("Nhập tên mới (hiện tại: " + existingProduct.get().getName() + "): ");
            String newName = scanner.nextLine().trim();
            while (newName.isEmpty()) {
                System.out.print("Tên không thể bỏ trống. Vui lòng nhập lại: ");
                newName = scanner.nextLine().trim();
            }

            double newPrice = -1;
            while (newPrice <= 0) {
                try {
                    System.out.print("Nhập giá mới (hiện tại: " + existingProduct.get().getPrice() + "): ");
                    newPrice = Double.parseDouble(scanner.nextLine());
                    if (newPrice <= 0) {
                        System.out.println("Giá phải lớn hơn 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                }
            }

            int newStock = -1;
            while (newStock < 0) {
                try {
                    System.out.print("Nhập số lượng mới (hiện tại: " + existingProduct.get().getStock() + "): ");
                    newStock = Integer.parseInt(scanner.nextLine());
                    if (newStock < 0) {
                        System.out.println("Số lượng tồn kho không thể âm.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                }
            }

            Products updatedProduct = new Products(id, newName, newPrice, existingProduct.get().getManufacturer(), existingProduct.get().getPlatform(), newStock, existingProduct.get().getType());
            productsController.updateProduct(id, updatedProduct);
            System.out.println("Sản phẩm đã được cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    private void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String id = scanner.nextLine().trim();
        productsController.deleteProduct(id);
        System.out.println("Sản phẩm đã được xóa.");
    }

    private void searchProductById() {
        System.out.print("Nhập ID sản phẩm cần tìm: ");
        String id = scanner.nextLine().trim();
        Optional<Products> product = productsController.findProductById(id);
        product.ifPresentOrElse(
                prod -> System.out.println("Sản phẩm tìm thấy: ID: " + prod.getId() + ", Tên: " + prod.getName() + ", Giá: " + prod.getPrice() + ", Số lượng: " + prod.getStock()),
                () -> System.out.println("Không tìm thấy sản phẩm.")
        );
    }

    private void displayAllProducts() {
        List<Products> products = productsController.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }

        System.out.println("=== Lựa Chọn Hiển Thị Sản Phẩm ===");
        System.out.println("1. Hiển thị tất cả sản phẩm");
        System.out.println("2. Gaming PC");
        System.out.println("3. Gaming Console");
        System.out.println("4. Gaming Disc");
        System.out.println("5. Gaming Accessories");
        System.out.println("0. Quay lại");
        System.out.print("Chọn thể loại: ");

        int choice = -1;
        while (choice < 0) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 5) {
                    System.out.println("Vui lòng nhập số từ 0 đến 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        switch (choice) {
            case 1:
                displayAllProductDetails(products);
                break;
            case 2:
                displayProductsByType(products, "GamingPC");
                break;
            case 3:
                displayProductsByType(products, "GamingConsole");
                break;
            case 4:
                displayProductsByType(products, "GamingDisc");
                break;
            case 5:
                displayProductsByType(products, "GamingAccessories");
                break;
            case 0:
                return; // Quay lại menu chính
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
        }
    }

    private void displayAllProductDetails(List<Products> products) {
        System.out.println("=== Danh Sách Tất Cả Sản Phẩm ===");
        for (Products prod : products) {
            System.out.println("ID: " + prod.getId() + ", Tên: " + prod.getName() + ", Giá: " + prod.getPrice() + ", Số lượng: " + prod.getStock() + ", Nhà sản xuất: " + prod.getManufacturer() + ", Nền tảng: " + prod.getPlatform() + ", Loại: " + prod.getType());

            // Hiển thị thông tin chi tiết theo từng loại sản phẩm
            if (prod instanceof GamingPC pc) {
                System.out.println("  CPU: " + pc.getCPU() + ", GPU: " + pc.getGPU() + ", RAM: " + pc.getRAM() + ", Dung lượng lưu trữ: " + pc.getStorage());
            } else if (prod instanceof GamingConsole console) {
                System.out.println("  Dung lượng lưu trữ: " + console.getStorageCapacity());
            } else if (prod instanceof GamingDisc disc) {
                System.out.println("  Thể loại: " + disc.getGenre());
            } else if (prod instanceof GamingAccessories accessories) {
                System.out.println("  Mô tả: " + accessories.getDescripton());
            }
            System.out.println(); // Thêm dòng trống cho dễ đọc
        }
    }

    private void searchProductsByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Tên không thể bỏ trống. Vui lòng nhập lại: ");
            name = scanner.nextLine().trim();
        }

        List<Products> products = productsController.findProductsByName(name);
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào.");
        } else {
            products.forEach(prod -> System.out.println("ID: " + prod.getId() + ", Tên: " + prod.getName() + ", Giá: " + prod.getPrice() + ", Số lượng: " + prod.getStock()));
        }
    }

    private void filterProductsByPrice() {
        double minPrice = -1;
        double maxPrice = -1;

        while (minPrice < 0) {
            System.out.print("Nhập giá tối thiểu: ");
            try {
                minPrice = Double.parseDouble(scanner.nextLine());
                if (minPrice < 0) {
                    System.out.println("Giá tối thiểu không thể âm.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        while (maxPrice < minPrice) {
            System.out.print("Nhập giá tối đa: ");
            try {
                maxPrice = Double.parseDouble(scanner.nextLine());
                if (maxPrice < minPrice) {
                    System.out.println("Giá tối đa phải lớn hơn hoặc bằng giá tối thiểu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        List<Products> products = productsController.filterProductsByPrice(minPrice, maxPrice);
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong khoảng giá này.");
        } else {
            products.forEach(prod -> System.out.println("ID: " + prod.getId() + ", Tên: " + prod.getName() + ", Giá: " + prod.getPrice() + ", Số lượng: " + prod.getStock()));
        }
    }

    private void displayProductsByType(List<Products> products, String type) {
        boolean hasProducts = false;

        for (Products prod : products) {
            if ((type.equals("GamingPC") && prod instanceof GamingPC) ||
                    (type.equals("GamingConsole") && prod instanceof GamingConsole) ||
                    (type.equals("GamingDisc") && prod instanceof GamingDisc) ||
                    (type.equals("GamingAccessories") && prod instanceof GamingAccessories)) {
                System.out.println("ID: " + prod.getId() + ", Tên: " + prod.getName() + ", Giá: " + prod.getPrice() + ", Số lượng: " + prod.getStock() + ", Nhà sản xuất: " + prod.getManufacturer() + ", Nền tảng: " + prod.getPlatform());

                // Hiển thị thông tin chi tiết theo từng loại sản phẩm
                if (prod instanceof GamingPC pc) {
                    System.out.println("  CPU: " + pc.getCPU() + ", GPU: " + pc.getGPU() + ", RAM: " + pc.getRAM() + ", Dung lượng lưu trữ: " + pc.getStorage());
                } else if (prod instanceof GamingConsole console) {
                    System.out.println("  Dung lượng lưu trữ: " + console.getStorageCapacity());
                } else if (prod instanceof GamingDisc disc) {
                    System.out.println("  Thể loại: " + disc.getGenre());
                } else {
                    GamingAccessories accessories = (GamingAccessories) prod;
                    System.out.println("  Mô tả: " + accessories.getDescripton());
                }
                System.out.println(); // Thêm dòng trống cho dễ đọc
                hasProducts = true;
            }
        }

        if (!hasProducts) {
            System.out.println("Không có sản phẩm nào trong thể loại này.");
        }
    }
}