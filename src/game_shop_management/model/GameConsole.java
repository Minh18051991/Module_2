package game_shop_management.model;

public class GameConsole extends Product {
    private int storageInGB; // Lưu dung lượng dưới dạng GB

    public GameConsole(String id, String name, double price, boolean isRented, String manufacturer, String platform, int quantity, String storage) {
        super(id, name, price, isRented, manufacturer, platform, quantity, null, null, 0.0, 0, 0.0); // Gọi constructor của lớp cha
        this.storageInGB = convertStorageToGB(storage);
    }

    private int convertStorageToGB(String storage) {
        if (storage.endsWith("TB")) {
            return Integer.parseInt(storage.substring(0, storage.length() - 2)) * 1000; // Chuyển đổi TB thành GB
        } else if (storage.endsWith("GB")) {
            return Integer.parseInt(storage.substring(0, storage.length() - 2));
        }
        return 0;
    }

    public int getStorageInGB() {
        return storageInGB;
    }

    @Override
    public String toString() {
        String storageDisplay = (storageInGB >= 1000)
                ? (storageInGB / 1000) + "TB"
                : storageInGB + "GB";
        return super.toString() + ", Dung lượng: " + storageDisplay;
    }
}