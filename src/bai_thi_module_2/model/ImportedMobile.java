package bai_thi_module_2.model;

public class ImportedMobile extends Mobile {
    private String importedCountry;
    private boolean isRepaired; // true nếu đã sửa chữa, false nếu chưa sửa chữa

    public ImportedMobile(String id, String name, double price, int stock, String manufacturer, String importedCountry, boolean isRepaired) {
        super(id, name, price, stock, manufacturer);
        this.importedCountry = importedCountry;
        this.isRepaired = isRepaired;
    }

    public String getImportedCountry() {
        return importedCountry;
    }

    public void setImportedCountry(String importedCountry) {
        this.importedCountry = importedCountry;
    }

    public boolean isRepaired() {
        return isRepaired;
    }

    public void setRepaired(boolean repaired) {
        isRepaired = repaired;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + String.format(",,%s,%b",
                importedCountry, isRepaired);
    }

    @Override
    public String toString() {
        String statusMessage = isRepaired ? "Đã sửa chữa" : "Chưa sửa chữa";
        return "ImportedMobile{" +
                "importedCountry='" + importedCountry + '\'' +
                ", status='" + statusMessage + '\'' +
                ", id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", stock=" + getStock() +
                ", manufacturer='" + getManufacturer() + '\'' +
                '}';
    }
}