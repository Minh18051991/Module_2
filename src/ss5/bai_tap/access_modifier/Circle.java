package ss5.bai_tap.access_modifier;

public class Circle {
    private double radius = 1.0;
    private String color = "Red";

    public Circle() {
    };
    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return radius * radius * Math.PI;
    }
}
