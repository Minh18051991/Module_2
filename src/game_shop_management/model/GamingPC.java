package game_shop_management.model;

public class GamingPC extends Product {
    private String gpu;
    private String cpu;
    private String ram;
    private String storage;
    private String deviceType;

    public GamingPC(String name, double price, boolean isRented, String manufacturer, String gpu, String cpu, String ram, String storage, String deviceType, int quantity) {
        super(name, price, isRented, manufacturer, quantity);
        this.gpu = gpu;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return super.toString() + ", GPU: " + gpu + ", CPU: " + cpu + ", RAM: " + ram + ", Dung lượng: " + storage + ", Loại thiết bị: " + deviceType;
    }
}