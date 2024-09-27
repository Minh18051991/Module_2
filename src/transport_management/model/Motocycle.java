package transport_management.model;

public class Motocycle extends Vehicle {
    private final int enginePower;

    public Motocycle(String licensePlate, Manufacturer manufacturer, String year, String owner, int enginePower) {
        super(licensePlate, manufacturer, year, owner);
        validateEnginePower(enginePower);
        this.enginePower = enginePower;
    }

    private void validateEnginePower(int enginePower) {
        if (enginePower <= 0) {
            throw new IllegalArgumentException("Engine power must be a positive number.");
        }
    }

    public int getEnginePower() {
        return enginePower;
    }

    @Override
    public void displayInfo() {
        System.out.println("Motorcycle: License Plate: " + getLicensePlate() + ", Manufacturer: " + getManufacturer().getName() +
                ", Year: " + getYear() + ", Owner: " + getOwner() +
                ", Engine Power: " + enginePower);
    }
}