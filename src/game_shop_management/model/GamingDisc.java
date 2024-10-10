package game_shop_management.model;

public class GamingDisc extends Products {
    private String genre;

    public GamingDisc(String id, String name, double price, String manufacturer, String platform, int stock, String type, String genre) {
        super(id, name, price, manufacturer, platform, stock, type);
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return super.toString() + ", Genre: " + genre;
    }
}

