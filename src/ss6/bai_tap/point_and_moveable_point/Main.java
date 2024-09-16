package ss6.bai_tap.point_and_moveable_point;

public class Main {
    public static void main(String[] args) {
        MoveablePoint mp1 = new MoveablePoint(1.0f, 2.0f, 0.5f, 0.5f);
        System.out.println(mp1);

        mp1.move();
        System.out.println("After moving: " + mp1);
        mp1.move();
        System.out.println("After moving again: " + mp1);
        mp1.setSpeed(4f, 6f);
        float[] speed = mp1.getSpeed();
        System.out.println("Speed: xSpeed = " + speed[0] + ", ySpeed = " + speed[1]); // Expected output: Speed: xSpeed = 1.0, ySpeed = 1.0


    }
}
