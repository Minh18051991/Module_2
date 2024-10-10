package electricity_bill_management.model;

public class ForeignCustomers extends Customers {
    private final String nationality; // Quốc tịch

    public ForeignCustomers(String id, String name, String nationality) {
        super(id, name);
        this.nationality = nationality;
    }


    public String getNationality() {
        return nationality;
    }

    public double calculateAmount(double quantity, double unitPrice) {
        return quantity * unitPrice; // Tính thành tiền cho khách hàng nước ngoài
    }
}