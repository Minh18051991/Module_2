package ss6.bai_tap.circle;

public class Cylinder extends Circle {
    private double height = 10;

    public Cylinder() {
    }

    public Cylinder(double radius, double height) {
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * getRadius() * height + 2 * super.getArea();
    }
    public double getVolume() {
        return super.getArea() * height;
    }
}
