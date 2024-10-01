package transport_management.utils;

import transport_management.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {

    public static void saveManufacturersToCSV(List<Manufacturer> manufacturers, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Manufacturer manufacturer : manufacturers) {
                writer.write(manufacturerToCSV(manufacturer));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Manufacturer> loadManufacturers(String filePath) {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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

    public static void saveVehiclesToCSV(List<Vehicle> vehicles, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicleToCSV(vehicle));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Vehicle> loadVehiclesFromCSV(String filePath, List<Manufacturer> manufacturers) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Vehicle vehicle = csvToVehicle(line, manufacturers);
                if (vehicle != null) {
                    vehicles.add(vehicle);
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Không tìm thấy dữ liệu phương tiện có sẵn. Bắt đầu mới.");
        }
        return vehicles;
    }

    private static String manufacturerToCSV(Manufacturer manufacturer) {
        return manufacturer.getCode() + "," + manufacturer.getName() + "," + manufacturer.getCountry();
    }

    private static String vehicleToCSV(Vehicle vehicle) {
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

    private static Vehicle csvToVehicle(String csvLine, List<Manufacturer> manufacturers) {
        String[] fields = csvLine.split(",");
        if (fields.length < 5) return null;

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

        try {
            switch (vehicleType.toLowerCase()) {
                case "oto":
                    if (fields.length >= 6) {
                        return new Oto(licensePlate, manufacturer, year, owner, Integer.parseInt(fields[5].trim()), "Sedan");
                    }
                    break;
                case "motocycle":
                    if (fields.length >= 6) {
                        return new Motocycle(licensePlate, manufacturer, year, owner, Integer.parseInt(fields[5].trim()));
                    }
                    break;
                case "truck":
                    if (fields.length >= 6) {
                        int loadCapacity = Integer.parseInt(fields[5].trim());
                        return new Truck(licensePlate, manufacturer, year, owner, loadCapacity);
                    }
                    break;
                default:
                    System.out.println("Loại phương tiện không hợp lệ: " + vehicleType);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi chuyển đổi giá trị từ chuỗi: " + e.getMessage());
        }

        return null; // Loại phương tiện không hợp lệ
    }
}