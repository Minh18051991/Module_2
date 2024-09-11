package ss3.bai_tap;

import java.util.Scanner;

public class RemoveElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements (N): ");
        int N = sc.nextInt();
        int[] array = new int[N];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("Enter the element X to be deleted: ");
        int X = sc.nextInt();
        int index_delete = -1;
        for (int i = 0; i < N; i++) {
            if (array[i] == X) {
                index_delete = i;
                break;
            }
        }
        if (index_delete == -1) {
            System.out.println("Element X not found in the array.");
        } else {
            for (int i = index_delete; i < N - 1; i++) {
                array[i] = array[i + 1];
            }
            System.out.println("Array after deletion: ");
            for (int i = 0; i < N - 1; i++) {
                System.out.print(array[i] + " ");
            }
        }
        sc.close();
    }
}
