package ss19;

public class ValidatePhoneNumber {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\(\\d{2}\\)-\\(0\\d{9}\\)$");
    }

    public static void main(String[] args) {
        System.out.println(isValidPhoneNumber("(84)-(0123144442)"));
        System.out.println(isValidPhoneNumber("84-0123-1444-42"));
        System.out.println(isValidPhoneNumber("43-0123144442"));
        System.out.println(isValidPhoneNumber("84-0123144431"));
        System.out.println(isValidPhoneNumber("(84)-(0123144442)"));

    }
}
