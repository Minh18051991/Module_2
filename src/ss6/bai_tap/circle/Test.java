package ss6.bai_tap.circle;

public class Test {
    public static void main(String[] args) {
        Cylinder cyl = new Cylinder();
        cyl.setRadius(10);
        System.out.println(cyl.getArea());
        Circle circle = new Circle();
        System.out.println(circle.getArea());
    }
}
