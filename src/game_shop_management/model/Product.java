package game_shop_management.model;

// Lớp cha Product
public abstract class Product {
    private String id; // ID của sản phẩm
    private String name;
    private double price;
    private boolean isRented;
    private String manufacturer;
    private String platform;
    private int quantity;
    private String rentDate; // Ngày cho thuê
    private String returnDate; // Ngày trả
    private double revenue; // Doanh thu
    private int rentDuration; // Thời gian thuê
    private double rentFee; // Phí thuê

    // Constructor đầy đủ
    public Product(String id, String name, double price, boolean isRented, String manufacturer, String platform, int quantity, String rentDate, String returnDate, double revenue, int rentDuration, double rentFee) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isRented = isRented;
        this.manufacturer = manufacturer;
        this.platform = platform;
        this.quantity = quantity;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.revenue = revenue;
        this.rentDuration = rentDuration;
        this.rentFee = rentFee;
    }

    // Constructor không có ngày thuê và ngày trả
    public Product(String name, double price, boolean isRented, String manufacturer, String platform, int quantity) {
        this.name = name;
        this.price = price;
        this.isRented = isRented;
        this.manufacturer = manufacturer;
        this.platform = platform;
        this.quantity = quantity;
    }

    // Getter và setter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public double setPrice(double price) {
        this.price = price;
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(int rentDuration) {
        this.rentDuration = rentDuration;
    }

    public double getRentFee() {
        return rentFee;
    }

    public void setRentFee(double rentFee) {
        this.rentFee = rentFee;
    }

    // Phương thức cho thuê sản phẩm
    public void rent(String rentDate, double rentFee) {
        if (!isRented && quantity > 0) {
            this.isRented = true;
            this.rentDate = rentDate;
            this.rentFee = rentFee;
            quantity--;
            revenue += rentFee; // Cập nhật doanh thu
        } else {
            System.out.println("Sản phẩm không có sẵn để cho thuê.");
        }
    }

    // Phương thức trả sản phẩm
    public void returnProduct(String returnDate) {
        if (isRented) {
            this.isRented = false;
            this.returnDate = returnDate;
            quantity++;
        } else {
            System.out.println("Sản phẩm không được cho thuê.");
        }
    }

    @Override
    public String toString() {
        return "Tên: " + name + ", Giá: " + price + ", Nhà sản xuất: " + manufacturer + ", Nền tảng: " + platform + ", Số lượng: " + quantity + ", Doanh thu: " + revenue;
    }
}