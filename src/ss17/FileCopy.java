package ss17;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        File sourceFile = new File("D:\\Codegym\\Module 2\\src\\ss17\\country.bin");
        File targetFile = new File("D:\\Codegym\\Module 2\\src\\ss17\\country1.bin");

        // Kiểm tra tồn tại của tệp nguồn
        if (!sourceFile.exists()) {
            System.out.println("Tệp nguồn không tồn tại!");
            return;
        }

        // Kiểm tra tệp đích đã tồn tại
        if (targetFile.exists()) {
            System.out.println("Tệp đích đã tồn tại!");
            return;
        }

        // Sao chép tệp và đếm ký tự từng dòng
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             OutputStream out = new FileOutputStream(targetFile)) {

            String line;
            long totalBytes = 0;

            while ((line = reader.readLine()) != null) {
                // Ghi dòng vào tệp đích
                out.write((line + System.lineSeparator()).getBytes());
                totalBytes += line.getBytes().length + System.lineSeparator().getBytes().length; // Đếm số byte

                // In ra số ký tự của dòng
                System.out.println("Số ký tự trong dòng: " + line.length());
            }

            System.out.println("Đã sao chép " + totalBytes + " byte từ tệp nguồn sang tệp đích.");
        } catch (IOException e) {
            System.out.println("Lỗi xảy ra trong quá trình sao chép: " + e.getMessage());
        }
    }
}