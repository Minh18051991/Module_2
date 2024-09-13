package ss5.bai_tap.access_modifier;

public class TestCircle {
    public static void main(String[] args) {
        Circle c = new Circle();
        c.setRadius(4);
        System.out.println(c.getRadius() + ", " + c.getArea());
    }
}
