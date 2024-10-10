package bai_thi_module_2.utils;

import bai_thi_module_2.model.AuthMobile;
import bai_thi_module_2.model.ImportedMobile;
import bai_thi_module_2.model.Mobile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {
    public static List<Mobile> loadMobiles(String filePath) {
        List<Mobile> mobiles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Mobile mobile = createMobileFromCSV(parts);
                if (mobile != null) {
                    mobiles.add(mobile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mobiles;
    }

    public static void saveMobiles(List<Mobile> mobiles, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Mobile mobile : mobiles) {
                writer.write(mobile.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Mobile createMobileFromCSV(String[] parts) {
        if (parts.length < 5) {
            return null; // Dữ liệu không hợp lệ
        }

        String id = parts[0];
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        int stock = Integer.parseInt(parts[3]);
        String manufacturer = parts[4];

        // Kiểm tra loại di động
        if (id.startsWith("A")) {
            String warrantyPeriod = parts[5];
            String warrantyScope = parts[6];
            return new AuthMobile(id, name, price, stock, manufacturer, warrantyPeriod, warrantyScope);
        } else if (id.startsWith("B")) {
            String importedCountry = parts[5];
            boolean isRepaired = Boolean.parseBoolean(parts[6]);
            return new ImportedMobile(id, name, price, stock, manufacturer, importedCountry, isRepaired);
        }

        return null; // Loại di động không hợp lệ
    }
}