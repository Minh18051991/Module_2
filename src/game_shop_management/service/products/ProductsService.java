package game_shop_management.service.products;

import game_shop_management.validate.InvalidProductException; // Nhập lớp exception
import game_shop_management.model.Products;
import game_shop_management.repository.products.IProductsRepository;
import game_shop_management.repository.products.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsService implements IProductsService {
    private final IProductsRepository productRepository;

    public ProductsService() {
        this.productRepository = new ProductsRepository();
    }

    public List<Products> getAllProducts() {
        return productRepository.getProducts();
    }

    public void saveAllProducts() {
        productRepository.saveAllProducts();
    }

    public void saveIndividualFiles() {
        productRepository.saveIndividualFiles();
    }

    public void addProduct(Products product) {
        try {
            if (validateProduct(product)) {
                productRepository.addProduct(product);
                System.out.println("Sản phẩm đã được thêm thành công.");
            }
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void updateProduct(String id, Products updatedProduct) {
        try {
            if (findProductById(id).isPresent() && validateProduct(updatedProduct)) {
                productRepository.updateProduct(id, updatedProduct);
                System.out.println("Sản phẩm đã được cập nhật thành công.");
            } else {
                System.out.println("Sản phẩm không tồn tại!");
            }
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void deleteProduct(String id) {
        if (findProductById(id).isPresent()) {
            productRepository.deleteProduct(id);
            System.out.println("Sản phẩm đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    public Optional<Products> findProductById(String id) {
        return productRepository.getProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public List<Products> findProductsByName(String name) {
        List<Products> result = new ArrayList<>();
        for (Products product : productRepository.getProducts()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Products> filterProductsByPrice(double minPrice, double maxPrice) {
        List<Products> result = new ArrayList<>();
        for (Products product : productRepository.getProducts()) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }


    private boolean validateProduct(Products product) throws InvalidProductException {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new InvalidProductException("Tên sản phẩm không được để trống.");
        }
        if (product.getPrice() < 0) {
            throw new InvalidProductException("Giá sản phẩm không được âm.");
        }
        if (product.getStock() < 0) {
            throw new InvalidProductException("Số lượng tồn kho không được âm.");
        }
        return true; // Dữ liệu hợp lệ
    }
}