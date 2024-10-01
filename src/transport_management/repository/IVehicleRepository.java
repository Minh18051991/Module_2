package transport_management.repository;

import transport_management.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public interface IVehicleRepository {
    void addVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle findVehicleByLicensePlate(String licensePlate);
    void updateVehicle(String licensePlate, Vehicle updatedVehicle);
    void deleteVehicle(String licensePlate);
    void saveToCSV(String filePath);
    void loadFromCSV(String filePath);
}