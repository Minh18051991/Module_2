package game_shop_management.util;

import game_shop_management.model.*;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static <T> void saveToFile(List<T> items, String filePath, String header) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header);
            writer.newLine();

            for (T item : items) {
                writer.write(itemToCSV(item));
                writer.newLine();
            }
            System.out.println("Dữ liệu đã được lưu vào file: " + filePath);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // Phương thức đọc danh sách đối tượng từ file CSV
    public static <T> List<T> loadFromFile(String filePath, ItemParser<T> parser) {
        List<T> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Bỏ qua tiêu đề
            reader.readLine();

            // Đọc từng dòng và tạo đối tượng
            while ((line = reader.readLine()) != null) {
                T item = parser.parse(line);
                items.add(item);
            }
            System.out.println("Dữ liệu đã được tải từ file: " + filePath);
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return items;
    }
    public interface ItemParser<T> {
        T parse(String line);
    }
    public static <T> String itemToCSV(T item) {
        if (item instanceof GamingPC pc) {
            return String.format("%s,%s,%s,%s,%s,%d,%s,%s,%s,%s,%s",
                    pc.getId(), pc.getName(), pc.getPrice(),
                    pc.getManufacturer(), pc.getPlatform(), pc.getStock(),
                    pc.getType(), pc.getCPU(), pc.getGPU(), pc.getRAM(), pc.getStorage());
        } else if (item instanceof GamingConsole console) {
            return String.format("%s,%s,%s,%s,%s,%d,%s,%s",
                    console.getId(), console.getName(), console.getPrice(),
                    console.getManufacturer(), console.getPlatform(), console.getStock(),
                    console.getType(), console.getStorageCapacity());
        } else if (item instanceof GamingDisc disc) {
            return String.format("%s,%s,%s,%s,%s,%d,%s,%s",
                    disc.getId(), disc.getName(), disc.getPrice(),
                    disc.getManufacturer(), disc.getPlatform(), disc.getStock(),
                    disc.getType(), disc.getGenre());
        } else if (item instanceof GamingAccessories accessories) {
            return String.format("%s,%s,%s,%s,%s,%d,%s,%s",
                    accessories.getId(), accessories.getName(), accessories.getPrice(),
                    accessories.getManufacturer(), accessories.getPlatform(), accessories.getStock(),
                    accessories.getType(), accessories.getDescripton());
        } else if (item instanceof UserModel.User user) {  // Thêm điều kiện cho User
            return user.toCSV();  // Gọi phương thức toCSV của User
        }
        return "";
    }

}