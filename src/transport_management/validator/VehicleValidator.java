package transport_management.validator;

import transport_management.model.Manufacturer;

import java.util.Calendar;
import java.util.List;

public class VehicleValidator {

    public static boolean isLicensePlateValid(String licensePlate) {
        return licensePlate != null && licensePlate.matches("^[0-9]{2}[A-Z]-[0-9]{5}$");
    }

    public static boolean isYearValid(String year) {
        try {
            int yearInt = Integer.parseInt(year);
            // Kiểm tra xem năm có lớn hơn 1885 và không lớn hơn năm hiện tại
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            return yearInt > 1885 && yearInt <= currentYear;
        } catch (NumberFormatException e) {
            return false; // Trả về false nếu không chuyển đổi được
        }
    }

    public static boolean isNumSeatsValid(int numSeats) {
        return numSeats > 0;
    }

    public static boolean isLoadCapacityValid(int loadCapacity) {
        return loadCapacity > 0;
    }

    public static boolean isEnginePowerValid(int enginePower) {
        return enginePower > 0;
    }

    public static boolean isManufacturerIndexValid(int index, List<Manufacturer> manufacturers) {
        return index >= 0 && index < manufacturers.size();
    }
}