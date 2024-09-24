package transport_management.model;

public class Manufacturer {
    private final String code;
    private final String name;
    private final String country;

    public Manufacturer(String code, String name, String country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }
}