package game_shop_management.controller;

import game_shop_management.model.Products;
import game_shop_management.service.products.IProductsService;
import game_shop_management.service.products.ProductsService;

import java.util.List;
import java.util.Optional;

public class ProductsController {
    private IProductsService productService;

    public ProductsController() {
        this.productService = new ProductsService();
    }

    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    public void addProduct(Products product) {
        productService.addProduct(product);
    }

    public void updateProduct(String id, Products updatedProduct) { // Đổi kiểu ID sang String
        productService.updateProduct(id, updatedProduct);
    }

    public void deleteProduct(String id) { // Đổi kiểu ID sang String
        productService.deleteProduct(id);
    }

    public Optional<Products> findProductById(String id) { // Đổi kiểu ID sang String
        return productService.findProductById(id);
    }

    public List<Products> findProductsByName(String name) {
        return productService.findProductsByName(name);
    }

    public List<Products> filterProductsByPrice(double minPrice, double maxPrice) {
        return productService.filterProductsByPrice(minPrice, maxPrice);
    }

    public void saveAllProducts() {
        productService.saveAllProducts();
    }
    public void saveIndividualFiles() {
        productService.saveIndividualFiles();
    }
}