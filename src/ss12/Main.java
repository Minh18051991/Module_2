package ss12;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Product Management ===");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Update product");
            System.out.println("4. List all products");
            System.out.println("5. Sort product by Price");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    String id = sc.next();
                    System.out.print("Enter product name: ");
                    String name = sc.next();
                    System.out.print("Enter product price: ");
                    double price = sc.nextDouble();
                    productManager.addProduct(new Product(id, name, price));
                    System.out.println("Added product successfully!");
                    break;

                case 2:
                    System.out.print("Enter product ID to remove: ");
                    String idToRemove = sc.next();
                    productManager.removeProduct(idToRemove);
                    System.out.println("Removed product successfully!");
                    break;

                case 3:
                    System.out.print("Enter product ID to update: ");
                    String idToUpdate = sc.next();
                    Product productToUpdate = productManager.findProductById(idToUpdate);
                    if (productToUpdate != null) {
                        System.out.print("Enter new product name: ");
                        productToUpdate.setName(sc.next());
                        System.out.print("Enter new product price: ");
                        productToUpdate.setPrice(sc.nextDouble());
                        productManager.updateProduct(productToUpdate);
                        System.out.println("Updated product successfully!");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 4:
                    List<Product> products = productManager.getAllProducts();
                    System.out.println("List of products:");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 5:
                    List<Product> sortedProducts = productManager.getAllProducts();
                    sortedProducts.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                    System.out.println("List of products sorted by price:");
                    for (Product product : sortedProducts) {
                        System.out.println(product);
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}