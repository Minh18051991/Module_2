package ss7.thuc_hanh.interface_comparable;


public class Square extends Rectangle  {
    public Square() {
        this.width = 1.0;
        this.length = 1.0;
    }

    public Square(double side) {
        this.width = side;
        this.length = side;
    }

    public double getSide() {
        return this.width;
    }

    public void setSide(double side) {
        this.width = side;
        this.length = side;
    }

    @Override
    public void setWidth(double side) {
        setSide(side);
    }
    @Override
    public void setLength(double side) {
        setSide(side);
    }
    @Override
    public void resize(double percent) {
        setSide(getSide() * (1 + percent / 100));
    }
    @Override
    public String toString() {
        return "A Square with side=" + getSide() + ", which is a subclass of " + super.toString();
    }
    @Override
    public void howToColor() {
        System.out.println("Color all four sides");
    }
}
