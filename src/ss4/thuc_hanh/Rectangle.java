package ss4.thuc_hanh;

public class Rectangle {
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return 2 * width * height;
    }
    public String display() {
       return "this rectangle of area is " + getArea() + " and perimeter is : " + getPerimeter();
    }
}
