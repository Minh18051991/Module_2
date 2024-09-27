package ss15.illegalTriangleExpression;

import java.util.Scanner;

public class IllegalTriangleExpressionClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter side 1: ");
                int side1 = Integer.parseInt(sc.nextLine());
                System.out.print("Enter side 2: ");
                int side2 = Integer.parseInt(sc.nextLine());
                System.out.print("Enter side 3: ");
                int side3 = Integer.parseInt(sc.nextLine());

                isTriangle(side1, side2, side3);
                System.out.println("Valid triangle expression.");
                validInput = true; //
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid integer values.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void isTriangle(int side1, int side2, int side3) {
        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1 || side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new IllegalArgumentException("Invalid triangle expression. Please enter valid sides.");
        }
    }
}