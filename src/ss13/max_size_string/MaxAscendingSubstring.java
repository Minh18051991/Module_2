package ss13.max_size_string;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MaxAscendingSubstring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập vào một chuỗi bất kỳ: ");
        String input = scanner.nextLine();

        String result = sortedUniqueSubstring(input);
        System.out.println("Chuỗi ký tự con sắp xếp theo thứ tự từ bé đến lớn là: " + result);

        scanner.close();
    }

    private static String sortedUniqueSubstring(String input) {
        Set<Character> uniqueChars = new TreeSet<>();

        // Thêm các ký tự vào TreeSet để tự động loại bỏ trùng lặp và sắp xếp
        for (char c : input.toCharArray()) {
            uniqueChars.add(c);
        }

        // Xây dựng chuỗi từ các ký tự duy nhất đã được sắp xếp
        StringBuilder sortedString = new StringBuilder();
        for (char c : uniqueChars) {
            sortedString.append(c);
        }

        return sortedString.toString();
    }
}