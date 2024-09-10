package ss2_loop.bai_tap.shape_printer;

import java.util.Scanner;

public class ShapePrinter {
    public static void printRectangle(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    public static void printSquareTriangleBottomLeft(int size) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    public static void printSquareTriangleTopLeft(int size) {
        for (int i = size; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    public static void printIsoscelesTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = i; j < height; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Print the rectangle");
            System.out.println("2. Print the square triangle (bottom-left)");
            System.out.println("3. Print the square triangle (top-left)");
            System.out.println("4. Print isosceles triangle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter width: ");
                    int width = scanner.nextInt();
                    System.out.print("Enter height: ");
                    int height = scanner.nextInt();
                    printRectangle(width, height);
                    break;
                case 2:
                    System.out.print("Enter size of triangle: ");
                    int size1 = scanner.nextInt();
                    printSquareTriangleBottomLeft(size1);
                    break;
                case 3:
                    System.out.print("Enter size of triangle: ");
                    int size2 = scanner.nextInt();
                    printSquareTriangleTopLeft(size2);
                    break;
                case 4:
                    System.out.print("Enter height of isosceles triangle: ");
                    int isoHeight = scanner.nextInt();
                    printIsoscelesTriangle(isoHeight);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        } while (choice != 5);
    }
}
