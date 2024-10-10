package game_shop_management.util;

import java.util.regex.Pattern;
import game_shop_management.validate.InvalidPhoneNumberException;

public class PhoneNumberUtils {

    // Biểu thức regex đơn giản hơn cho số điện thoại
    private static final String PHONE_REGEX = "^\\d{10,11}$"; // Kiểm tra từ 10 đến 11 chữ số

    public static boolean isValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (!Pattern.matches(PHONE_REGEX, phoneNumber)) {
            throw new InvalidPhoneNumberException("Số điện thoại không hợp lệ: " + phoneNumber);
        }
        return true; // Số điện thoại hợp lệ
    }

    public static String formatPhoneNumber(String phoneNumber) {
        // Kiểm tra xem số điện thoại có đúng 10 hoặc 11 chữ số không
        try {
            if (isValidPhoneNumber(phoneNumber)) {
                // Định dạng theo kiểu XXX-XXXX-XXXX
                return phoneNumber.substring(0, 3) + "-" +
                        phoneNumber.substring(3, 7) + "-" +
                        phoneNumber.substring(7);
            }
        } catch (InvalidPhoneNumberException e) {
            throw new IllegalArgumentException("Số điện thoại phải có 10 hoặc 11 chữ số.");
        }
        return phoneNumber; // Trả về số điện thoại nếu không hợp lệ
    }
}