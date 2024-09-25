package ss13.recursive_binary_research;

import java.util.Arrays;
import java.util.Scanner;

public class RecursiveBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int input = Integer.parseInt(sc.nextLine());
        int[] array = new int[input];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(sc.nextLine());
        }
        Arrays.sort(array);
        System.out.println("Array after sort : " + Arrays.toString(array));
        System.out.print("Enter the number to search: ");
        int target = Integer.parseInt(sc.nextLine());
        int index = recursiveBinarySearch(array, target, 0, array.length - 1);
        if (index == -1) {
            System.out.println(target + " not found in the array.");
        } else {
            System.out.println(target + " found at index " + index);
        }
        sc.close();
    }

    public static int recursiveBinarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}