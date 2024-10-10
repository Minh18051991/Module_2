package electricity_bill_management.model;

public class VNCustomers extends Customers {
    private final String customerType; // Loại khách hàng
    private final double consumptionLimit; // Định mức tiêu thụ

    public VNCustomers(String id, String name, String customerType, double consumptionLimit) {
        super(id, name);
        this.customerType = customerType;
        this.consumptionLimit = consumptionLimit;
    }


    public String getCustomerType() {
        return customerType;
    }

    public double getConsumptionLimit() {
        return consumptionLimit;
    }

    public double calculateAmount(double quantity, double unitPrice) {
        if (quantity <= consumptionLimit) {
            return quantity * unitPrice; // Tính thành tiền nếu không vượt định mức
        } else {
            return consumptionLimit * unitPrice + (quantity - consumptionLimit) * unitPrice * 2.5; // Tính thành tiền nếu vượt định mức
        }
    }
}