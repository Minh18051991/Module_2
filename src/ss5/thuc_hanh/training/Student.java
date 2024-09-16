package ss5.thuc_hanh.training;

public class Student extends Human {
    private int classYear;
    private String major;

    public Student(String name, int age, String gender, int classYear, String major) {
        super(name, age, gender);
        this.classYear = classYear;
        this.major = major;
    }
    public int getClassYear() {
        return classYear;
    }
}
