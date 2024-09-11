package ss3.bai_tap;

import java.util.Arrays;
import java.util.Scanner;

public class MergeArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements for array1 (N1): ");
        int N1 = sc.nextInt();
        int[] array1 = new int[N1];
        System.out.println("Enter the elements of the array1: ");
        for (int i = 0; i < N1; i++) {
            array1[i] = sc.nextInt();
        }
        System.out.println("Enter the number of elements for N2 (N2): ");
        int N2 = sc.nextInt();
        int[] array2 = new int[N2];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < N1; i++) {
            array2[i] = sc.nextInt();
        }
        int[] array3 = new int[N2 + N1];
        for (int i = 0; i < N1; i++) {
            array3[i] = array1[i];
        }
        for (int i = 0; i < N2; i++) {
            array3[N1 + i] = array2[i];
        }
        System.out.println(Arrays.toString(array3));
    }

}

