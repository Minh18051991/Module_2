package game_shop_management.model;

public class GameAccessories extends Product {
    private String accessoryType;

    public GameAccessories(String id, String name, double price, boolean isRented, String manufacturer, String platform, String accessoryType, int quantity) {
        super(id, name, price, isRented, manufacturer, platform, quantity, null, null, 0.0, 0, 0.0); // Gọi constructor của lớp cha
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