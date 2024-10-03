package game_shop_management.utils;

import game_shop_management.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {


    // Phương thức lưu sản phẩm vào file CSV
    public static void saveProductsToCSV(List<? extends Product> products, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                String line = productToCSV(product);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu sản phẩm vào file: " + e.getMessage());
        }
    }

    // Phương thức đọc sản phẩm từ file CSV
    public static List<Product> loadProductsFromCSV(String filePath) {
        List<Product> products = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Product product = csvToProduct(values);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc sản phẩm từ file: " + e.getMessage());
        }
        return products;
    }

    private static String productToCSV(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(product.getClass().getSimpleName()).append(",")
                .append(product.getId()).append(",")
                .append(product.getName()).append(",")
                .append(product.getPrice()).append(",")
                .append(product.isRented()).append(",")
                .append(product.getManufacturer()).append(",")
                .append(product instanceof GamingPC ? "Windows" : product.getPlatform()).append(",")
                .append(product instanceof GameDisc ? ((GameDisc) product).getGenre() : "")
                .append(",").append(product.getQuantity());

        // Nếu có các trường khác như rentDate, returnDate, revenue, hãy thêm vào đây
        if (product instanceof GameDisc) {
            sb.append(",").append(product.getRentDate()).append(",")
                    .append(product.getReturnDate()).append(",")
                    .append(product.getRevenue());
        }

        return sb.toString();
    }

    // Chuyển đổi chuỗi CSV thành đối tượng Product
    private static Product csvToProduct(String[] values) {
        String type = values[0];

        return switch (type) {
            case "GameDisc" -> new GameDisc(
                    values[1], // id
                    values[2], // name
                    Double.parseDouble(values[3]), // price
                    Boolean.parseBoolean(values[4]), // isRented
                    values[5], // manufacturer
                    values[6], // platform
                    values[7], // genre
                    Integer.parseInt(values[8]) // quantity
            );
            case "GameConsole" -> new GameConsole(
                    values[1], // id
                    values[2], // name
                    Double.parseDouble(values[3]), // price
                    Boolean.parseBoolean(values[4]), // isRented
                    values[5], // manufacturer
                    values[6], // platform
                    Integer.parseInt(values[7]), // quantity
                    values[8] // storage (dưới dạng String)
            );
            case "GameAccessories" -> new GameAccessories(
                    values[1], // id
                    values[2], // name
                    Double.parseDouble(values[3]), // price
                    Boolean.parseBoolean(values[4]), // isRented
                    values[5], // manufacturer
                    values[6], // platform
                    values[7], // accessoryType
                    Integer.parseInt(values[8]) // quantity
            );
            case "GamingPC" -> new GamingPC(
                    values[1], // id
                    values[2], // name
                    Double.parseDouble(values[3]), // price
                    Boolean.parseBoolean(values[4]), // isRented
                    values[5], // manufacturer
                    values[6], // gpu
                    values[7], // cpu
                    values[8], // ram
                    values[9], // storage (dưới dạng String)
                    "Windows", // Cố định nền tảng là Windows
                    Integer.parseInt(values[10]) // quantity
            );
            default -> null; // Không tìm thấy loại sản phẩm
        };
    }
}