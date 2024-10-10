package game_shop_management.model;

public class GamingAccessories extends Products {
    private String descripton;

    public GamingAccessories(String id, String name, double price, String manufacturer, String platform, int stock, String type, String descripton) {
        super(id, name, price, manufacturer, platform, stock, type);
        this.descripton = descripton;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    @Override
    public String toString() {
        return super.toString() + ", Mô tả: " + descripton;
    }

}
