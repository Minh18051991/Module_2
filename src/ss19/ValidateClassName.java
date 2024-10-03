package ss19;

public class ValidateClassName {
    public static boolean isValidClassName(String className) {
        return className.matches("^[CAP]\\d{4}[GHIK]$");
    }

    public static void main(String[] args) {
        System.out.println(isValidClassName("C2234G")); // true
        System.out.println(isValidClassName("CA1234H")); // false
        System.out.println(isValidClassName("C1234K")); // true
        System.out.println(isValidClassName("CA1234I")); // false
        System.out.println(isValidClassName("CA1234")); // false
        System.out.println(isValidClassName("CA12345G")); // false
        System.out.println(isValidClassName("C0223G"));
    }
}
