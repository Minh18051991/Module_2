package ss7.bai_tap.resizable;

import ss7.thuc_hanh.interface_comparable.Circle;
import ss7.thuc_hanh.interface_comparable.Rectangle;
import ss7.thuc_hanh.interface_comparable.Shape;
import ss7.thuc_hanh.interface_comparable.Square;

public class ResizeableTest {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(4, "black", true);
        shapes[1] = new Rectangle(5, 2);
        shapes[2] = new Square(8);
        for (Shape shape : shapes) {
            System.out.println("Area before resizing :" + shape.toString());
            if (shape instanceof Resizeable) {
                ((Resizeable) shape).resize(10);  // Increase size by 10%
                System.out.println("Area after resizing: " + shape.toString());
            }

        }
    }
}
