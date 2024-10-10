package electricity_bill_management.utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {

    public static <T> List<T> loadData(String filePath, DataParser<T> parser) {
        List<T> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                T data = parser.parse(line);
                if (data != null) {
                    dataList.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static <T> void saveData(String filePath, List<T> dataList, DataFormatter<T> formatter) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (T data : dataList) {
                bw.write(formatter.format(data));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface DataParser<T> {
        T parse(String line);
    }

    public interface DataFormatter<T> {
        String format(T data);
    }
}