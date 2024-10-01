package transport_management.service;

import transport_management.model.Motocycle;
import transport_management.model.Oto;
import transport_management.model.Truck;
import transport_management.model.Vehicle;
import transport_management.repository.IVehicleRepository;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleService implements IVehicleService {
    private final IVehicleRepository repository;

    public VehicleService(IVehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return repository.getAllVehicles().stream()
                .filter(vehicle -> switch (type.toLowerCase()) {
                    case "oto" -> vehicle instanceof Oto;
                    case "motocycle" -> vehicle instanceof Motocycle;
                    case "truck" -> vehicle instanceof Truck;
                    default -> false; // Hoặc có thể ném ngoại lệ nếu loại không hợp lệ
                })
                .collect(Collectors.toList());
    }

    public void addVehicle(Vehicle vehicle) {
        repository.addVehicle(vehicle); // Ghi vào repository (cũng sẽ ghi vào CSV)
    }

    public List<Vehicle> getAllVehicles() {
        return repository.getAllVehicles();
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return repository.findVehicleByLicensePlate(licensePlate);
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        repository.updateVehicle(licensePlate, updatedVehicle); // Cập nhật trong repository
    }

    public boolean deleteVehicle(String licensePlate) {
        Vehicle vehicle = findVehicleByLicensePlate(licensePlate);
        if (vehicle != null) {
            repository.deleteVehicle(licensePlate); // Xóa trong repository
            return true;
        }
        return false;
    }
}