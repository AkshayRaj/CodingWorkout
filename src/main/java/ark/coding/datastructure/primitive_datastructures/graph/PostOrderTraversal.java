package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Stack;

public class PostOrderTraversal {
    private static final String SPACE = " ";

    Stack<Node> stack = new Stack<Node>();
    public static void main(String[] args){

    }

    //PostOrder
    void traverseTree(Stack stack){
        Node parentNode = (Node) stack.pop();
        Node rightNode = parentNode.getRightChild();
        Node leftNode = parentNode.getLeftChild();
        if(parentNode == null){
            return;
        }else if(rightNode == null){
            System.out.println(" " + parentNode.getData());
            if(leftNode == null){
                return;
            }else{

            }
        }
        while(parentNode.getRightChild() != null){
            stack.push(parentNode);
            parentNode = parentNode.getRightChild();
        }
        System.out.println(rightNode.getData());
        parentNode = (Node) stack.pop();
        System.out.println(parentNode.getData());
        leftNode = parentNode.getLeftChild();
        //traverseTree(leftNode, stack);
    }

    private class Node{
        private int data;
        private Node rightChild;
        private Node leftChild;

        public int getData() {
            return data;
        }

        Node getRightChild(){
            return rightChild;
        }

        Node getLeftChild(){
            return leftChild;
        }
    }
}
