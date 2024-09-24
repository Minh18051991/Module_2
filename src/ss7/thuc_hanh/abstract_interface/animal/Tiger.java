package ss7.thuc_hanh.abstract_interface.animal;

import ss7.thuc_hanh.abstract_interface.edible.Edible;

public class Tiger extends Animal implements Edible {
    @Override
    public String speak() {
        return "gừ gừ";
    }

    @Override
    public String HowToEat() {
        return "can be fried";
    }
}
