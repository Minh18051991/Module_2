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
                    addProduct(sc, productManager);
                    break;

                case 2:
                    removeProduct(sc, productManager);
                    break;

                case 3:
                    updateProduct(sc, productManager);
                    break;

                case 4:
                    updateProduct(productManager);
                    break;

                case 5:
                    sortProduct(productManager);
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

    private static void sortProduct(ProductManager productManager) {
        List<Product> sortedProducts = productManager.getAllProducts();
        sortedProducts.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        System.out.println("List of products sorted by price:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    private static void updateProduct(ProductManager productManager) {
        List<Product> products = productManager.getAllProducts();
        System.out.println("List of products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void updateProduct(Scanner sc, ProductManager productManager) {
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
    }

    private static void removeProduct(Scanner sc, ProductManager productManager) {
        System.out.println("List of products:");
        for (Product product : productManager.getAllProducts()) {
            System.out.println(product);
        }
        System.out.print("Enter product ID to remove: ");
        String idToRemove = sc.next();
        System.out.println("1. Yes");
        System.out.println("2. No (Cancel)");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            productManager.removeProduct(idToRemove);
            System.out.println("Removed product successfully!");
        }else {
            System.out.println("Removal cancelled.");
        }
    }

    private static void addProduct(Scanner sc, ProductManager productManager) {
        System.out.print("Enter product ID: ");
        String id = sc.next();
        System.out.print("Enter product name: ");
        String name = sc.next();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        productManager.addProduct(new Product(id, name, price));
        System.out.println("Added product successfully!");
    }
}