package ss10.bai_tap;
import java.util.Arrays;

public class MyArrayList<E> {
    private int size;
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    public MyArrayList(int capacity) {
        elements = new Object[capacity];
        size = 0;
    }
    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    private void ensureCapacity() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
