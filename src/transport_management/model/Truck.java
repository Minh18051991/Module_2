package transport_management.model;

public class Truck extends Vehicle {
    private final int loadCapacity;

    public Truck(String licensePlate, Manufacturer manufacturer, String year, String owner, int loadCapacity) {
        super(licensePlate, manufacturer, year, owner);
        validateLoadCapacity(loadCapacity);
        this.loadCapacity = loadCapacity;
    }

    private void validateLoadCapacity(int loadCapacity) {
        if (loadCapacity <= 0) {
            throw new IllegalArgumentException("Load capacity must be a positive number.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Truck: License Plate: " + getLicensePlate() + ", Manufacturer: " + getManufacturer().getName() +
                ", Year: " + getYear() + ", Owner: " + getOwner() +
                ", Load Capacity: " + loadCapacity);
    }
}