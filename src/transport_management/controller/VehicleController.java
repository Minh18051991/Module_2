package transport_management.controller;

import transport_management.model.Vehicle;
import transport_management.service.VehicleService;
import java.util.List;

public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    public void addVehicle(Vehicle vehicle) {
        service.addVehicle(vehicle);
    }

    public List<Vehicle> getVehiclesByType(Class<? extends Vehicle> vehicleType) {
        return service.getVehiclesByType(vehicleType);
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return service.findVehicleByLicensePlate(licensePlate);
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        service.updateVehicle(licensePlate, updatedVehicle);
    }

    public void deleteVehicle(String licensePlate) {
        service.deleteVehicle(licensePlate);
    }
}