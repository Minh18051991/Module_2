package ss3.bai_tap;

import java.util.Scanner;

public class MaxIn2dArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int rows = sc.nextInt();
        System.out.println("Enter the number of columns: ");
        int cols = sc.nextInt();

        int[][] array = new int[rows][cols];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = sc.nextInt();
            }
        }
        int max = array[0][0];
        int maxRow = 0;
        int maxCols = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                    maxRow = i;
                    maxCols = j;
                }
            }
        }
        System.out.println("The largest element in the 2D array is: " + max + " vị trí " + maxRow + maxCols);
        sc.close();
    }
}
