package quiz;

/**
 * Check the tree is a binary search tree
 * (node.left.data < node.data < node.right.data)
 */
public class IsBinarySearchTree {
    class Node {
        int data;
        Node left;
        Node right;
    }

    public boolean checkBST(Node node) {

        return checkBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkBST(Node node, int min, int max) {

        return node == null ||
            checkBST(node.left, min, node.data) &&
            checkBST(node.right, node.data, max);
    }
}

