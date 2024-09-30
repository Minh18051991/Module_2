package game_shop_management.model;

// Lớp cha Product
public abstract class Product {
    private String name;
    private double price;
    private boolean isRented;
    private String manufacturer;
    private String platform;
    private int quantity;

    public Product(String name, double price, boolean isRented, String manufacturer, String platform, int quantity) {
        this.name = name;
        this.price = price;
        this.isRented = isRented;
        this.manufacturer = manufacturer;
        this.platform = platform;
        this.quantity = quantity;
    }

    public Product(String name, double price, boolean isRented, String manufacturer, int quantity) {
        this(name, price, isRented, manufacturer, "", quantity); // Gọi constructor khác
    }

    // Getter và setter...

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPlatform() {
        return platform;
    }

    public int getQuantity() {
        return quantity;
    }

    public void rent() {
        // Logic cho thuê
    }

    public void returnProduct() {
        // Logic trả sản phẩm
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Tên: " + name + ", Giá: " + price + ", Nhà sản xuất: " + manufacturer + ", Nền tảng: " + platform + ", Số lượng: " + quantity;
    }
}