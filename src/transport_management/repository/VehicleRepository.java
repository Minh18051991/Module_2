package transport_management.repository;

import transport_management.model.Manufacturer;
import transport_management.model.*;
import transport_management.model.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class VehicleRepository implements IVehicleRepository {
    private final List<Vehicle> vehicles;
    private final List<Manufacturer> manufacturers;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
        this.manufacturers = new ArrayList<>();
        initalizeData();
    }

    public void initalizeData() {
        Manufacturer toyota = new Manufacturer("001", "Toyota", "Japan");
        Manufacturer honda = new Manufacturer("002", "Honda", "Japan");
        Manufacturer ford = new Manufacturer("003", "Ford", "USA");
        Manufacturer yamaha = new Manufacturer("004", "Yamaha", "Japan");
        Manufacturer suzuki = new Manufacturer("005", "Suzuki", "Japan");
        Manufacturer isuzu = new Manufacturer("006", "Isuzu", "Japan");
        Manufacturer mercedes = new Manufacturer("007", "Mercedes", "Germany");

        this.manufacturers.add(toyota);
        this.manufacturers.add(honda);
        this.manufacturers.add(ford);
        this.manufacturers.add(yamaha);
        this.manufacturers.add(suzuki);
        this.manufacturers.add(isuzu);
        this.manufacturers.add(mercedes);

        // Thêm các phương tiện mẫu
        vehicles.add(new Oto("30A-12345", toyota, "2020", "Nguyen Van A", 5, "Sedan"));
        vehicles.add(new Oto("30B-67890", honda, "2019", "Tran Thi B", 7, "SUV"));
        vehicles.add(new Motocycle("29M-11111", yamaha, "2021", "Le Van C", 150));
        vehicles.add(new Motocycle("29N-22222", suzuki, "2022", "Hoang Van D", 200));
        vehicles.add(new Truck("29C-33333", isuzu, "2018", "Pham Van E", 1000));
        vehicles.add(new Truck("29D-44444", mercedes, "2021", "Vu Thi F", 2000));
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null; // Not found
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        Vehicle existingVehicle = findVehicleByLicensePlate(licensePlate);
        if (existingVehicle != null) {
            int index = vehicles.indexOf(existingVehicle);
            vehicles.set(index, updatedVehicle);
        }
    }

    public void deleteVehicle(String licensePlate) {
        vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate));
    }
}