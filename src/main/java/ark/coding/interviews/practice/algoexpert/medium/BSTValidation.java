/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

public class BSTValidation {
    public static boolean validateBst(BST tree) {
        if (tree == null) {
            return true;
        }
        return validateLeftChild(tree.left, tree.value, Integer.MIN_VALUE)
                && validateRightChild(tree.right, tree.value, Integer.MAX_VALUE);
    }

    static boolean validateLeftChild(BST leftChild, int parentValue, int min) {
        if (leftChild == null) {
            return true;
        }
        return leftChild.value < parentValue && leftChild.value >= min
                && validateLeftChild(leftChild.left, leftChild.value, min)
                && validateRightChild(leftChild.right, leftChild.value, parentValue);
    }

    static boolean validateRightChild(BST rightChild, int parentValue, int max) {
        if (rightChild == null) {
            return true;
        }
        return rightChild.value >= parentValue && rightChild.value < max
                && validateLeftChild(rightChild.left, rightChild.value, parentValue)
                && validateRightChild(rightChild.right, rightChild.value, max);
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
