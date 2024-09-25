package transport_management.view;

import transport_management.model.*;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("1. Thêm phương tiện");
        System.out.println("2. Hiển thị phương tiện");
        System.out.println("3. Cập nhật phương tiện");
        System.out.println("4. Xóa phương tiện");
        System.out.println("0. Thoát");
    }
    public int inputVehicleTypeSelection() {
        System.out.println("Chọn loại xe để hiển thị:");
        System.out.println("1. Oto");
        System.out.println("2. Motocycle");
        System.out.println("3. Truck");
        System.out.print("Nhập lựa chọn: ");
        return scanner.nextInt();
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

    public String inputVehicleType() {
        System.out.println("Chọn loại xe:");
        System.out.println("1. Oto");
        System.out.println("2. Motocycle");
        System.out.println("3. Truck");
        System.out.print("Nhập lựa chọn: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (choice) {
            case 1: return "Oto";
            case 2: return "Motocycle";
            case 3: return "Truck";
            default: return null;
        }
    }
}