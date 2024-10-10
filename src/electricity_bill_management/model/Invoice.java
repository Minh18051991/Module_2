package electricity_bill_management.model;

import java.util.Date;

public class Invoice {
    private String invoiceId;
    private Customers customer;
    private Date invoiceDate;
    private double quantity;
    private double unitPrice;

    public Invoice(String invoiceId, Customers customer, Date invoiceDate, double quantity, double unitPrice) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters and Setters
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return quantity * unitPrice; // Tính tổng tiền
    }

    public double calculateTotalAmount() {
        if (customer instanceof VNCustomers) {
            return ((VNCustomers) customer).calculateAmount(quantity, unitPrice);
        } else {
            return quantity * unitPrice; // Thành tiền cho khách hàng nước ngoài
        }
    }
}