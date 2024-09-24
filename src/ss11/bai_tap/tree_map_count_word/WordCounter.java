package ss11.bai_tap.tree_map_count_word;

import java.util.Scanner;
import java.util.TreeMap;

public class WordCounter {
    public static void main(String[] args) {
        TreeMap<String, Integer> wordCountMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        String inputText = scanner.nextLine();
        String[] words = inputText.toLowerCase().split("\\s+");
        for (String word : words) {
            if (wordCountMap.containsKey(word) &&!word.isEmpty() &&!word.matches("\\d+")) {
              wordCountMap.put(word, wordCountMap.get(word) + 1);
              } else {
              wordCountMap.put(word, 1);
            }
        }
        System.out.println("Số lần xuất hiện của từng từ:");
        for (String word : wordCountMap.keySet()) {
            System.out.println(word + ": " + wordCountMap.get(word));
        }
        scanner.close();
    }
}
