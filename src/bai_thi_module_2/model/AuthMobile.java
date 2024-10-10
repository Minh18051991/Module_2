package bai_thi_module_2.model;

public class AuthMobile extends Mobile {
    private String warrantyPeriod;
    private String warrantyScope; // Cập nhật tên biến

    public AuthMobile(String id, String name, double price, int stock, String manufacturer, String warrantyPeriod, String warrantyScope) {
        super(id, name, price, stock, manufacturer);
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyScope = warrantyScope; // Sửa tên biến
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyScope() { // Cập nhật phương thức getter
        return warrantyScope;
    }

    public void setWarrantyScope(String warrantyScope) { // Cập nhật phương thức setter
        this.warrantyScope = warrantyScope;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + String.format(",%s,%s,,",
                warrantyPeriod, warrantyScope); // Sửa tên biến
    }

    @Override
    public String toString() {
        return "AuthMobile{" +
                "warrantyPeriod='" + warrantyPeriod + '\'' +
                ", warrantyScope='" + warrantyScope + '\'' + // Sửa tên biến
                "} " + super.toString();
    }
}