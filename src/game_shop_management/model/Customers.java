package game_shop_management.model;

import game_shop_management.util.EmailUtils;
import game_shop_management.util.PhoneNumberUtils;

public class Customers {
    private String id;
    private String name;
    private String emailAddress;
    private String phoneNumber;

    public Customers(String id, String name, String emailAddress, String phoneNumber) {
        this.id = id;
        this.name = name;
        setEmailAddress(emailAddress);
        setPhoneNumber(phoneNumber);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    @Override
    public String toString() {
        return "Customers.csv{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}