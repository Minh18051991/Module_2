package ss7.thuc_hanh.abstract_interface;

public class Chicken extends Animal implements Edible {
    @Override
    public String speak() {
        return "Chicken :  ò ó o o";
    }

    @Override
    public void HowToEat() {
        System.out.println("could be steam");
    }
}
