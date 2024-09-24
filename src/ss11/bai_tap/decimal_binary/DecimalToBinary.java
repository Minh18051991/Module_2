package ss11.bai_tap.decimal_binary;

import java.util.Stack;

public class DecimalToBinary {
    public static String decimalToBinary(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            stack.push(decimal % 2);
            decimal /= 2;
        }
        StringBuilder binaryNumber = new StringBuilder();
        while (!stack.isEmpty()) {
            binaryNumber.append(stack.pop());
        }
        return binaryNumber.toString();
    }
    public static void main(String[] args) {
        int decimalNumber = 30 ;
        System.out.println("Số nhị phân của  " + decimalNumber + " là :  " + decimalToBinary(decimalNumber));
    }
}
