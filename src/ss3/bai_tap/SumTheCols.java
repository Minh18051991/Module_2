package ss3.bai_tap;

import java.util.Scanner;

public class SumTheCols {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows : ");
        int rows = sc.nextInt();
        System.out.println("Enter the number of columns : ");
        int cols = sc.nextInt();
        int[][] arr = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the element : ");
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println("what the columns you want to calculate : ");
        int col = sc.nextInt();
        if (col < 0 || col >= cols) {
            System.out.println("The columns you want to calculate is not valid");
        } else {
            int sum = 0;
            for (int i = 0; i < rows; i++) {
                sum += arr[i][col];
            }
            System.out.println("the summary of column " + col + " is " + sum);
        }
    }
}
