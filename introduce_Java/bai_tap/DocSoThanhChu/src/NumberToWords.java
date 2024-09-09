import java.util.Scanner;

public class NumberToWords {
    public static String numberToWords(int num) {
        if (num < 0 || num > 999) return "Out of ability";
        if (num == 0) return "zero";

        String result = "";

        if (num >= 100) {
            switch (num / 100) {
                case 1: result += "one hundred";
                break;
                case 2: result += "two hundred";
                break;
                case 3: result += "three hundred";
                break;
                case 4: result += "four hundred";
                break;
                case 5: result += "five hundred";
                break;
                case 6: result += "six hundred";
                break;
                case 7: result += "seven hundred";
                break;
                case 8: result += "eight hundred";
                break;
                case 9: result += "nine hundred";
                break;
            }
            num %= 100;
            if (num > 0) result += " and ";
        }

        if (num >= 10 && num < 20) {
            switch (num) {
                case 10: result += "ten";
                break;
                case 11: result += "eleven";
                break;
                case 12: result += "twelve";
                break;
                case 13: result += "thirteen";
                break;
                case 14: result += "fourteen";
                break;
                case 15: result += "fifteen";
                break;
                case 16: result += "sixteen";
                break;
                case 17: result += "seventeen";
                break;
                case 18: result += "eighteen";
                break;
                case 19: result += "nineteen";
                break;
            }
            return result;
        }

        if (num >= 20) {
            switch (num / 10) {
                case 2: result += "twenty";
                break;
                case 3: result += "thirty";
                break;
                case 4: result += "forty";
                break;
                case 5: result += "fifty";
                break;
                case 6: result += "sixty";
                break;
                case 7: result += "seventy";
                break;
                case 8: result += "eighty";
                break;
                case 9: result += "ninety";
                break;
            }
            num %= 10;
            if (num > 0) result += " ";
        }
        if (num > 0 ) {
            switch (num) {
                case 1: result += "one";
                break;
                case 2: result += "two";
                break;
                case 3: result += "three";
                break;
                case 4: result += "four";
                break;
                case 5: result += "five";
                break;
                case 6: result += "six";
                break;
                case 7: result += "seven";
                break;
                case 8: result += "eight";
                break;
                case 9: result += "nine";
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number (0-999): ");
        int number = scanner.nextInt();
        System.out.println(numberToWords(number));
    }
}
