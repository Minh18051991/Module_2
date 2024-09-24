package ss10.bai_tap;

public class TestMyArrayList {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");
        list.add("World");

        System.out.println("Size: " + list.size());
        System.out.println("First element: " + list.get(0));
        System.out.println("Is empty: " + list.isEmpty());

        list.clear();
        System.out.println("Size after clear: " + list.size());
    }
}
