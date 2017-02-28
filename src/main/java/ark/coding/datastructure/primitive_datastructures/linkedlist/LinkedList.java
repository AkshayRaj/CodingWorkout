package ark.coding.datastructure.primitive_datastructures.linkedlist;

public class LinkedList {
    private Node mHead;

    public LinkedList(Node head){
        mHead = head;
    }

    public Node addNode(int data){
        Node newNode = new Node(data);
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

    public Node deleteNode(int data){
        Node currentNode = mHead;
        if(currentNode.getData() == data){

        }
        return mHead;
    }

    public static void printLinkedList(LinkedList linkedList){
        Node currentNode = linkedList.getHead();
        while(currentNode != null){
            System.out.print("\n" + currentNode.getData());
            currentNode = currentNode.getNextNode();
        }
    }

    public static void main(String[] args){
        LinkedList singlyLinkedList = new LinkedList(null);
        singlyLinkedList.addNode(1);
        singlyLinkedList.addNode(2);
        singlyLinkedList.addNode(3);
        singlyLinkedList.addNode(1);
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
    protected static class Node{
        private int mData;
        private Node mNextNode = null;

        protected Node(int value){
            mData = value;
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
