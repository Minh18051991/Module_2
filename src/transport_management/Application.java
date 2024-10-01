package transport_management;

import java.util.InputMismatchException;


import transport_management.controller.IVehicleController;
import transport_management.repository.IVehicleRepository;
import transport_management.service.IVehicleService;
import transport_management.service.VehicleService;
import transport_management.repository.VehicleRepository;
import transport_management.view.VehicleView;
import transport_management.controller.VehicleController;

import java.util.Scanner;

import static transport_management.repository.VehicleRepository.CSV_FILE_PATH;

public class Application {
    public static void main(String[] args) {
        IVehicleRepository repository = new VehicleRepository();
        IVehicleService service = new VehicleService(repository);
        VehicleView view = new VehicleView();
        IVehicleController controller = new VehicleController(service, view);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            view.displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng sau khi nhập số
                switch (choice) {
                    case 1:
                        controller.addVehicle();
                        repository.saveToCSV(CSV_FILE_PATH);
                        break;

                    case 2:
                        controller.displayVehiclesByType();
                        break;

                    case 3:
                        controller.updateVehicle();
                        break;

                    case 4:
                        controller.displayVehicles();
                        controller.deleteVehicle();
                        break;

                    case 0: // Exit
                        running = false;
                        System.out.println("Thoát chương trình.");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
                scanner.nextLine(); // Xóa bỏ đầu vào không hợp lệ
            }
        }
        scanner.close();
    }
}