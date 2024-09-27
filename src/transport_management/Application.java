package transport_management;

import transport_management.controller.IVehicleController;
import transport_management.repository.IVehicleRepository;
import transport_management.service.IVehicleService;
import transport_management.service.VehicleService;
import transport_management.repository.VehicleRepository;
import transport_management.view.VehicleView;
import transport_management.controller.VehicleController;

import java.util.Scanner;

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
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    controller.addVehicle();
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
        }
        scanner.close();
    }
}