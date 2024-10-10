package game_shop_management.util;

import java.util.regex.Pattern;
import game_shop_management.validate.InvalidEmailException;

public class EmailUtils {

    // Biểu thức regex đơn giản hơn cho địa chỉ email
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValidEmail(String email) throws InvalidEmailException {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new InvalidEmailException("Địa chỉ email không hợp lệ: " + email);
        }
        return true; // Email hợp lệ
    }
}