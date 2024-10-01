package transport_management.view;

import transport_management.model.*;
import transport_management.repository.VehicleRepository;
import transport_management.validator.VehicleValidator;
import transport_management.utils.SaveAndLoad;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VehicleView {
    private final Scanner scanner = new Scanner(System.in);
    private final VehicleRepository vehicleRepository = new VehicleRepository();

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
        System.out.println("4. Tất cả");
        System.out.print("Nhập lựa chọn: ");
        return scanner.nextInt();
    }

    public Vehicle inputVehicleDetails(String vehicleType) {
        String licensePlate;
        String manufacturerName;
        String year;
        String owner;

        // Nhập biển số xe và kiểm tra tính hợp lệ
        while (true) {
            System.out.print("Biển số: ");
            licensePlate = scanner.nextLine();
            if (VehicleValidator.isLicensePlateValid(licensePlate)) {
                break; // Biển số hợp lệ, thoát vòng lặp
            } else {
                System.out.println("Biển số không hợp lệ. Vui lòng nhập lại.");
            }
        }

        manufacturerName = chooseOrAddManufacturer();

        // Nhập năm sản xuất và kiểm tra tính hợp lệ
        while (true) {
            System.out.print("Năm sản xuất: ");
            year = scanner.nextLine();
            if (VehicleValidator.isYearValid(year)) {
                break; // Năm hợp lệ, thoát vòng lặp
            } else {
                System.out.println("Năm không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập chủ sở hữu
        System.out.print("Chủ sở hữu: ");
        owner = scanner.nextLine();

        // Nhập thông tin bổ sung dựa trên loại xe
        return createVehicle(vehicleType, licensePlate, manufacturerName, year, owner);
    }

    private Vehicle createVehicle(String vehicleType, String licensePlate, String manufacturerName, String year, String owner) {
        if (vehicleType.equalsIgnoreCase("Oto")) {
            int numSeats = inputNumSeats();
            System.out.print("Kiểu: ");
            String type = scanner.nextLine();
            return new Oto(licensePlate, new Manufacturer("Code", manufacturerName, "Country"), year, owner, numSeats, type);

        } else if (vehicleType.equalsIgnoreCase("Motocycle")) {
            int enginePower = inputEnginePower();
            return new Motocycle(licensePlate, new Manufacturer("Code", manufacturerName, "Country"), year, owner, enginePower);

        } else if (vehicleType.equalsIgnoreCase("Truck")) {
            int loadCapacity = inputLoadCapacity();
            return new Truck(licensePlate, new Manufacturer("Code", manufacturerName, "Country"), year, owner, loadCapacity);
        }

        return null; // Nếu loại xe không hợp lệ
    }

    private int inputNumSeats() {
        int numSeats = 0;
        while (true) {
            try {
                System.out.print("Số chỗ: ");
                numSeats = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (VehicleValidator.isNumSeatsValid(numSeats)) {
                    break; // Số chỗ hợp lệ, thoát vòng lặp
                } else {
                    System.out.println("Số chỗ không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
                scanner.nextLine(); // Xóa bỏ đầu vào không hợp lệ
            }
        }
        return numSeats;
    }

    private int inputEnginePower() {
        int enginePower = 0;
        while (true) {
            try {
                System.out.print("Công suất: ");
                enginePower = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (VehicleValidator.isEnginePowerValid(enginePower)) {
                    break; // Công suất hợp lệ, thoát vòng lặp
                } else {
                    System.out.println("Công suất không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
                scanner.nextLine(); // Xóa bỏ đầu vào không hợp lệ
            }
        }
        return enginePower;
    }

    private int inputLoadCapacity() {
        int loadCapacity = 0;
        while (true) {
            try {
                System.out.print("Tải trọng: ");
                loadCapacity = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (VehicleValidator.isLoadCapacityValid(loadCapacity)) {
                    break; // Tải trọng hợp lệ, thoát vòng lặp
                } else {
                    System.out.println("Tải trọng không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
                scanner.nextLine(); // Xóa bỏ đầu vào không hợp lệ
            }
        }
        return loadCapacity;
    }

    private String chooseOrAddManufacturer() {
        System.out.println("Chọn nhà sản xuất:");
        List<Manufacturer> manufacturers = SaveAndLoad.loadManufacturers(vehicleRepository.MANUFACTURERS_CSV_FILE_PATH);
        for (int i = 0; i < manufacturers.size(); i++) {
            System.out.println((i + 1) + ". " + manufacturers.get(i).getName());
        }
        System.out.println((manufacturers.size() + 1) + ". Thêm nhà sản xuất mới");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice > 0 && choice <= manufacturers.size()) {
            return manufacturers.get(choice - 1).getName();
        } else {
            // Thêm nhà sản xuất mới
            System.out.print("Nhập tên nhà sản xuất mới: ");
            String newManufacturerName = scanner.nextLine();
            Manufacturer newManufacturer = new Manufacturer("Code", newManufacturerName, "Country"); // Thay đổi theo yêu cầu
            addManufacturer(newManufacturer); // Thêm vào danh sách
            return newManufacturerName;
        }
    }

    private void addManufacturer(Manufacturer manufacturer) {
        vehicleRepository.addManufacturer(manufacturer);
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
        return switch (choice) {
            case 1 -> "Oto";
            case 2 -> "Motocycle";
            case 3 -> "Truck";
            default -> null;
        };
    }
}