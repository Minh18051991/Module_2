package ss3.bai_tap;

import java.util.Scanner;

public class AddElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = 10;
        int[] arr = new int[size];
        System.out.println("Enter the number of elements (max " + size + "): ");
        int n = sc.nextInt();
        if (n > size) {
            System.out.println("The number of elements cannot exceed " + size);
            return;
        }
        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        if (n == size) {
            System.out.println("The array is full. Cannot add more elements.");
            return;
        }

        System.out.println("Enter element to be added: ");
        int number = sc.nextInt();

        int index = -1;
        while (index < 0 || index > n) {
            System.out.println("Enter the index where element should be added (0 to " + n + "): ");
            index = sc.nextInt();
        }

        for (int i = n; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = number;

        System.out.println("Array after adding the element: ");
        for (int i = 0; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
