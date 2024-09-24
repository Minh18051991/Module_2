package ss7.thuc_hanh.interface_comparable;

import ss7.bai_tap.resizable.Resizeable;

public class Rectangle extends Shape  {
    protected double length;
    protected double width;

    public Rectangle(double length, double width) {
        super("red", false);
        this.length = 1;
        this.width = 1;
    }

    public Rectangle() {
        super("red", false);
    }

    public Rectangle(double length, double width, String color) {
        super("red", false);
        this.length = length;
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * length + 2 * width;
    }

    @Override
    public void resize(double percent) {
        this.length *= (1 + percent / 100);
        this.width *= (1 + percent / 100);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                "} " + super.toString() +
                "the Area is " + getArea() +
                "the perimeter is " + getPerimeter();
    }
}
