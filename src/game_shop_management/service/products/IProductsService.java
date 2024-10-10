package game_shop_management.service.products;

import game_shop_management.model.Products;

import java.util.List;
import java.util.Optional;

public interface IProductsService {
    List<Products> getAllProducts();
    void addProduct(Products product);
    void updateProduct(String id, Products updatedProduct);
    void deleteProduct(String id);
    Optional<Products> findProductById(String id);
    List<Products> findProductsByName(String name);
    List<Products> filterProductsByPrice(double minPrice, double maxPrice);
    void saveAllProducts();
    void saveIndividualFiles();
}