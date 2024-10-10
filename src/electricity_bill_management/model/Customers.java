package electricity_bill_management.model;

public abstract class Customers {
    private String id;
    private String name;

    public Customers(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customers customers = (Customers) obj;
        return id.equals(customers.id); // So sánh theo ID
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
