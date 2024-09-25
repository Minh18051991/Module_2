package transport_management.service;

import transport_management.model.Vehicle;
import transport_management.repository.IVehicleRepository;
import transport_management.repository.VehicleRepository;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleService implements IVehicleService {
    private final IVehicleRepository repository;
    public List<Vehicle> getVehiclesByType(String type) {
        return repository.getAllVehicles().stream()
                .filter(vehicle -> vehicle.getClass().getSimpleName().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public VehicleService(IVehicleRepository repository) {
        this.repository = repository;
    }

    public void addVehicle(Vehicle vehicle) {
        repository.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return repository.getAllVehicles();
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return repository.findVehicleByLicensePlate(licensePlate);
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        repository.updateVehicle(licensePlate, updatedVehicle);
    }

    public boolean deleteVehicle(String licensePlate) {
        Vehicle vehicle = findVehicleByLicensePlate(licensePlate);
        if (vehicle != null) {
            repository.deleteVehicle(licensePlate);
            return true;
        }
        return false;
    }
}