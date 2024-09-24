package transport_management.service;

import transport_management.model.Vehicle;
import transport_management.repository.VehicleRepository;
import java.util.List;

public class VehicleService {
    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public void addVehicle(Vehicle vehicle) {
        repository.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return repository.getAllVehicles();
    }

    public List<Vehicle> getVehiclesByType(Class<? extends Vehicle> vehicleType) {
        return repository.getVehiclesByType(vehicleType);
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return repository.findVehicleByLicensePlate(licensePlate);
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        repository.updateVehicle(licensePlate, updatedVehicle);
    }

    public void deleteVehicle(String licensePlate) {
        repository.deleteVehicle(licensePlate);
    }
}