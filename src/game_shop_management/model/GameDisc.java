package game_shop_management.model;

public class GameDisc extends Product {
    private String genre;

    public GameDisc(String name, double price, boolean isRented, String manufacturer, String platform, String genre, int quantity) {
        super(name, price, isRented, manufacturer, platform, quantity); // Sử dụng constructor chính xác
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return super.toString() + ", Thể loại: " + genre;
    }
}