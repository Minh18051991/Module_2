package game_shop_management.model;

import java.util.List;

public class Invoice {
    private String invoiceId;
    private Customers customer;
    private String invoiceDate;
    private double totalPrice;
    private List<Products> products; // Danh sách sản phẩm trong hóa đơn

    public Invoice(String invoiceId, Customers customer, String invoiceDate, List<Products> products) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }
    public Invoice(){}


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
        if (customer != null) {
            this.customer = customer;
        } else {
            throw new IllegalArgumentException("Khách hàng không được null.");
        }
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
        this.totalPrice = calculateTotalPrice(); // Cập nhật tổng giá trị hóa đơn khi sản phẩm thay đổi
    }

    private double calculateTotalPrice() {
        return products.stream().mapToDouble(Products::getPrice).sum(); // Tính tổng giá trị sản phẩm
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", customer=" + customer +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}