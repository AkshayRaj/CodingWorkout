package ark.coding.datastructure.primitive_datastructures.linkedlist;

public class SinglyLinkedList {
    private Node mHead;

    public SinglyLinkedList(Node node){
        mHead = node;
    }

    public Node addNode(Node newNode){
        if(mHead == null){
            mHead = newNode;
        }else {
            Node currentNode = mHead;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
        return mHead;
    }

    public static void printLinkedList(SinglyLinkedList singlyLinkedList){
        Node currentNode = singlyLinkedList.getHead();
        while(currentNode != null){
            System.out.print("\n" + currentNode.getData());
            currentNode = currentNode.getNextNode();
        }
    }

    public static void main(String[] args){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList(null);
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(2, null);
        singlyLinkedList.addNode(node1);
        singlyLinkedList.addNode(node2);
        singlyLinkedList.addNode(node3);
        singlyLinkedList.addNode(node4);
        singlyLinkedList.printLinkedList(singlyLinkedList);
    }

/******************************Getters and Setters*******************************/
    public Node getHead() {
        return mHead;
    }

    public void setHead(Node head) {
        mHead = head;
    }

/*********************************Node******************************************/
    private static class Node{
        private int mData;
        private Node mNextNode;

        public Node(int value, Node next){
            mData = value;
            mNextNode = next;
        }

        public int getData() {
            return mData;
        }

        public void setData(int data) {
            mData = data;
        }
        public Node getNextNode() {
            return mNextNode;
        }

        public void setNextNode(Node nextNode){
            mNextNode = nextNode;
        }

    }
}
