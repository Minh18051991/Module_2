package game_shop_management.controller;

import game_shop_management.model.Product;
import game_shop_management.repository.GameShopRepository;
import game_shop_management.service.GameShopService;
import game_shop_management.utils.SaveAndLoad;

import java.util.List;

public class GameShopController {
    private GameShopService service;

    public GameShopController(GameShopService service) {
        this.service = service;
        // Tải sản phẩm từ file CSV khi khởi tạo
        service.loadAllProducts(); // Tải tất cả sản phẩm từ các file
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        service.addProduct(product);
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // Tìm sản phẩm theo tên
    public Product findProductByName(String name) {
        return service.findProductByName(name);
    }

    // Cập nhật sản phẩm
    public void updateProduct(Product product) {
        service.updateProduct(product);
    }

    // Xóa sản phẩm
    public void deleteProduct(Product product) {
        service.deleteProduct(product);
    }

    // Lấy sản phẩm theo loại
    public List<Product> getProductsByType(String type) {
        return service.getProductsByType(type);
    }

    // Lưu tất cả sản phẩm vào file
    public void saveAllProducts() {
        service.saveAllProducts(); // Lưu tất cả sản phẩm vào các file
        System.out.println("Tất cả sản phẩm đã được lưu vào file.");
    }

    // Lưu sản phẩm vào file CSV cụ thể
    public void saveProductsToCSV(String filePath) {
        List<Product> allProducts = service.getAllProducts(); // Gọi từ service
        SaveAndLoad.saveProductsToCSV(allProducts, filePath);
    }
}