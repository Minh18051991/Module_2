package ss16.read_file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Country {
    private int id;
    private String code;
    private String name;

    public Country(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{id=" + id + ", code='" + code + "', name='" + name + "'}";
    }
}

public class CSVreader {
    public static void main(String[] args) {
        String filePath = "D:\\Codegym\\Module 2\\src\\ss16\\read_file\\country.csv";
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Tệp không tồn tại: " + filePath);
            return;
        }

        List<Country> countries = readCSV(filePath);
        if (!countries.isEmpty()) {
            countries.forEach(System.out::println);
        } else {
            System.out.println("Không có quốc gia nào được tìm thấy trong tệp.");
        }
    }

    public static List<Country> readCSV(String filePath) {
        List<Country> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    try {
                        result.add(new Country(Integer.parseInt(values[0]), values[1], values[2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng số cho ID: " + values[0]);
                    }
                } else {
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp: " + e.getMessage());
        }
        return result;
    }
}