package ss10.thuc_hanh;

public class TestMyLinkedList {
    public static void main(String[] args) {
        System.out.println("+++++ TEST ++++");
        MyLinkedList linkedList = new MyLinkedList(); // Sử dụng constructor mặc định
        linkedList.addFirst(13);
        linkedList.addFirst(13);

        linkedList.add(2, 9); // Sử dụng phương thức add(int index, Object data) để chèn vào vị trí 4
        linkedList.add(2, 9); // Thêm lần nữa để kiểm tra
        linkedList.printList();
    }
}
