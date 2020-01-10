/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

/**
 * https://www.algoexpert.io/questions/BST%20Construction
 */
public class BSTConstruction {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        /**
         * Exercise: Implement this method
         * @param value
         * @return
         */
        public BST insert(int value) {
            // Write your code here.
            // Do not edit the return statement of this method.
            BST node = this;
            while (true) {
                if (value < node.value) {
                    if (node.left == null) {
                        node.left = new BST(value);
                        break;
                    }
                    else {
                        node = node.left;
                    }
                }
                else if (value >= node.value) {
                    if (node.right == null) {
                        node.right = new BST(value);
                        break;
                    }
                    else {
                        node = node.right;
                    }
                }
            }

            return this;
        }

        /**
         * Exercise: Implement this method
         * @param value
         * @return
         */
        public boolean contains(int value) {
            // Write your code here.
            BST node = this;
            while (true) {
                if (node.value == value) {
                    return true;
                }
                else if (value < node.value) {
                    if (node.left == null) {
                        break;
                    }
                    else {
                        node = node.left;
                    }
                } else if (value > node.value) {
                    if (node.right == null) {
                        break;
                    }
                    else {
                        node = node.right;
                    }
                }
            }
            return false;
        }

        /**
         * Exercise: Implement this method
         * @param value
         * @return
         */
        public BST remove(int value) {
            // Write your code here.
            // Do not edit the return statement of this method.
            BST nodeToRemove = this;
            BST parentNode = null;
            boolean leftOrRight = false;// true - left; false - right

            // 1. Find the node to remove
            //    Identify the node that we want to remove
            while (true) {
                if (nodeToRemove.value == value) {
                    break;
                }
                else if (value < nodeToRemove.value) {
                    if (nodeToRemove.left == null) {
                        throw new RuntimeException("======== node with value [" + value + "] does not exist ============");
                    }
                    else {
                        parentNode = nodeToRemove;
                        nodeToRemove = nodeToRemove.left;
                        leftOrRight = true;
                    }
                } else if (value > nodeToRemove.value) {
                    if (nodeToRemove.right == null) {
                        throw new RuntimeException("======== node with value [" + value + "] does not exist ============");
                    }
                    else {
                        parentNode = nodeToRemove;
                        nodeToRemove = nodeToRemove.right;
                        leftOrRight = false;
                    }
                }
            }

            // 2. Find the node to replace with.
            //    Carve it out - No incoming link from parent, no outgoing links to left/right children.
            BST replacementNode = nodeToRemove.right;
            BST replacementNodeParent = nodeToRemove;
            if (nodeToRemove.right != null) {
                if (replacementNode.left != null) {
                    while (replacementNode.left != null) {
                        replacementNodeParent = replacementNode;
                        replacementNode = replacementNode.left;
                    }
                    //left rotate the sub-tree
                    replacementNodeParent.left = replacementNode.right;
                    replacementNode.right = null; // outgoing links to replacement node are completely gone.
                }
                else {
                    // `replacementNode.left` is null
                    replacementNodeParent.right = replacementNode.right;
                    replacementNode.right = null;
                }
            }
            else if (nodeToRemove.left != null) {
                replacementNode = nodeToRemove.left;
                replacementNodeParent = nodeToRemove;
                if (replacementNode.right != null) {
                    while (replacementNode.right != null) {
                        replacementNodeParent = replacementNode;
                        replacementNode = replacementNodeParent.right;
                    }
                    replacementNodeParent.right = replacementNode.left;
                    replacementNode.left = null;
                }
                else {
                    replacementNodeParent.left = replacementNode.left;
                    replacementNode.left = null;
                }
            }
            else {
                // both of `nodeToRemove`'s children are null, i.e. it is a leaf node.
                replacementNode = null;
            }

            // 3. Replace the nodeToRemove with its replacement
            //      By this point we have -
            //      i)   `nodeToRemove` & its parent
            //      ii)  a fully carved out `replacementNode`
            if (parentNode == null) {
                // we are removing the root.
                this.value = replacementNode.value;
            }
            else {
                replacementNode.left = nodeToRemove.left;
                replacementNode.right = nodeToRemove.right;
                nodeToRemove.left = null;
                nodeToRemove.right = null;
                if (leftOrRight) {
                    parentNode.left = replacementNode;
                }
                else {
                    parentNode.right = replacementNode;
                }
            }

            return this;
        }
    }

    public static void main(String[] args) {
        BST test2 = new BST(10);
        test2.insert(15).insert(11).insert(22).remove(10);
        System.out.println();
    }
}
