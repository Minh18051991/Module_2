package game_shop_management.service;

import game_shop_management.model.Product;
import game_shop_management.repository.GameShopRepository;

import java.util.List;

public class GameShopService {
    private GameShopRepository repository;

    public GameShopService(GameShopRepository repository) {
        this.repository = repository;
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        repository.addProduct(product);
        System.out.println("Sản phẩm đã được thêm: " + product.getName());
    }

    // Lấy tất cả sản phẩm
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
        System.out.println("Sản phẩm đã được cập nhật: " + product.getName());
    }

    // Xóa sản phẩm
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
        System.out.println("Sản phẩm đã được xóa: " + product.getName());
    }
}