/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.trees;

/**
 * An AVL Tree is a self-balancing BinarySearchTree (BST)
 * Some of the invariant characteristics of a BST are -
 * For a given {@link Node} of a Binary Search Tree
 * 1. The keys of nodes in left subtree are less than or equal to the key of the node
 * 2. The keys of nodes in right subtree are greater than or equal to the key of the node
 * This is recursively true for the all the nodes in a binary tree
 *
 * One of the drawbacks of BST is that the height of left/right subtree might grow linearly along with the
 * number of nodes in the tree. Thus we might get a skewed tree, which will give a worst case search time of O(n)
 * To address this, a "balanced" BST is used.
 *
 * An {@link AVLTree} is a type of BalancedBinarySearchTree(BBST), where for any given node in the tree,
 * the difference in heights of left & right subtree is 1.
 * |H(l) - H(r)| <= 1
 * This is recursively true for all the nodes in the tree.
 *
 * The way AVLTree achieves this is by "re-balancing" the BST, if needed, after every insert/delete operation.
 */
public class AVLTree {

    private Node root;


    private void rightRotate(final Node node) {
        Node nodeParent;
        Node leftChild = node.getLeftChild();
        Node leftChildLeftSubtree = leftChild.getLeftChild();
        Node leftChildRightSubtree = leftChild.getRightChild();
        Node rightChildSubtree = node.getRightChild();
    }

    private void leftRotate() {

    }

}
