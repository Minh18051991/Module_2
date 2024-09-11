package ss3.bai_tap;

import java.util.Scanner;

public class SumOfDiagonal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter row of the array: ");
        int row = sc.nextInt();
        System.out.println("Enter column of the array: ");
        int column = sc.nextInt();
        int[][] matrix = new int[row][column];
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.println("Enter the elements of the array: ");
                matrix[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < row; i++) {
            sum += matrix[i][i];
        }
        System.out.println("The sum of the diagonal is: " + sum);
    }
}
