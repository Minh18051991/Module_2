package transport_management.model;

import transport_management.validator.VehicleValidator;

public abstract class Vehicle {
    private final String licensePlate;
    private final Manufacturer manufacturer;
    private final String year;
    private final String owner;

    public Vehicle(String licensePlate, Manufacturer manufacturer, String year, String owner) {
        if (!VehicleValidator.isLicensePlateValid(licensePlate)) {
            throw new IllegalArgumentException("License plate is not valid.");
        }
        VehicleValidator.isYearValid(year);
        validateOwner(owner);
        this.licensePlate = licensePlate;
        this.manufacturer = manufacturer;
        this.year = year;
        this.owner = owner;
    }

    private void validateYear(String year) {
        if (year == null || !year.matches("\\d{4}")) {
            throw new IllegalArgumentException("Year must be a 4-digit number.");
        }
    }

    private void validateOwner(String owner) {
        if (owner == null || owner.isEmpty()) {
            throw new IllegalArgumentException("Owner cannot be empty.");
        }
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getYear() {
        return year;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "License Plate: " + licensePlate + ", Manufacturer: " + manufacturer.getName() +
                ", Year: " + year + ", Owner: " + owner;
    }

    public abstract void displayInfo();
}