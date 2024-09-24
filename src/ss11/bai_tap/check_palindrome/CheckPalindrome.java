package ss11.bai_tap.check_palindrome;

import java.util.LinkedList;
import java.util.Queue;

public class CheckPalindrome {
    public static boolean isPalindrome(String str) {
        Queue<Character> charQueue = new LinkedList<>();

        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                charQueue.add(Character.toLowerCase(c));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!charQueue.isEmpty()) {
            sb.append(charQueue.poll());
        }

        // So sánh chuỗi gốc với chuỗi đảo ngược
     return sb.toString().equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(str));
        String str2 = "madam";
        System.out.println(isPalindrome(str2));
        String str3 = "Hello";
        System.out.println(isPalindrome(str3));
    }
}