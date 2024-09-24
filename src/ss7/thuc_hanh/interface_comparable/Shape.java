package ss7.thuc_hanh.interface_comparable;

import ss7.bai_tap.colorable.Colorable;
import ss7.bai_tap.resizable.Resizeable;

public class Shape implements Resizeable, Colorable {
    private String color = "black";
    private boolean filled = true;

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public Shape() {
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    @Override
    public String toString() {
        return "the shape is " + getColor() +
                " and " + (isFilled() ? " filled " : " unfilled ");
    }

    @Override
    public void resize(double percent) {
    }

    @Override
    public void howToColor() {

    }
}
