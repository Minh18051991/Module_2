package game_shop_management.model;

public class GameAccessories extends Product {
    private String accessoryType;

    public GameAccessories(String name, double price, boolean isRented, String manufacturer, String platform, String accessoryType, int quantity) {
        super(name, price, isRented, manufacturer, platform, quantity); // Sử dụng constructor chính xác
        this.accessoryType = accessoryType;
    }

    public String getAccessoryType() {
        return accessoryType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Loại phụ kiện: " + accessoryType;
    }
}