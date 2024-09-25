package transport_management.service;

import transport_management.model.Vehicle;
import java.util.List;

public interface IVehicleService {
    void addVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle findVehicleByLicensePlate(String licensePlate);
    void updateVehicle(String licensePlate, Vehicle updatedVehicle);
    boolean deleteVehicle(String licensePlate);
    List<Vehicle> getVehiclesByType(String type);
}