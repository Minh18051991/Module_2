package game_shop_management.model;

public class GamingConsole extends Products {
    private String storageCapacity;

    public GamingConsole(String id, String name, double price, String manufacturer, String platform, int stock,String type, String storageCapacity) {
        super(id, name, price, manufacturer, platform, stock, type);
        this.storageCapacity = storageCapacity;
    }


    public String getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String toString() {
        return "GamingConsole{" +
                "storageCapacity='" + storageCapacity + '\'' +
                "} " + super.toString();
    }
}
