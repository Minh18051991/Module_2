package game_shop_management.model;


public class GameDisc extends Product {
    private String genre;

    public GameDisc(String name, double price, String manufacturer, String platform, String genre) {
        super(name, price, manufacturer, platform);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "GameDisc: " + getName() + ", Giá: " + getPrice() + ", Nhà sản xuất: " + getManufacturer() + ", Nền tảng: " + getPlatform() + ", Thể loại: " + genre;
    }
}




