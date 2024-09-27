package game_shop_management.model;

public class GameAccessories extends Product {
    private String accessoryType;

    public GameAccessories(String name, double price, String manufacturer, String platform, String accessoryType) {
        super(name, price, manufacturer, platform);
        this.accessoryType = accessoryType;
    }

    public String getAccessoryType() {
        return accessoryType;
    }

    @Override
    public String toString() {
        return "GameAccessory: " + getName() + ", Giá: " + getPrice() +
                ", Nhà sản xuất: " + getManufacturer() + ", Nền tảng: " + getPlatform() +
                ", Loại phụ kiện: " + accessoryType;
    }
}
