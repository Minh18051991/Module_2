package game_shop_management.repository.products;

import game_shop_management.model.Products;

import java.util.List;

public interface IProductsRepository {
    List<Products> getProducts();
    void addProduct(Products product);
    void updateProduct(String id, Products updatedProduct);
    void deleteProduct(String id);
    void saveAllProducts();
    void saveIndividualFiles();

}