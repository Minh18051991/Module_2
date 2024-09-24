package ss11.bai_tap.dao_nguoc_phan_tu;

import java.util.Stack;

public class ReserveString {
    public static String reverseString(String str) {
        Stack<String> wStack = new Stack<>();
        String[] words = str.split("\\s+");
        for (String word : words) {
            wStack.push(word);
        }
        StringBuilder result = new StringBuilder();
        while (!wStack.isEmpty()) {
            result.append(wStack.pop()).append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        String sentence = "Hello  World";
        System.out.println("Original string: " + sentence);
        System.out.println("Reversed string: " + reverseString(sentence));
    }
}
