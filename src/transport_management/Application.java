package transport_management;

import transport_management.controller.VehicleController;
import transport_management.model.Manufacturer;
import transport_management.model.Motocycle;
import transport_management.model.Oto;
import transport_management.model.Truck;
import transport_management.repository.VehicleRepository;
import transport_management.service.VehicleService;
import transport_management.view.VehicleView;
import transport_management.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        VehicleRepository repository = new VehicleRepository();
        VehicleService service = new VehicleService(repository);
        VehicleController controller = new VehicleController(service);
        VehicleView view = new VehicleView();
        Scanner scanner = new Scanner(System.in);

        // Separate lists for each vehicle type
        List<Vehicle> otoList = new ArrayList<>();
        List<Vehicle> motorcycleList = new ArrayList<>();
        List<Vehicle> truckList = new ArrayList<>();

        boolean running = true;
        while (running) {
            view.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1: // Add vehicle
                    System.out.println(" 1. Oto ");
                    System.out.println(" 2. Motocycle ");
                    System.out.println(" 3. Truck ");
                    System.out.print("Chọn loại xe để thêm: ");
                    int vehicleType = Integer.parseInt(scanner.nextLine());
                    Vehicle newVehicle;
                    switch (vehicleType) {
                        case 1:
                            newVehicle = view.inputVehicleDetails("Oto");
                            otoList.add((Oto) newVehicle);
                            System.out.println("Ôtô đã được thêm.");
                            break;
                        case 2:
                            newVehicle = view.inputVehicleDetails("Motocycle");
                            motorcycleList.add((Motocycle) newVehicle);
                            System.out.println("Xe máy đã được thêm.");
                            break;
                        case 3:
                            newVehicle = view.inputVehicleDetails("Truck");
                            truckList.add((Truck) newVehicle);
                            System.out.println("Xe tải đã được thêm.");
                            break;
                        default:
                            System.out.println("Loại xe không hợp lệ.");
                    }
                    break;

                case 2: // Display vehicles
                    System.out.print("Chọn loại xe để hiển thị (Oto/Motocycle/Truck): ");
                    String typeToDisplay = scanner.nextLine();

                    switch (typeToDisplay.toLowerCase()) {
                        case "oto":
                            view.displayVehicles(otoList);
                            break;
                        case "motocycle":
                            view.displayVehicles(motorcycleList);
                            break;
                        case "truck":
                            view.displayVehicles(truckList);
                            break;
                        default:
                            System.out.println("Loại xe không hợp lệ.");
                    }
                    break;

                case 3: // Update vehicle
                    System.out.print("Chọn loại xe để cập nhật (Oto/Motocycle/Truck): ");
                    String typeToUpdate = scanner.nextLine();
                    System.out.print("Nhập biển số xe: ");
                    String licensePlateToUpdate = scanner.nextLine();
                    Vehicle vehicleToUpdate = null;

                    switch (typeToUpdate.toLowerCase()) {
                        case "oto":
                            vehicleToUpdate = findVehicleByLicensePlate(otoList, licensePlateToUpdate);
                            break;
                        case "motocycle":
                            vehicleToUpdate = findVehicleByLicensePlate(motorcycleList, licensePlateToUpdate);
                            break;
                        case "truck":
                            vehicleToUpdate = findVehicleByLicensePlate(truckList, licensePlateToUpdate);
                            break;
                        default:
                            System.out.println("Loại xe không hợp lệ.");
                    }

                    if (vehicleToUpdate != null) {
                        System.out.println("Cập nhật thông tin xe:");
                        Vehicle updatedVehicle = view.inputVehicleDetails(vehicleToUpdate.getClass().getSimpleName());
                        if (typeToUpdate.equalsIgnoreCase("Oto")) {
                            otoList.remove(vehicleToUpdate);
                            otoList.add((Oto) updatedVehicle);
                        } else if (typeToUpdate.equalsIgnoreCase("Motocycle")) {
                            motorcycleList.remove(vehicleToUpdate);
                            motorcycleList.add((Motocycle) updatedVehicle);
                        } else if (typeToUpdate.equalsIgnoreCase("Truck")) {
                            truckList.remove(vehicleToUpdate);
                            truckList.add((Truck) updatedVehicle);
                        }
                        System.out.println("Xe đã được cập nhật.");
                    } else {
                        System.out.println("Không tìm thấy xe với biển số này.");
                    }
                    break;

                case 4: // Delete vehicle
                    System.out.print("Chọn loại xe để xóa (Oto/Motocycle/Truck): ");
                    String typeToDelete = scanner.nextLine();
                    System.out.print("Nhập biển số xe: ");
                    String licensePlateToDelete = scanner.nextLine();

                    switch (typeToDelete.toLowerCase()) {
                        case "oto":
                            if (deleteVehicleByLicensePlate(otoList, licensePlateToDelete)) {
                                System.out.println("Ôtô đã được xóa.");
                            } else {
                                System.out.println("Không tìm thấy ôtô với biển số này.");
                            }
                            break;
                        case "motocycle":
                            if (deleteVehicleByLicensePlate(motorcycleList, licensePlateToDelete)) {
                                System.out.println("Xe máy đã được xóa.");
                            } else {
                                System.out.println("Không tìm thấy xe máy với biển số này.");
                            }
                            break;
                        case "truck":
                            if (deleteVehicleByLicensePlate(truckList, licensePlateToDelete)) {
                                System.out.println("Xe tải đã được xóa.");
                            } else {
                                System.out.println("Không tìm thấy xe tải với biển số này.");
                            }
                            break;
                        default:
                            System.out.println("Loại xe không hợp lệ.");
                    }
                    break;

                case 0: // Exit
                    running = false;
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }
        scanner.close();
    }

    private static Vehicle findVehicleByLicensePlate(List<? extends Vehicle> vehicles, String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null; // Not found
    }

    private static boolean deleteVehicleByLicensePlate(List<? extends Vehicle> vehicles, String licensePlate) {
        Vehicle vehicleToRemove = findVehicleByLicensePlate(vehicles, licensePlate);
        if (vehicleToRemove != null) {
            vehicles.remove(vehicleToRemove);
            return true; // Successfully removed
        }
        return false; // Not found
    }
}