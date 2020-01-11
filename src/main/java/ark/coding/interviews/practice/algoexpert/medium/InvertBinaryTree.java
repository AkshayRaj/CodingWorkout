/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

class InvertBinaryTree {
    public static void invertBinaryTree(BinaryTree tree) {
        // Write your code here.
        if (tree == null) {
            return;
        }
        BinaryTree left = tree.left;
        tree.left = tree.right;
        tree.right = left;
        invertBinaryTree(tree.left);
        invertBinaryTree(tree.right);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
