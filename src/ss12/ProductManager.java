package ss12;

import java.util.LinkedList;
import java.util.List;

public class ProductManager {
   List<Product> products = new LinkedList<>();
   {
        addProduct(new Product("P001", "Laptop", 10000));
        addProduct(new Product("P002", "Smartphone", 50000));
        addProduct(new Product("P003", "Tablet", 3000));
    }


    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }
    public Product findProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    public List<Product> getAllProducts() {
        return products;
    }
    public void updateProduct(Product product) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                index = i;
                break;
            }
        }
        if (index!= -1) {
            products.set(index, product);
        }
    }
}
