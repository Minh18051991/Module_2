package ss5.bai_tap.immutable_class;

public class Student {
    private String name = "john";
    private String classes = "C02";
    public Student() {
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", classes='" + classes + '\'' +
                '}';
    }
}
