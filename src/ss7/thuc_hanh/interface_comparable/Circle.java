package ss7.thuc_hanh.interface_comparable;



public class Circle extends Shape  {
    private double radius;

    public Circle(double radius, String color, boolean filled) {
        super("red", false);
        this.radius = radius;
    }
    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle() {
        super("red", false);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double percent) {
        this.radius *= (1 + percent / 100);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                "} " + super.toString() +
                "the Area is " + getArea() +
                "the perimeter is " + getPerimeter();
    }
}
