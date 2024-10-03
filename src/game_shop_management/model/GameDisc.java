package game_shop_management.model;

public class GameDisc extends Product {
    private String genre;

    // Constructor đầy đủ với các tham số cần thiết
    public GameDisc(String id, String name, double price, boolean isRented, String manufacturer, String platform, String genre, int quantity) {
        super(id, name, price, isRented, manufacturer, platform, quantity, null, null, 0.0, 0, 0.0); // Gọi constructor của lớp cha
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