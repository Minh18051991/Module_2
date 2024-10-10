package bai_thi_module_2.model;

public abstract class Mobile {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String manufacturer;

    public Mobile(String id, String name, double price, int stock, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.manufacturer = manufacturer;
    }

    public Mobile() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toCSV() {
        return String.format("%s,%s,%.2f,%d,%s",
                id, name, price, stock, manufacturer);
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}