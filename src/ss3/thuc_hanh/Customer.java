package ss3.thuc_hanh;

public class Customer {
    public String customerName ;
    public int customerId ;
    public String customerAddress ;
    public String customerMobile ;
    public String customerEmail ;
    public Customer(String customerName, int customerId, String customerAddress, String customerMobile, String customerEmail) {
        this.customerName = customerName;
        this.customerId =  customerId;
        this.customerAddress = customerAddress;
        this.customerMobile = customerMobile ;
        this.customerEmail = customerEmail;
    }
     public void register(){};
    public void login(){};
    public void updateInformation(){};

}
