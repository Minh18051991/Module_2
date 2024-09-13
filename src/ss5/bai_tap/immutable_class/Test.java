package ss5.bai_tap.immutable_class;

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        s1.setName("minh2");
        s2.setName("Dung");
        System.out.println(s1);
        System.out.println(s2);
    }
}
