package transport_management.model;

public class Manufacturer {
    private final String code;
    private final String name;
    private final String country;

    public Manufacturer(String code, String name, String country) {
        this.code = code; // Thêm khởi tạo cho thuộc tính code
        this.name = name;
        this.country = country;
    }


    public String getCode() {
        return code; // Thêm phương thức getter cho code
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country; // Thêm phương thức getter cho country
    }
}