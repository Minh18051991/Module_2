package ss13.max_size_string;

import java.util.Scanner;

public class MaxAscendingSubstring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vào một chuỗi bất kỳ: ");
        String input = scanner.nextLine();

        String result = findMaxAscendingSubstring(input);
        System.out.println("Chuỗi con tăng dần lớn nhất là: " + result);

        scanner.close();
    }

    private static String findMaxAscendingSubstring(String input) {
        StringBuilder result = new StringBuilder();

        if (input.length() > 0) {
            result.append(input.charAt(0));  // Bắt đầu từ ký tự đầu tiên
        }

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) > result.charAt(result.length() - 1)) {
                result.append(input.charAt(i));  // Thêm ký tự nếu lớn hơn ký tự trước đó
            }
        }

        return result.toString();
    }
}
