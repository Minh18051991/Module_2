package ss6.bai_tap.point_2d_3d;

public class Point3d extends Point2d {
    private float z = 0;

    public Point3d(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }



    public Point3d() {
        super();
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    public void setXYZ(float x, float y, float z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public float[] getXYZ(float x, float y, float z) {
        float[] array = new float[3];
        return array;
    }

    @Override
    public String toString() {
        return "Point3d{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + z +
                '}';
    }
}

