package bai_thi_module_2.utils;

import bai_thi_module_2.model.AuthMobile;
import bai_thi_module_2.model.ImportedMobile;
import bai_thi_module_2.model.Mobile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {

    // Phương thức để lưu danh sách điện thoại vào file
    public static void saveMobiles(List<Mobile> mobiles, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String header = "id,name,price,stock,manufacturer,warrantyPeriod,warrantyCoverage,importedCountry,isRepaired";
            writer.write(header);
            writer.newLine();

            for (Mobile mobile : mobiles) {
                writer.write(mobile.toCSV());
                writer.newLine();
            }
            System.out.println("Dữ liệu đã được lưu vào file: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // Phương thức để tải danh sách điện thoại từ file
    public static List<Mobile> loadMobiles(String filePath) {
        List<Mobile> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Bỏ qua tiêu đề
            reader.readLine();

            // Đọc từng dòng và tạo đối tượng
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String type = fields[5]; // Loại điện thoại

                Mobile mobile;
                if ("AuthMobile".equals(type)) {
                    mobile = new AuthMobile(fields[0], fields[1], Double.parseDouble(fields[2]),
                            Integer.parseInt(fields[3]), fields[4], fields[6], fields[7]);
                } else {
                    mobile = new ImportedMobile(fields[0], fields[1], Double.parseDouble(fields[2]),
                            Integer.parseInt(fields[3]), fields[4], fields[6], Boolean.parseBoolean(fields[7]));
                }
                items.add(mobile);
            }
            System.out.println("Dữ liệu đã được tải từ file: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return items;
    }
}