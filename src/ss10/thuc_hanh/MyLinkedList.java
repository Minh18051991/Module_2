package ss10.thuc_hanh;

public class LinkedList {
    private  Node head;
    private  int numNodes;

    public MyLinkedList(Object data) {
        head = new Node(data);
        numNodes++;
    }

    private class Node {
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }
    }
}