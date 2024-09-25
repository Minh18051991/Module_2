package transport_management.controller;

import transport_management.model.Vehicle;
import transport_management.service.IVehicleService;
import transport_management.view.VehicleView;

import java.util.List;

public class VehicleController implements IVehicleController {
    private final IVehicleService service;
    private final VehicleView view;

    public VehicleController(IVehicleService service, VehicleView view) {
        this.service = service;
        this.view = view;
    }

    public void addVehicle() {
        Vehicle vehicle = view.inputVehicleDetails(view.inputVehicleType());
        service.addVehicle(vehicle);
        System.out.println("Phương tiện đã được thêm.");
    }

    public void displayVehicles() {
        List<Vehicle> vehicles = service.getAllVehicles();
        view.displayVehicles(vehicles);
    }

    public void displayVehiclesByType() {
        int type = view.inputVehicleTypeSelection(); // Nhận lựa chọn loại xe từ người dùng
        List<Vehicle> vehicles;

        switch (type) {
            case 1: // Ô tô
                vehicles = service.getVehiclesByType("Oto");
                break;
            case 2: // Xe máy
                vehicles = service.getVehiclesByType("Motocycle");
                break;
            case 3: // Xe tải
                vehicles = service.getVehiclesByType("Truck");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        view.displayVehicles(vehicles);
    }

    public void updateVehicle() {
        String licensePlate = view.inputLicensePlate();
        Vehicle existingVehicle = service.findVehicleByLicensePlate(licensePlate);
        if (existingVehicle != null) {
            Vehicle updatedVehicle = view.inputVehicleDetails(existingVehicle.getClass().getSimpleName());
            service.updateVehicle(licensePlate, updatedVehicle);
            System.out.println("Phương tiện đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy xe với biển số này.");
        }
    }

    public void deleteVehicle() {
        String licensePlate = view.inputLicensePlate();
        if (service.deleteVehicle(licensePlate)) {
            System.out.println("Phương tiện đã được xóa.");
        } else {
            System.out.println("Không tìm thấy xe với biển số này.");
        }
    }
}