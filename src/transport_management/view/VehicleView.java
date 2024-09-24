package transport_management.view;

import transport_management.model.Vehicle;
import java.util.List;
import java.util.Scanner;
import transport_management.model.Manufacturer;
import transport_management.model.Truck;
import transport_management.model.Oto;
import transport_management.model.Motocycle;

public class VehicleView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("Không có xe nào để hiển thị.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
        }
    }

    public void displayMenu() {
        System.out.println("Chọn chức năng:");
        System.out.println("1. Thêm xe");
        System.out.println("2. Hiển thị xe");
        System.out.println("3. Cập nhật xe");
        System.out.println("4. Xóa xe");
        System.out.println("0. Thoát");
    }

    public Vehicle inputVehicleDetails(String vehicleType) {
        System.out.print("Biển số: ");
        String licensePlate = scanner.nextLine();
        System.out.print("Nhà sản xuất: ");
        String manufacturerName = scanner.nextLine();
        System.out.print("Năm sản xuất: ");
        String year = scanner.nextLine();
        System.out.print("Chủ sở hữu: ");
        String owner = scanner.nextLine();

        if (vehicleType.equalsIgnoreCase("Oto")) {
            System.out.print("Số chỗ: ");
            int numSeats = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Kiểu: ");
            String type = scanner.nextLine();
            return new Oto(licensePlate, new Manufacturer("Code", manufacturerName, "Country"), year, owner, numSeats, type);
        } else if (vehicleType.equalsIgnoreCase("Motocycle")) {
            System.out.print("Công suất: ");
            int enginePower = scanner.nextInt();
            scanner.nextLine(); // consume newline
            return new Motocycle(licensePlate, new Manufacturer("Code", manufacturerName, "Country"), year, owner, enginePower);
        } else if (vehicleType.equalsIgnoreCase("Truck")) {
            System.out.print("Tải trọng: ");
            int loadCapacity = scanner.nextInt();
            scanner.nextLine(); // consume newline
            return new Truck(licensePlate, new Manufacturer("Code", manufacturerName, "Country"), year, owner, loadCapacity);
        }
        return null;
    }

    public String inputLicensePlate() {
        System.out.print("Nhập biển số xe: ");
        return scanner.nextLine();
    }
}