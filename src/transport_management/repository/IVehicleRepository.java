package transport_management.repository;

import transport_management.model.Vehicle;

import java.util.ArrayList;

public interface IVehicleRepository {
    void addVehicle(Vehicle vehicle);
    boolean deleteVehicle(String licensePlate);
    ArrayList<Vehicle> searchVehicle(String licensePlate);
    ArrayList<Vehicle> getAllVehicles();
    boolean updateVehicle(String licensePlate, Vehicle updatedVehicle);
}