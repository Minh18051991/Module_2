package ss6.bai_tap.triangle;


public class TriangleTest {
    public static void main(String[] args) {

        Triangle defaultTriangle = new Triangle();
        System.out.println("Default Triangle: ");
        System.out.println(defaultTriangle);


        Triangle triangle1 = new Triangle(3, 4, 5);
        System.out.println("triangle1: ");
        System.out.println(triangle1);


        triangle1.setSide1(6);
        triangle1.setSide2(8);
        triangle1.setSide3(10);
        triangle1.setColor("green");
        System.out.println("Modified triangle:");
        System.out.println(triangle1);


        System.out.println("Perimeter of triangle: " + triangle1.getPerimeter());
        System.out.println("Area of triangle: " + triangle1.getArea());
    }

}
