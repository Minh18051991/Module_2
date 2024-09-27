package transport_management.model;

public class Oto extends Vehicle {
    private final int numSeats;
    private final String type;

    public Oto(String licensePlate, Manufacturer manufacturer, String year, String owner, int numSeats, String type) {
        super(licensePlate, manufacturer, year, owner);
        validateNumSeats(numSeats);
        validateType(type);
        this.numSeats = numSeats;
        this.type = type;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public String getType() {
        return type;
    }

    private void validateNumSeats(int numSeats) {
        if (numSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be a positive number.");
        }
    }

    private void validateType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Car: License Plate: " + getLicensePlate() + ", Manufacturer: " + getManufacturer().getName() +
                ", Year: " + getYear() + ", Owner: " + getOwner() +
                ", Number of Seats: " + numSeats + ", Type: " + type);
    }
}