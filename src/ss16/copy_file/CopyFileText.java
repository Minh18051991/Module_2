package ss16.copy_file;

import java.io.*;

public class CopyFileText {
    public static void main(String[] args) {
        // Cập nhật địa chỉ tệp nguồn và tệp đích trực tiếp ở đây
        String sourceFile = "D:/Codegym/Module 2/src/ss16/copy_file/source.txt";
        String targetFile = "D:/Codegym/Module 2/src/ss16/copy_file/target.txt";
        File srcFile = new File(sourceFile);
        File tgtFile = new File(targetFile);


        if (!srcFile.exists()) {
            System.out.println("Tệp nguồn không tồn tại.");
            return;
        }


        if (tgtFile.exists()) {
            System.out.println("Tệp đích đã tồn tại. Vui lòng chọn tên khác.");
            return;
        }

        // Sao chép tệp
        try (Reader reader = new FileReader(srcFile); Writer writer = new FileWriter(tgtFile)) {
            int totalChars = 0;
            int charRead;
            while ((charRead = reader.read()) != -1) {
                writer.write(charRead);
                totalChars++;
            }

            System.out.println("Sao chép hoàn tất. Số ký tự trong tệp: " + totalChars);
        } catch (IOException e) {
            System.out.println("Lỗi khi sao chép tệp: " + e.getMessage());
        }
    }
}