package game_shop_management.model;

public class GameConsole extends Product {
    private String platform;
    private String storage; // Dung lượng

public GameConsole(String name, double price, boolean isRented, String manufacturer, String platform, int quantity, String storage) {
    super(name, price, isRented, manufacturer, platform, quantity);
    this.platform = platform;
    this.storage = storage;
}

    public String getPlatform() {
        return platform;
    }

    public String getStorage() {
        return storage;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nền tảng: " + platform + ", Dung lượng: " + storage;
    }
}