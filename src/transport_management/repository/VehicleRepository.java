package transport_management.repository;

import transport_management.model.*;
import transport_management.utils.SaveAndLoad;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private final List<Vehicle> vehicles;
    private final List<Manufacturer> manufacturers;
    public static final String CSV_FILE_PATH = "D:\\Codegym\\Module 2\\src\\transport_management\\repository\\Vehicles.csv";
    public static final String MANUFACTURERS_CSV_FILE_PATH = "D:\\Codegym\\Module 2\\src\\transport_management\\repository\\Manufacturers.csv";

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
        this.manufacturers = SaveAndLoad.loadManufacturers(MANUFACTURERS_CSV_FILE_PATH);
        this.vehicles.addAll(SaveAndLoad.loadVehiclesFromCSV(CSV_FILE_PATH, manufacturers));
    }

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
        SaveAndLoad.saveManufacturersToCSV(manufacturers, MANUFACTURERS_CSV_FILE_PATH);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (findVehicleByLicensePlate(vehicle.getLicensePlate()) == null) {
            vehicles.add(vehicle);
            SaveAndLoad.saveVehiclesToCSV(vehicles, CSV_FILE_PATH);
        } else {
            System.out.println("Phương tiện với biển số " + vehicle.getLicensePlate() + " đã tồn tại.");
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    @Override
    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        Vehicle existingVehicle = findVehicleByLicensePlate(licensePlate);
        if (existingVehicle != null) {
            int index = vehicles.indexOf(existingVehicle);
            vehicles.set(index, updatedVehicle);
            SaveAndLoad.saveVehiclesToCSV(vehicles, CSV_FILE_PATH);
        } else {
            System.out.println("Không tìm thấy phương tiện với biển số " + licensePlate);
        }
    }

    @Override
    public void deleteVehicle(String licensePlate) {
        if (vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate))) {
            SaveAndLoad.saveVehiclesToCSV(vehicles, CSV_FILE_PATH);
        } else {
            System.out.println("Không tìm thấy phương tiện với biển số " + licensePlate);
        }
    }
}