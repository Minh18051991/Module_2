package ss4.bai_tap.quadratic_equation;

public class QuadraticEquation {
    private double a, b, c;
    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    public double getDiscriminant(){
        return b*b - 4*a*c;
    }
    public double getRoot1(){
        if (getDiscriminant() >= 0){
            return (-b + Math.pow(getDiscriminant(), 0.5)) / (2 * a);
        }else {
            return 0;
        }
    }
    public double getRoot2(){
        if (getDiscriminant() >= 0){
            return (-b - Math.pow(getDiscriminant(), 0.5)) / (2 * a);
        }else {
            return 0;
        }
    }
}
