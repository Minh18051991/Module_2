package ss10.thuc_hanh;

public class MyLinkedList {
    private Node head; // Điểm bắt đầu của danh sách
    private int numNodes = 0; // Đếm số lượng node

    public MyLinkedList() {
        // Constructor mặc định
    }

    // Thêm một phần tử vào cuối danh sách
    public void add(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; // Nếu danh sách trống, gán head bằng newNode
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next; // Duyệt đến node cuối cùng
            }
            temp.next = newNode; // Thêm newNode vào cuối
        }
        numNodes++;
    }

    // Thêm phần tử vào đầu danh sách
    public void addFirst(Object data) {
        Node newNode = new Node(data);
        newNode.next = head; // Liên kết newNode với node hiện tại của head
        head = newNode; // Cập nhật lại head thành newNode
        numNodes++;
    }

    public void add(int index, Object data) {
        if (index > numNodes || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            addFirst(data); // Thêm vào đầu danh sách
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next; // Duyệt đến node trước vị trí chỉ định
        }
        newNode.next = current.next; // Liên kết newNode với node tiếp theo
        current.next = newNode; // Liên kết node trước với newNode
        numNodes++;
    }



    // Lấy phần tử tại vị trí index
    public Node get(int index) {
        if (index >= numNodes || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next; // Duyệt đến phần tử tại index
        }
        return current;
    }

    // In danh sách
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data); // In dữ liệu của node
            current = current.next; // Chuyển sang node tiếp theo
        }
    }

    // Lớp nội bộ Node để biểu diễn một node trong danh sách
    private class Node {
        private Node next; // Liên kết đến node tiếp theo
        private Object data; // Dữ liệu chứa trong node

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }
    }
}
