package bai_thi_module_2.model;

public class AuthMobile extends Mobile {
    private String warrantyPeriod;
    private String warrantyScope; // Cập nhật tên biến

    public AuthMobile(String id, String name, double price, int stock, String manufacturer, String warrantyPeriod, String warrantyScope) {
        super(id, name, price, stock, manufacturer);
        this.warrantyPeriod = warrantyPeriod;
        setWarrantyScope(warrantyScope);
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyScope() {
        return warrantyScope;
    }

    public void setWarrantyScope(String warrantyScope) {
        if (!"Toàn quốc".equals(warrantyScope) && !"Quốc tế".equals(warrantyScope)) {
            throw new IllegalArgumentException("Giá trị warrantyScope không hợp lệ: " + warrantyScope);
        }
        this.warrantyScope = warrantyScope;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + String.format(",%s,%s,,",
                warrantyPeriod, warrantyScope);
    }

    @Override
    public String toString() {
        return "AuthMobile{" +
                "warrantyPeriod='" + warrantyPeriod + '\'' +
                ", warrantyScope='" + warrantyScope + '\'' +
                "} " + super.toString();
    }
}