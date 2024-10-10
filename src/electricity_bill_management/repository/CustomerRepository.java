package electricity_bill_management.repository;

import electricity_bill_management.model.Customers;
import electricity_bill_management.model.ForeignCustomers;
import electricity_bill_management.model.VNCustomers;
import electricity_bill_management.utilities.SaveAndLoad;

import java.util.List;

public class CustomerRepository {
    private final String filePath;

    public CustomerRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Customers> loadCustomers() {
        return SaveAndLoad.loadData(filePath, line -> {
            String[] data = line.split(",");
            if (data[0].startsWith("KHVN")) {
                return new VNCustomers(data[0], data[1], data[2], Double.parseDouble(data[3]));
            } else if (data[0].startsWith("KHNN")) {
                return new ForeignCustomers(data[0], data[1], data[2]);
            }
            return null;
        });
    }

    public void saveCustomer(Customers customer) {
        SaveAndLoad.saveData(filePath, List.of(customer), data -> {
            if (data instanceof VNCustomers vnCustomer) {
                return String.format("%s,%s,%s,%.2f", vnCustomer.getId(), vnCustomer.getName(), vnCustomer.getCustomerType(), vnCustomer.getConsumptionLimit());
            } else if (data instanceof ForeignCustomers nnCustomer) {
                return String.format("%s,%s,%s", nnCustomer.getId(), nnCustomer.getName(), nnCustomer.getNationality());
            }
            return "";
        });
    }
}