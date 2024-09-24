package ss7.thuc_hanh.interface_comparable;

import java.util.Arrays;

public class CircleComparatorTest {
    public static void main(String[] args) {
        Circle[] circles = new Circle[3];
        circles[0] = new Circle(5,"red",false);
        circles[1] = new Circle(6);
        circles[2] = new Circle(4,"blue",true);
        for (Circle circle : circles) {
            System.out.println(circle);
        }
        CircleComparator circleComparator = new CircleComparator();
        Arrays.sort(circles, circleComparator);
        System.out.println("After sorting: ");
        for(Circle circle : circles) {
            System.out.println(circle);
        }
    }
}
