package game_shop_management.model;

public class GamingPC extends Product {
    private String gpu;
    private String cpu;
    private String ram;
    private int storageInGB; // Lưu dung lượng dưới dạng GB
    private String deviceType;

    public GamingPC(String id, String name, double price, boolean isRented, String manufacturer, String gpu, String cpu, String ram, String storage, String deviceType, int quantity) {
        super(id, name, price, isRented, manufacturer, "Windows", quantity, null, null, 0.0, 0, 0.0); // Gọi constructor của lớp cha
        this.gpu = gpu;
        this.cpu = cpu;
        this.ram = ram;
        this.storageInGB = convertStorageToGB(storage);
        this.deviceType = deviceType;
    }

    private int convertStorageToGB(String storage) {
        if (storage.endsWith("TB")) {
            return Integer.parseInt(storage.substring(0, storage.length() - 2)) * 1000; // Chuyển đổi TB thành GB
        } else if (storage.endsWith("GB")) {
            return Integer.parseInt(storage.substring(0, storage.length() - 2));
        }
        return 0;
    }

    @Override
    public String toString() {
        String storageDisplay = (storageInGB >= 1000)
                ? (storageInGB / 1000) + "TB"
                : storageInGB + "GB";
        return super.toString() + ", GPU: " + gpu + ", CPU: " + cpu + ", RAM: " + ram + ", Dung lượng: " + storageDisplay + ", Loại thiết bị: " + deviceType;
    }
}