package transport_management.repository;

import transport_management.model.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private final List<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(Class<? extends Vehicle> vehicleType) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicleType.isInstance(vehicle)) {
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null; // Not found
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        int index = -1;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getLicensePlate().equals(licensePlate)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            vehicles.set(index, updatedVehicle);
        }
    }

    public void deleteVehicle(String licensePlate) {
        vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate));
    }
}