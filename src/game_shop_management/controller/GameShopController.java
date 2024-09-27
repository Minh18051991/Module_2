package game_shop_management.controller;

import game_shop_management.model.Product;
import game_shop_management.service.GameShopService;

import java.util.List;

public class GameShopController {
    private GameShopService service;

    public GameShopController(GameShopService service) {
        this.service = service;
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        service.addProduct(product);
    }

    // Lấy tất cả sản phẩm
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
}