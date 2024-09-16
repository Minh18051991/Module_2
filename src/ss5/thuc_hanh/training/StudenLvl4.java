package ss5.thuc_hanh.training;

public class StudenLvl4 extends Student {
    private int skill;


    public void killer(){
        System.out.println("kill");
    }

    public StudenLvl4(String name, int age, String gender, int classYear, String major) {
        super(name, age, gender, classYear, major);
    }
}
