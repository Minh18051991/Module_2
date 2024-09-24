package ss11.bai_tap.dao_nguoc_phan_tu;

import java.util.Stack;

public class ReserveArray {
    public static void reverseArray(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            stack.push(num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
    }
  public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        reverseArray(arr);
        System.out.println("Array reversed:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

  }
}
