package transport_management.repository;

import transport_management.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private final List<Vehicle> vehicles;
    private final List<Manufacturer> manufacturers;
    public static final String CSV_FILE_PATH = "D:\\Codegym\\Module 2\\src\\transport_management\\repository\\Vehicles.csv";

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
        this.manufacturers = new ArrayList<>();
        loadFromCSV(CSV_FILE_PATH); // Tải dữ liệu từ file CSV
    }

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
        saveManufacturersToCSV("D:\\Codegym\\Module 2\\src\\transport_management\\repository\\Manufacturers.csv"); // Đường dẫn tới file CSV
    }

    public void saveManufacturersToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Manufacturer manufacturer : manufacturers) {
                writer.write(manufacturerToCSV(manufacturer));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Manufacturer> loadManufacturers() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Codegym\\Module 2\\src\\transport_management\\repository\\Manufacturers.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    manufacturers.add(new Manufacturer(values[0], values[1], values[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    private String manufacturerToCSV(Manufacturer manufacturer) {
        return manufacturer.getCode() + "," + manufacturer.getName() + "," + manufacturer.getCountry();
    }


    @Override
    public void addVehicle(Vehicle vehicle) {
        if (findVehicleByLicensePlate(vehicle.getLicensePlate()) == null) {
            vehicles.add(vehicle);
            saveToCSV(CSV_FILE_PATH);
        } else {
            System.out.println("Phương tiện với biển số " + vehicle.getLicensePlate() + " đã tồn tại.");
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    @Override
    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null; // Không tìm thấy
    }

    @Override
    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        Vehicle existingVehicle = findVehicleByLicensePlate(licensePlate);
        if (existingVehicle != null) {
            int index = vehicles.indexOf(existingVehicle);
            vehicles.set(index, updatedVehicle);
            saveToCSV(CSV_FILE_PATH);
        }
    }

    @Override
    public void deleteVehicle(String licensePlate) {
        vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate));
        saveToCSV(CSV_FILE_PATH);
    }

    @Override
    public void loadFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Vehicle vehicle = csvToVehicle(line);
                if (vehicle != null) {
                    if (findVehicleByLicensePlate(vehicle.getLicensePlate()) == null) {
                        vehicles.add(vehicle);
                    } else {
                        System.out.println("Phương tiện với biển số " + vehicle.getLicensePlate() + " đã tồn tại.");
                    }
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Không tìm thấy dữ liệu phương tiện có sẵn. Bắt đầu mới.");
        }
    }

    @Override
    public void saveToCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicleToCSV(vehicle));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String vehicleToCSV(Vehicle vehicle) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(vehicle.getLicensePlate()).append(",")
                .append(vehicle.getManufacturer().getName()).append(",")
                .append(vehicle.getYear()).append(",")
                .append(vehicle.getOwner()).append(",")
                .append(vehicle.getClass().getSimpleName());

        if (vehicle instanceof Oto oto) {
            csvBuilder.append(",").append(oto.getNumSeats());
        } else if (vehicle instanceof Truck truck) {
            csvBuilder.append(",").append(truck.getLoadCapacity());
        } else if (vehicle instanceof Motocycle motorcycle) {
            csvBuilder.append(",").append(motorcycle.getEnginePower());
        }

        return csvBuilder.toString();
    }

    private Vehicle csvToVehicle(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length < 5) return null; // Dòng không hợp lệ

        String licensePlate = fields[0].trim();
        String manufacturerName = fields[1].trim();
        String year = fields[2].trim();
        String owner = fields[3].trim();
        String vehicleType = fields[4].trim();

        Manufacturer manufacturer = manufacturers.stream()
                .filter(m -> m.getName().equalsIgnoreCase(manufacturerName))
                .findFirst()
                .orElse(null);

        if (manufacturer == null) {
            System.out.println("Nhà sản xuất không hợp lệ: " + manufacturerName);
            return null;
        }

        // Khai báo các biến
        int numberOfSeats = 0;
        int loadCapacity = 0;
        int engineCapacity = 0;

        try {
            if ("Oto".equalsIgnoreCase(vehicleType)) {
                if (fields.length >= 6) {
                    numberOfSeats = Integer.parseInt(fields[5].trim());
                    return new Oto(licensePlate, manufacturer, year, owner, numberOfSeats, "Sedan");
                }
            } else if ("Motocycle".equalsIgnoreCase(vehicleType)) {
                if (fields.length >= 6) {
                    engineCapacity = Integer.parseInt(fields[5].trim());
                    return new Motocycle(licensePlate, manufacturer, year, owner, engineCapacity);
                }
            } else if ("Truck".equalsIgnoreCase(vehicleType)) {
                if (fields.length >= 6) {
                    loadCapacity = Integer.parseInt(fields[5].trim());
                    if (loadCapacity <= 0) {
                        System.out.println("Khối lượng hàng không hợp lệ: " + loadCapacity);
                        return null; // Trả về null nếu khối lượng hàng không hợp lệ
                    }
                    return new Truck(licensePlate, manufacturer, year, owner, loadCapacity);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi chuyển đổi giá trị từ chuỗi: " + e.getMessage());
            return null; // Trả về null nếu không thể chuyển đổi
        }

        return null; // Loại phương tiện không hợp lệ
    }

}