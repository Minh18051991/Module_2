package game_shop_management.model;

public class GameConsole extends Product {
    public GameConsole(String name, double price, String manufacturer, String platform) {
        super(name, price, manufacturer, platform);
    }

    @Override
    public String toString() {
        return "GameConsole: " + getName() + ", Giá: " + getPrice() +
                ", Nhà sản xuất: " + getManufacturer() + ", Nền tảng: " + getPlatform();
    }
}