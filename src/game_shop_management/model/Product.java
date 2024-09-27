package game_shop_management.model;

// Lớp cha Product
public abstract class Product {
    private String name;
    private double price;
    private boolean isRented;
    private String manufacturer; // Thêm nhà sản xuất
    private String platform; // Thêm nền tảng

    public Product(String name, double price, String manufacturer, String platform) {
        this.name = name;
        this.price = price;
        this.isRented = false;
        this.manufacturer = manufacturer;
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
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

    public boolean isRented() {
        return isRented;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPlatform() {
        return platform;
    }

    public void rent() {
        if (!isRented) {
            isRented = true;
            System.out.println(name + " đã được cho thuê.");
        } else {
            System.out.println(name + " hiện đang được cho thuê.");
        }
    }

    public void returnProduct() {
        if (isRented) {
            isRented = false;
            System.out.println(name + " đã được trả lại.");
        } else {
            System.out.println(name + " không phải là sản phẩm đang thuê.");
        }
    }
}
