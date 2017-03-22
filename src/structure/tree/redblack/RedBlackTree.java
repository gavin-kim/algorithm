package structure.tree.redblack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RedBlackTree <K extends Comparable<K>, V> {

    private Node root;
    private int size;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    class Node {

        K key;
        V value;
        Node left;
        Node right;
        Node parent;
        boolean color = BLACK;

        Node(K key, V value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return String.format("Key: %s, Value: %s, Color: %s", key, value,
                color ? "BLACK" : "RED");
        }
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        Node node = getNode(key);

        return node == null ? null : node.value;
    }

    public Node getNode(K key) {

        Node node = root;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) node = node.left;   // go left: key is less
            else if (cmp > 0) node = node.right;  // go right: key is greater
            else return node;  // find a key
        }

        return null;
    }

    public V put(K key, V value) {

        if (root == null) {
            root = new Node(key, value, null);
            size = 1;
            return null;
        }

        Node node = root;
        Node parent = root.parent;

        while (node != null) {

            int cmp = key.compareTo(node.key);
            parent = node;

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        Node newNode = new Node(key, value, parent);
        if (key.compareTo(parent.key) < 0)
            parent.left = newNode;
        else
            parent.right = newNode;

        fixAfterInsertion(newNode);
        size++;
        return null;
    }

    /** Returns the successor of the specified Node, or null */
    private Node successor(Node node) {
        if (node == null)
            return null;
        else if (node.right != null) {
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {
            while (node.parent != null && node == node.parent.right)
                node = node.parent;
            return node.parent;
        }
    }

    /** Returns the predecessor of the specified Node, or null */
    private Node predecessor(Node node) {
        if (node == null)
            return null;
        else if (node.left != null) {
            node = node.left;
            while (node.right != null)
                node = node.right;
            return node;
        } else {
            while (node.parent != null && node == node.parent.left)
                node = node.parent;
            return node.parent;
        }
    }

    /**
     *    p             x
     *   / \           / \
     *  a  x    ->    p  c
     *    / \        / \
     *   b  c       a  b
     */
    private void rotateLeft(Node parent) {

        Node node = parent.right;

        parent.right = node.left;    // relocate b
        if (parent.right != null)
            parent.right.parent = parent;

        node.parent = parent.parent; // grand parent's pointer
        if (node.parent == null)
            root = node;
        else if (parent.parent.left == parent)
            parent.parent.left = node;
        else
            parent.parent.right = node;

        node.left = parent;
        parent.parent = node;
    }

    /**
     *      p            x
     *     / \          / \
     *    x  c   ->    a   p
     *   / \              / \
     *  a  b             b  c
     */
    private void rotateRight(Node parent) {

        Node node = parent.left;

        parent.left = node.right;      // relocate b
        if (parent.left != null)
            parent.left.parent = parent;

        node.parent = parent.parent;   // grand parent's pointer
        if (node.parent == null)
            root = node;
        else if (parent.parent.left == parent)
            parent.parent.left = node;
        else
            parent.parent.right = node;

        node.right = parent;
        parent.parent = node;
    }

    /**
     *       B              *R
     *      / \             / \
     *     R  R    ->     *B *B
     *    /               /
     *   R(x)            R(x)
     */
    private void recolor(Node node, Node uncle) {
        node.parent.color = BLACK;      // parent
        node.parent.parent.color = RED; // grand parent
        uncle.color = BLACK; // uncle
    }

    /** Fix Double-Red violation */
    public void fixAfterInsertion(Node node) {
        node.color = RED;

        // If a parent color is red, it has its parent (black)
        while (node != null && node != root && node.parent.color == RED) {

            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;

                if (uncle == null || uncle.color == BLACK) { // rotate
                    if (node.parent.right == node) { // LR -> LL
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent); // node will be its parent

                } else { // recolor
                    recolor(node, uncle);
                    node = node.parent.parent;
                }

            } else {
                Node uncle = node.parent.parent.left;

                if (uncle == null || uncle.color == BLACK) { // rotate
                    if (node.parent.left == node) { // RL -> RR
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent); // node will be its parent

                } else { // recolor
                    recolor(node, uncle);
                    node = node.parent.parent;
                }
            }
        }
        root.color = BLACK;
    }

    public V delete(K key) {
        Node node = getNode(key);

        if (node == null)
            return null;

        V value = node.value;
        deleteNode(node);
        return value;
    }

    /** Remove the node and Link child to parent */
    private void transplant(Node node, Node child) {
        if (node.parent == null)
            root = child;
        else if (node == node.parent.left)
            node.parent.left = child;
        else
            node.parent.right = child;

        if (child != null)
            child.parent = node.parent;
    }

    private void deleteNode(Node node) {
        size--;
        Node child;

        if (node.left == null) {          // left child is null
            child = node.right;
        } else if (node.right == null) {  // right child is null
            child = node.left;
        } else {                          // the node has both
            Node successor = successor(node);
            node.key = successor.key;     // copy successor's key
            node.value = successor.value; // copy successor's value
            node = successor;             // change node to successor
            // successor can't be null because the node has left and right.
            child = successor.left != null ? successor.left : successor.right;
        }

        if (node.color == BLACK)
            if (child == null)           // No children.
                fixAfterDeletion(node);  // Use self as phantom
            else
                fixAfterDeletion(child);

        transplant(node, child);
    }

    private void fixAfterDeletion(Node node) {
        while (node != root && node.color == BLACK) {
            if (node == node.parent.left) {
                Node sib = node.parent.right;

                if (sib.color == RED) {
                    sib.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sib = node.parent.right;
                }

                if ((sib.left == null || sib.left.color == BLACK) && // Merge
                    (sib.right == null || sib.right.color == BLACK)) {
                    sib.color = RED;
                    node = node.parent;

                } else { // Transfer
                    if (sib.right == null || sib.right.color == BLACK) {
                        sib.left.color = BLACK;
                        sib.color = RED;
                        rotateRight(sib);
                        sib = node.parent.right;
                    }
                    sib.right.color = BLACK;
                    sib.color = node.parent.color;
                    node.parent.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }

            } else {
                Node sib = node.parent.left;

                if (sib.color == RED) {
                    sib.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sib = node.parent.left;
                }

                if ((sib.left == null || sib.left.color == BLACK) && // Merge
                    (sib.right == null || sib.right.color == BLACK)) {
                    sib.color = RED;
                    node = node.parent;

                } else { // Transfer
                    if (sib.left == null ||
                        sib.left.color == BLACK) {
                        sib.right.color = BLACK;
                        sib.color = RED;
                        rotateLeft(sib);
                        sib = node.parent.left;
                    }
                    sib.left.color = BLACK;
                    sib.color = node.parent.color;
                    node.parent.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    private boolean validateNode(Node node) {
        return (node.color == RED) && (node.parent.color == RED);
    }

    public void validateTree() {
        if (root == null || size == 1)
            return;

        List<Node> nodes = new ArrayList<>();
        if (root.left != null) nodes.add(root.left);
        if (root.right != null) nodes.add(root.right);


        while (!nodes.isEmpty()) {
            List<Node> nodeList = new ArrayList<>(nodes.size() * 3);

            nodes.forEach(node -> {

                if (validateNode(node))
                    System.out.println((node.color ? "BLACK" : "RED") + " : " + (node.parent.color ? "BLACK" : "RED"));
                if (node.left != null) nodeList.add(node.left);
                if (node.right != null) nodeList.add(node.right);
            });

            nodes = nodeList;
        }
    }
}
