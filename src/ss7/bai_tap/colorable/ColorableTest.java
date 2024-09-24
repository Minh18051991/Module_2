package ss7.bai_tap.colorable;

import ss7.thuc_hanh.interface_comparable.Circle;
import ss7.thuc_hanh.interface_comparable.Rectangle;
import ss7.thuc_hanh.interface_comparable.Shape;
import ss7.thuc_hanh.interface_comparable.Square;

public class ColorableTest {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(4, "black", true);
        shapes[1] = new Rectangle(5, 2);
        shapes[2] = new Square(8);
        for (Shape shape : shapes) {
            if(shape instanceof Colorable){
                ((Colorable) shape).howToColor();
                System.out.println(shape);
            }
        }
    }
}
