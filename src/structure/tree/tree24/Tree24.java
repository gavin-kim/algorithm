package structure.tree.tree24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tree24<K extends Comparable<K>, V> {

    private Tree24Node root;
    private int size;

    /**
     * Return value associated with the key if the key is in the tree
     */
    public V get(K key) {
        Tree24Node current = root;

        while (current != null) {
            int index = indexOf(key, current); // index of the key in the node

            if (index < 0) // not found
                current = getChildNode(key, current); // search in a subtree
            else // found
                return current.values.get(index);
        }
        return null;
    }

    /**
     * Return previous value if the key already exists in the tree
     */
    public V put(K key, V value) {

        Stack<Tree24Node> path = new Stack<>();

        if (root == null) {
            root = new Tree24Node(key, value);
        } else {

            Tree24Node current = root;

            while (current != null) {

                int index = indexOf(key, current);
                path.push(current);

                if (index < 0) { // not found
                    current = getChildNode(key, current);
                } else { // duplicate
                    return current.values.set(index, value);
                }
            }

            insertAndSplit(key, value, path);
        }

        return null;
    }

    /**
     * Insert and validate nodes along the path.
     * NOTE: A new key is always added to a leaf node
     *
     *  [ M N ]   same    [ M N ]                  [ M   C   N ]
     *     |  <-- index -->  |                         |   |
     * [A B C D]           [A B] [C] [D]            [A B]  [D]
     * a b c d e           a b c     d e            | | |  | |
     *                     -----     ---            a b c  d e
     *                     Node      New Node
     *
     * 1. insert
     * 2. check overflow
     * 3. split
     */
    private void insertAndSplit(K key, V value, Stack<Tree24Node> path) {

        // first insertion
        Tree24Node node = path.pop();
        int insertionPoint = insertionPointOf(key, node);
        node.keys.add(insertionPoint, key);
        node.values.add(insertionPoint, value);

        // check overflow
        while (node != null) {
            if (node.keys.size() > 3) { // overflow
                split(node, path); // split
                node = path.pop();
            }
            else
                break;
        }
    }

    /**
     * Split a node that is overflow [A B C D] -> [A B] [D]
     *                               0 1 2 3 4    0 1 2 3 4
     */
    private void split(Tree24Node node, Stack<Tree24Node> path) {

        // create a right child
        Tree24Node rightChildOfKey = new Tree24Node(node.keys.pop(), node.values.pop());
        rightChildOfKey.children.add(node.children.remove(3)); // Left child of D
        rightChildOfKey.children.add(node.children.remove(4)); // Right child of D

        // median key, value
        K key = node.keys.pop();
        V value = node.values.pop();

        if (path.isEmpty()) { // root is overflow
            root = new Tree24Node(key, value);
            root.children.add(node);
            root.children.add(rightChildOfKey);

        } else {
            Tree24Node parent = path.peek();
            int indexOfNode = parent.children.indexOf(node);
            parent.keys.add(indexOfNode, key);
            parent.values.add(indexOfNode, value);
            parent.children.add(indexOfNode + 1, rightChildOfKey);
        }
    }

    /**
     * Delete the key and return its value if the key exists in the tree
     */
    public V delete(K key) {
        return null;
    }

    /**
     * Return the index of the key in the specific node.
     * Return -1, if the key doesn't exist in the node.
     */
    public int indexOf(K key, Tree24Node node) {
        return node.keys.indexOf(key);
    }

    /**
     * Return the insertion point of the key in the node
     *
     * Node index:   (0 1 2)
     *               | | | |
     * Child index:  0 1 2 3
     */
    public int insertionPointOf(K key, Tree24Node node) {
        for (int i = 0; i < node.keys.size(); i++)
            if (key.compareTo(node.keys.get(i)) <= 0)
                return i;

        return node.keys.size();
    }

    /**
     * Return the next child node to search for the key
     */
    public Tree24Node getChildNode(K key, Tree24Node node) {

        if (node.children.isEmpty()) // node is a leaf
            return null;

        int insertionPoint = insertionPointOf(key, node);
        return node.children.get(insertionPoint);
    }



    /**
     * Delete the key from the node
     */
    public V delete(K key, Tree24Node node) {
        return null;
    }

    /**
     * Perform a transfer and fusion operation if the node is empty
     */
    public void validate(K key, Tree24Node node, List<Tree24Node> path) {

    }

    /**
     * Return a search path from the root to the key
     */
    public List<Tree24Node> path(K key) {
        return null;
    }

    /**
     * 2-3-4 Tree node
     *
     * Node index:   (0 1 2)
     *               | | | |
     * Child index:  0 1 2 3
     *
     */
    protected class Tree24Node {
        private LinkedList<K> keys = new LinkedList<>();
        private LinkedList<V> values = new LinkedList<>();
        private LinkedList<Tree24Node> children = new LinkedList<>();

        public Tree24Node(K key, V value) {
            keys.add(key);
            values.add(value);
        }
    }
}
