package ss7.thuc_hanh.abstract_interface.animal;

import ss7.thuc_hanh.abstract_interface.edible.Edible;

public class Chicken extends Animal implements Edible {
    @Override
    public String speak() {
        return "Chicken :  ò ó o o";
    }

    @Override
    public String HowToEat() {
       return "could be steam" ;
    }
}
