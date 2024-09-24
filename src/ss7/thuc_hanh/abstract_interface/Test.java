package ss7.thuc_hanh.abstract_interface;

import ss7.thuc_hanh.abstract_interface.animal.Animal;
import ss7.thuc_hanh.abstract_interface.animal.Chicken;
import ss7.thuc_hanh.abstract_interface.animal.Tiger;
import ss7.thuc_hanh.abstract_interface.edible.Edible;
import ss7.thuc_hanh.abstract_interface.fruit.Apple;
import ss7.thuc_hanh.abstract_interface.fruit.Orange;

public class Test {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        animals[0] = new Tiger();
        animals[1] = new Chicken();
        for (Animal animal : animals) {
            System.out.println(animal.speak());
            if(animal instanceof Tiger) {
                Edible edible = (Tiger) animal;
                System.out.println(edible.HowToEat());
            }
        }
    }
}
