package transport_management.controller;

import transport_management.model.Vehicle;
import java.util.ArrayList;

public interface IVehicleController {
    boolean addVehicle(Vehicle vehicle);
    void displayVehicles();
    boolean deleteVehicle(String licensePlate);
    ArrayList<Vehicle> searchVehicle(String licensePlate);
    boolean updateVehicle(String licensePlate, Vehicle updatedVehicle);
}