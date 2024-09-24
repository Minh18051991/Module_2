package ss9.bai_tap;

public class TriangleClassifier {

    public static String classify(int a, int b, int c) {

        if (a <= 0 || b <= 0 || c <= 0 || (a + b <= c) || (a + c <= b) || (b + c <= a)) {
            return "không phải là tam giác";
        }
        if (a == b && b == c) {
            return "tam giác đều";
        }

        if (a == b || b == c || a == c) {
            return "tam giác cân";
        }
        return "tam giác thường";
    }
}

