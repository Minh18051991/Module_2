package transport_management.service;

import transport_management.model.Vehicle;
import java.util.ArrayList;

public interface IVehicleService {
    void addVehicle(Vehicle vehicle);
    void displayVehicles();
    boolean deleteVehicle(String licensePlate);
    ArrayList<Vehicle> searchVehicle(String licensePlate);
    boolean updateVehicle(String licensePlate, Vehicle updatedVehicle);
}