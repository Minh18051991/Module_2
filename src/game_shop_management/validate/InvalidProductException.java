package game_shop_management.validate;

public class InvalidProductException extends Exception  {
    public InvalidProductException(String errorMessage) {
        super(errorMessage);
    }
}
