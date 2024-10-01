package transport_management.controller;

import transport_management.model.Motocycle;
import transport_management.model.Oto;
import transport_management.model.Truck;
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
        displayVehiclesList(vehicles);
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
            case 4: // Tất cả
                vehicles = service.getAllVehicles();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        if (vehicles.isEmpty()) {
            System.out.println("Không tìm thấy phương tiện nào.");
            return;
        }

        // Hiển thị danh sách phương tiện dưới dạng bảng
        displayVehiclesList(vehicles);
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
    private static void displayVehiclesList(List<Vehicle> vehicles) {
        System.out.printf("%-15s|%-15s|%-5s| %-15s |%-10s| %-15s\n",
                "Biển số", "Nhà sản xuất", "Năm", "Chủ sở hữu", "Loại xe", "Thông tin thêm");
        System.out.println("----------------------------------------------------------------------");

        for (Vehicle vehicle : vehicles) {
            String additionalInfo = "";
            if (vehicle instanceof Oto oto) {
                additionalInfo = "Số chỗ: " + oto.getNumSeats() + "   Kiểu: " + oto.getType();
            } else if (vehicle instanceof Motocycle motorcycle) {
                additionalInfo = "Công suất: " + motorcycle.getEnginePower();
            } else if (vehicle instanceof Truck truck) {
                additionalInfo = "Tải trọng: " + truck.getLoadCapacity();
            }

            System.out.printf("%-15s|%-15s|%-5s| %-15s |%-10s| %-30s\n",
                    vehicle.getLicensePlate(),
                    vehicle.getManufacturer().getName(),
                    vehicle.getYear(),
                    vehicle.getOwner(),
                    vehicle.getClass().getSimpleName(), // Hiển thị loại xe
                    additionalInfo);
            System.out.println("--------------------------------------------------------------------------------");
        }
    }
}