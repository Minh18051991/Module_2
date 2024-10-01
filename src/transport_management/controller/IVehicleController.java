package transport_management.controller;

import transport_management.model.Vehicle;
import java.util.ArrayList;

public interface IVehicleController {
    void addVehicle();
    void displayVehicles();
    void displayVehiclesByType();
    void updateVehicle();
    void deleteVehicle();

}