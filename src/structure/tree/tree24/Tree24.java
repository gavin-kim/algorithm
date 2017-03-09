package structure.tree.tree24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tree24<K extends Comparable<K>, V> {

    private Tree24Node root;
    private int size; // number of keys

    /**
     * Return value associated with the key if the key is in the tree
     */
    public V get(K key) {
        Tree24Node found = getNode(key);

        if (found != null)
            return found.getValue(key);

        return null;
    }

    private Tree24Node getNode(K key) {
        Stack<Tree24Node> path = getPath(key);

        return path == null ? null : path.peek();
    }

    /**
     * Get a path to the key.
     * Return null, if the key is not found
     */
    private Stack<Tree24Node> getPath(K key) {
        Tree24Node current = root;
        Stack<Tree24Node> path = new Stack<>();

        while (current != null) {

            path.push(current);

            if (current.keys.contains(key)) // found
                return path;
            else // not found
                current = current.getChildNode(key); // search in a subtree
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

                int index = current.indexOf(key);
                path.push(current);

                if (index < 0) // not found
                    current = current.getChildNode(key);
                else           // duplicate
                    return current.values.set(index, value);
            }
            insertAndSplit(key, value, path);
        }
        size++;
        return null;
    }

    /**
     * Insert and validate nodes along the path.
     * NOTE: A new key is always added to a leaf node
     *
     *
     * 1. insert
     * 2. check overflow
     * 3. split
     */
    private void insertAndSplit(K key, V value, Stack<Tree24Node> path) {

        // 1. insertion
        Tree24Node node = path.peek();
        int insertionPoint = node.insertionPointOf(key);
        node.keys.add(insertionPoint, key);
        node.values.add(insertionPoint, value);

        // 2. check overflow
        while (!path.empty()) {
            node = path.pop();

            if (node.keys.size() > 3)
                split(node, path.empty() ? null : path.peek()); // 3. split
            else
                break;
        }
    }

    /**
     * Split a node that is overflow
     *
     *  [ M N ]     same    [ M N ]              [ M   C   N ]
     *     |    <-- index -->  |                     |   |
     * [A B C D]             [A B] [C] [D]        [A B]  [D]
     * a b c d e             a b c     d e        | | |  | |
     *                       -----     ---        a b c  d e
     *                       Node      New
     */
    private void split(Tree24Node node, Tree24Node parent) {

        // create a new node (D)
        Tree24Node newNode = new Tree24Node(node.keys.pollLast(), node.values.pollLast());

        if (node.children.size() > 3)
            newNode.children.add(node.children.remove(3)); // Left child of D
        if (node.children.size() > 3)
            newNode.children.add(node.children.remove(3)); // Right child of D

        // median key, value
        K key = node.keys.pollLast();
        V value = node.values.pollLast();

        if (parent == null) { // root is overflow (add a new node)
            root = new Tree24Node(key, value);
            root.children.add(node);
            root.children.add(newNode);

        } else {
            int indexOfNode = parent.children.indexOf(node);
            parent.keys.add(indexOfNode, key);
            parent.values.add(indexOfNode, value);
            parent.children.add(indexOfNode + 1, newNode);
        }
    }

    /**
     * Delete the key and return its value if the key exists in the tree
     *
     *  Case 1: is a leaf node?
     *
     *
     *  Case 2: is underflow?
     *
     *  Case 3: immediate sibling has 2+ elements?
     *
     *  Case 4: After fusion, a parent of the node is underflow?
     *
     */
    public V delete(K key) {

        Tree24Node target = getNode(key);

        if (target == null)
            return null;

        // is a leaf node?
        if (!target.children.isEmpty()) { // no leaf node
            target = swapKeyForRightmost(key, target);
        }

        // delete
        V value = target.deleteElement(key);

        // is underflow?
        if (target.keys.isEmpty())
            resolveUnderflow(target);


        return value;
    }


    /**
     * swap the key for the rightmost key and return the node had the rightmost
     *
     * Node     A X Z        A Y Z
     *         | | | |      | | | |
     *           \            \
     *            \            \
     *          (...Y)       (...X)  <-- swap X for Y and return the node
     */
    private Tree24Node swapKeyForRightmost(K key, Tree24Node node) {
        return null;
    }

    private void resolveUnderflow(Stack<Tree24Node> path) {
        // Has a sibling node?
        Tree24Node node = path.

    }

    /**
     *
     */
    private void transfer(Tree24Node node, Tree24Node parent) {

    }

    /**
     * Node is underflow and has no siblings(2+ elements)
     *
     *  A B     A B     A B         B
     * a b c   a - c   a b c   (a A) c
     *
     *   A     A         P             P  Q R
     *  a b   a -        -            -  ? ? ?
     *              (a A) -      (a A) - * * *  <-- leaf nodes
     *
     *   A     A         P
     *  a b   - b        -
     *                    (A b)
     *
     */
    private boolean fusion(Tree24Node node, Tree24Node parent) {

    }


    /**
     * Perform a transfer and fusion operation if the node is empty
     */
    public void validate(K key, Tree24Node node, List<Tree24Node> path) {

    }

    /**
     * Return a number of pairs of key and value.
     */
    public int size() {
        return size;
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

        private V getValue(K key) {
            return values.get(indexOf(key));
        }

        private V deleteElement(K key) {
            int index = indexOf(key);
            keys.remove(index);
            return values.remove(index);
        }

        /**
         * Return the index of the key in the specific node.
         * Return -1, if the key doesn't exist in the node.
         */
        private int indexOf(K key) {
            return keys.indexOf(key);
        }

        /**
         * Return the insertion point of the key in the node
         *
         * Node index:   (0 1 2)
         *               | | | |
         * Child index:  0 1 2 3
         */
        private int insertionPointOf(K key) {
            for (int i = 0; i < keys.size(); i++)
                if (key.compareTo(keys.get(i)) <= 0)
                    return i;

            return keys.size();
        }

        /**
         * Return the next child node to search for the key
         */
        private Tree24Node getChildNode(K key) {

            if (children.isEmpty()) // node is a leaf
                return null;

            return children.get(insertionPointOf(key));
        }
    }
}
