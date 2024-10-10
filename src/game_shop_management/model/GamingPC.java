package game_shop_management.model;

public class GamingPC extends Products {
    private String CPU;
    private String GPU;
    private String RAM;
    private String storage;

    public GamingPC(String id, String name, double price, String manufacturer, String platform, int stock, String CPU, String GPU, String RAM,String type,  String storage) {
        super(id, name, price, manufacturer, platform, stock, type);
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
        this.storage = storage;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
    @Override
    public String toString() {
        return super.toString() + ", CPU: " + CPU + ", GPU: " + GPU + ", RAM: " + RAM + ", Storage: " + storage;
    }
}