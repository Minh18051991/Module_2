package ss4.bai_tap.stop_watch;

public class Fan {
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;

    private int speed = SLOW;
    private boolean on = false;
    private double radius = 5.0;
    private String color = "blue";
    @Override
    public String toString() {
        if (on) {
            return "Fan is on: Speed: " + speed + ", Color: " + color + ", Radius: " + radius;
        } else {
            return "Fan is off: Color: " + color + ", Radius: " + radius;
        }
    }

    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.speed = FAST;
        fan1.radius = 10.0;
        fan1.color = "yellow";
        fan1.on = true;

        Fan fan2 = new Fan();
        fan2.speed = MEDIUM;
        fan2.radius = 5.0;
        fan2.color = "blue";
        fan2.on = false;
        System.out.println(fan1.toString());
        System.out.println(fan2.toString());
    }
}
