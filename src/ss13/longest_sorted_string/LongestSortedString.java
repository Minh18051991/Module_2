package ss13.longest_sorted_string;

import java.util.Scanner;

public class LongestSortedString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập chuỗi từ bàn phím
        System.out.print("Nhập vào chuỗi: ");
        String input = scanner.nextLine();

        // Tìm chuỗi con có độ dài tăng dần lớn nhất
        String longestSorted = findLongestSortedString(input);
        System.out.println("Chuỗi có độ dài tăng dần nhiều nhất: " + longestSorted);

        scanner.close();
    }

    public static String findLongestSortedString(String input) {
        String longestSorted = "";
        String currentSorted = "";

        currentSorted += input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) > input.charAt(i - 1)) {
                currentSorted += input.charAt(i);
            } else {

                if (currentSorted.length() > longestSorted.length()) {
                    longestSorted = currentSorted;
                }
                currentSorted = "" + input.charAt(i);
            }
        }

        if (currentSorted.length() > longestSorted.length()) {
            longestSorted = currentSorted;
        }

        return longestSorted;
    }
}
