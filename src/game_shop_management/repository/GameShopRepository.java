package game_shop_management.repository;

import game_shop_management.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GameShopRepository {
    private List<Product> products;

    public GameShopRepository() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Trả về bản sao của danh sách
    }

    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; // Không tìm thấy
    }

    public void updateProduct(Product product) {
        // Cập nhật logic ở đây nếu cần
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }
}