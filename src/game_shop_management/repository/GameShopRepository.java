package game_shop_management.repository;

import game_shop_management.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GameShopRepository {
    private List<Product> products;

    public GameShopRepository() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Trả về một bản sao danh sách
    }

    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        System.out.println("Sản phẩm không tìm thấy: " + name);
        return null;
    }

    public void updateProduct(Product product) {
        int index = products.indexOf(product);
        if (index != -1) {
            products.set(index, product);
        } else {
            System.out.println("Sản phẩm không tìm thấy để cập nhật.");
        }
    }

    public void deleteProduct(Product product) {
        if (products.remove(product)) {
            System.out.println("Sản phẩm đã được xóa.");
        } else {
            System.out.println("Sản phẩm không tìm thấy để xóa.");
        }
    }
}