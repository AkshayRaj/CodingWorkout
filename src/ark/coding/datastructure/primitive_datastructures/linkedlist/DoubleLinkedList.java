package ark.coding.datastructure.primitive_datastructures.linkedlist;

/**
 * Created by Akshayraj on 11/29/15.
 */
public class DoubleLinkedList extends LinkedList {
    private DoubleNode mHead;

    public DoubleLinkedList(LinkedList.Node head) {
        super(head);
        mHead = (DoubleNode) head;
    }

    @Override
    public Node getHead() {
        return mHead;
    }

    @Override
    public Node addNode(int data) {
        DoubleNode newNode = new DoubleNode(data);
        if(mHead == null){
            mHead = newNode;
        }else {
            DoubleNode currentNode = mHead;
            while (currentNode.getNextNode() != null) {
                currentNode = (DoubleNode) currentNode.getNextNode();
            }
            newNode.setPreviousNode(currentNode);
            currentNode.setNextNode(newNode);
        }
        return mHead;
    }

    public static void main(String[] args){
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList(null);
        doubleLinkedList.addNode(1);
        doubleLinkedList.addNode(2);
        doubleLinkedList.addNode(3);
        doubleLinkedList.addNode(1);
        LinkedList.printLinkedList(doubleLinkedList);
        System.out.println("\nHead: " + doubleLinkedList.getHead().getData());
    }

    /*****************DoubleNode**********************************/
    protected static class DoubleNode extends LinkedList.Node{
        DoubleNode mPreviousNode = null;

        private DoubleNode(int value){
            super(value);
        };

        public DoubleNode getPreviousNode() {
            return mPreviousNode;
        }

        public void setPreviousNode(DoubleNode previousDoubleNode) {
            mPreviousNode = previousDoubleNode;
        }
    }
}
