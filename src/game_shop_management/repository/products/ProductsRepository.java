package game_shop_management.repository.products;

import game_shop_management.model.Products;
import game_shop_management.model.GamingPC;
import game_shop_management.model.GamingConsole;
import game_shop_management.model.GamingDisc;
import game_shop_management.model.GamingAccessories;
import game_shop_management.util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepository implements IProductsRepository {
    private static final String ALL_PRODUCTS_FILE = "src/game_shop_management/data/AllProducts.csv";
    private static final String GAMING_PC_FILE = "src/game_shop_management/data/GamingPC.csv";
    private static final String GAMING_CONSOLE_FILE = "src/game_shop_management/data/GamingConsole.csv";
    private static final String GAMING_DISC_FILE = "src/game_shop_management/data/GamingDisc.csv";
    private static final String GAMING_ACCESSORIES_FILE = "src/game_shop_management/data/GamingAccessories.csv";

    private final List<Products> products;

    public ProductsRepository() {
        this.products = loadAllProducts();
    }

    // Lưu tất cả sản phẩm vào file
    public void saveAllProducts() {
        FileHandler.saveToFile(products, ALL_PRODUCTS_FILE, "id,name,price,manufacturer,platform,stock,type,CPU,GPU,RAM,storage,storageCapacity,genre");
    }

    // Đọc tất cả sản phẩm từ file
    private List<Products> loadAllProducts() {
        List<Products> allProducts = new ArrayList<>();
        allProducts.addAll(loadGamingPCs());
        allProducts.addAll(loadGamingConsoles());
        allProducts.addAll(loadGamingDiscs());
        allProducts.addAll(loadGamingAccessories());
        return allProducts;
    }

    private List<Products> loadGamingPCs() {
        return FileHandler.loadFromFile(GAMING_PC_FILE, line -> {
            String[] values = line.split(",");
            if (values.length < 11) {
                throw new IllegalArgumentException("Dữ liệu không đầy đủ cho GamingPC: " + line);
            }
            return new GamingPC(values[0], values[1], parseDouble(values[2]), values[3], values[4], parseInt(values[5]), values[6], values[7], values[8], values[9], values[10]); // Thêm values[10] cho type
        });
    }

    private List<Products> loadGamingConsoles() {
        return FileHandler.loadFromFile(GAMING_CONSOLE_FILE, line -> {
            String[] values = line.split(",");
            if (values.length < 8) {
                throw new IllegalArgumentException("Dữ liệu không đầy đủ cho GamingConsole: " + line);
            }
            return new GamingConsole(values[0], values[1], parseDouble(values[2]), values[3], values[4], parseInt(values[5]), values[6], values[7]); // values[7] cho storageCapacity
        });
    }

    private List<Products> loadGamingDiscs() {
        return FileHandler.loadFromFile(GAMING_DISC_FILE, line -> {
            String[] values = line.split(",");
            if (values.length < 8) {
                throw new IllegalArgumentException("Dữ liệu không đầy đủ cho GamingDisc: " + line);
            }
            return new GamingDisc(values[0], values[1], parseDouble(values[2]), values[3], values[4], parseInt(values[5]), values[6], values[7]); // values[7] cho genre
        });
    }

    private List<Products> loadGamingAccessories() {
        return FileHandler.loadFromFile(GAMING_ACCESSORIES_FILE, line -> {
            String[] values = line.split(",");
            if (values.length < 8) {
                throw new IllegalArgumentException("Dữ liệu không đầy đủ cho GamingAccessories: " + line);
            }
            return new GamingAccessories(values[0], values[1], parseDouble(values[2]), values[3], values[4], parseInt(values[5]), values[6], values[7]);
        });
    }

    @Override
    public List<Products> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public void addProduct(Products product) {
        products.add(product);
        saveAllProducts();
        saveIndividualFiles();
    }

    // Ghi từng loại sản phẩm vào file riêng
    public void saveIndividualFiles() {
        List<Products> gamingPCs = new ArrayList<>();
        List<Products> gamingConsoles = new ArrayList<>();
        List<Products> gamingDiscs = new ArrayList<>();
        List<Products> gamingAccessories = new ArrayList<>();

        for (Products product : products) {
            if (product instanceof GamingPC) {
                gamingPCs.add(product);
            } else if (product instanceof GamingConsole) {
                gamingConsoles.add(product);
            } else if (product instanceof GamingDisc) {
                gamingDiscs.add(product);
            } else if (product instanceof GamingAccessories) {
                gamingAccessories.add(product);
            }
        }

        FileHandler.saveToFile(gamingPCs, GAMING_PC_FILE, "id,name,price,manufacturer,platform,stock,type,CPU,GPU,RAM,storage");
        FileHandler.saveToFile(gamingConsoles, GAMING_CONSOLE_FILE, "id,name,price,manufacturer,platform,stock,type,storageCapacity");
        FileHandler.saveToFile(gamingDiscs, GAMING_DISC_FILE, "id,name,price,manufacturer,platform,stock,type,genre");
        FileHandler.saveToFile(gamingAccessories, GAMING_ACCESSORIES_FILE, "id,name,price,manufacturer,platform,stock,type");
    }

    @Override
    public void updateProduct(String id, Products updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, updatedProduct);
                saveAllProducts();
                saveIndividualFiles();
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với ID: " + id);
    }

    public void deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                saveAllProducts();
                saveIndividualFiles();
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với ID: " + id);
    }

    // Phương thức hỗ trợ chuyển đổi chuỗi thành double
    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Giá không hợp lệ: " + value);
        }
    }

    // Phương thức hỗ trợ chuyển đổi chuỗi thành int
    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Số lượng không hợp lệ: " + value);
        }
    }
}