package game_shop_management.repository;

import game_shop_management.model.GameDisc;
import game_shop_management.model.GameConsole;
import game_shop_management.model.GameAccessories;
import game_shop_management.model.GamingPC;
import game_shop_management.model.Product;
import game_shop_management.utils.SaveAndLoad;

import java.util.ArrayList;
import java.util.List;

public class GameShopRepository {

    private List<GameDisc> gameDiscs;
    private List<GameConsole> gameConsoles;
    private List<GameAccessories> gameAccessories;
    private List<GamingPC> gamingPCs;

    // Địa chỉ file CSV
    private static final String GAME_DISC_FILE = "src/game_shop_management/Data/GameDisc.csv";
    private static final String GAME_CONSOLE_FILE = "src/game_shop_management/Data/GameConsole.csv";
    private static final String GAME_ACCESSORIES_FILE = "src/game_shop_management/Data/GameAccessories.csv";
    private static final String GAMING_PC_FILE = "src/game_shop_management/Data/GamingPC.csv";
    private static final String ALL_PRODUCTS_FILE = "src/game_shop_management/Data/allProducts.csv";

    public GameShopRepository() {
        this.gameDiscs = new ArrayList<>();
        this.gameConsoles = new ArrayList<>();
        this.gameAccessories = new ArrayList<>();
        this.gamingPCs = new ArrayList<>();
        loadAllProducts(); // Tải dữ liệu khi khởi tạo
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        if (product instanceof GameDisc) {
            gameDiscs.add((GameDisc) product);
        } else if (product instanceof GameConsole) {
            gameConsoles.add((GameConsole) product);
        } else if (product instanceof GameAccessories) {
            gameAccessories.add((GameAccessories) product);
        } else if (product instanceof GamingPC) {
            gamingPCs.add((GamingPC) product);
        }
        saveAllProducts(); // Lưu dữ liệu sau khi thêm
    }

    // Cập nhật sản phẩm
    public void updateProduct(Product product) {
        if (product instanceof GameDisc) {
            for (int i = 0; i < gameDiscs.size(); i++) {
                if (gameDiscs.get(i).getId().equals(product.getId())) {
                    gameDiscs.set(i, (GameDisc) product);
                    saveAllProducts(); // Lưu dữ liệu sau khi cập nhật
                    return;
                }
            }
        } else if (product instanceof GameConsole) {
            for (int i = 0; i < gameConsoles.size(); i++) {
                if (gameConsoles.get(i).getId().equals(product.getId())) {
                    gameConsoles.set(i, (GameConsole) product);
                    saveAllProducts(); // Lưu dữ liệu sau khi cập nhật
                    return;
                }
            }
        } else if (product instanceof GameAccessories) {
            for (int i = 0; i < gameAccessories.size(); i++) {
                if (gameAccessories.get(i).getId().equals(product.getId())) {
                    gameAccessories.set(i, (GameAccessories) product);
                    saveAllProducts(); // Lưu dữ liệu sau khi cập nhật
                    return;
                }
            }
        } else if (product instanceof GamingPC) {
            for (int i = 0; i < gamingPCs.size(); i++) {
                if (gamingPCs.get(i).getId().equals(product.getId())) {
                    gamingPCs.set(i, (GamingPC) product);
                    saveAllProducts(); // Lưu dữ liệu sau khi cập nhật
                    return;
                }
            }
        }
    }

    // Xóa sản phẩm
    public void deleteProduct(Product product) {
        if (product instanceof GameDisc) {
            gameDiscs.remove(product);
        } else if (product instanceof GameConsole) {
            gameConsoles.remove(product);
        } else if (product instanceof GameAccessories) {
            gameAccessories.remove(product);
        } else if (product instanceof GamingPC) {
            gamingPCs.remove(product);
        }
        saveAllProducts(); // Lưu dữ liệu sau khi xóa
    }

    // Lưu sản phẩm vào file CSV cho từng loại
    public void saveAllProducts() {
        System.out.println("Lưu sản phẩm vào các file CSV...");
        System.out.println("Số lượng GameDisc: " + gameDiscs.size());
        System.out.println("Số lượng GameConsole: " + gameConsoles.size());
        System.out.println("Số lượng GameAccessories: " + gameAccessories.size());
        System.out.println("Số lượng GamingPC: " + gamingPCs.size());

        SaveAndLoad.saveProductsToCSV(gameDiscs, GAME_DISC_FILE);
        SaveAndLoad.saveProductsToCSV(gameConsoles, GAME_CONSOLE_FILE);
        SaveAndLoad.saveProductsToCSV(gameAccessories, GAME_ACCESSORIES_FILE);
        SaveAndLoad.saveProductsToCSV(gamingPCs, GAMING_PC_FILE);
        SaveAndLoad.saveProductsToCSV(getAllProducts(), ALL_PRODUCTS_FILE); // Lưu tất cả sản phẩm
    }

    // Tải sản phẩm từ file CSV cho từng loại
    public void loadAllProducts() {
        gameDiscs.clear(); // Xóa danh sách trước khi tải
        gameConsoles.clear();
        gameAccessories.clear();
        gamingPCs.clear();

        // Tải sản phẩm từ các file CSV và ép kiểu về loại cụ thể
        List<Product> loadedGameDiscs = SaveAndLoad.loadProductsFromCSV(GAME_DISC_FILE);
        for (Product product : loadedGameDiscs) {
            if (product instanceof GameDisc) {
                gameDiscs.add((GameDisc) product);
            }
        }

        List<Product> loadedGameConsoles = SaveAndLoad.loadProductsFromCSV(GAME_CONSOLE_FILE);
        for (Product product : loadedGameConsoles) {
            if (product instanceof GameConsole) {
                gameConsoles.add((GameConsole) product);
            }
        }

        List<Product> loadedGameAccessories = SaveAndLoad.loadProductsFromCSV(GAME_ACCESSORIES_FILE);
        for (Product product : loadedGameAccessories) {
            if (product instanceof GameAccessories) {
                gameAccessories.add((GameAccessories) product);
            }
        }

        List<Product> loadedGamingPCs = SaveAndLoad.loadProductsFromCSV(GAMING_PC_FILE);
        for (Product product : loadedGamingPCs) {
            if (product instanceof GamingPC) {
                gamingPCs.add((GamingPC) product);
            }
        }
    }

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(gameDiscs);
        allProducts.addAll(gameConsoles);
        allProducts.addAll(gameAccessories);
        allProducts.addAll(gamingPCs);
        return allProducts;
    }

    // Tìm sản phẩm theo tên
    public Product findProductByName(String name) {
        for (Product product : getAllProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; //
    }


    public List<Product> getProductsType(String type) {
        List<Product> productsByType = new ArrayList<>();
        switch (type) {
            case "GameDisc":
                productsByType.addAll(gameDiscs);
                break;
            case "GameConsole":
                productsByType.addAll(gameConsoles);
                break;
            case "GameAccessories":
                productsByType.addAll(gameAccessories);
                break;
            case "GamingPC":
                productsByType.addAll(gamingPCs);
                break;
            default:
                System.out.println("Loại sản phẩm không hợp lệ.");
        }
        return productsByType;
    }
}