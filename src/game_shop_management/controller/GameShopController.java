package game_shop_management.controller;

import game_shop_management.model.Product;
import game_shop_management.service.GameShopService;

import java.util.List;

public class GameShopController {
    private GameShopService repository;

    public GameShopController(GameShopService repository) {
        this.repository = repository;
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        repository.addProduct(product);
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    // Tìm sản phẩm theo tên
    public Product findProductByName(String name) {
        return repository.findProductByName(name);
    }

    // Cập nhật sản phẩm
    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    // Xóa sản phẩm
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
    }
}