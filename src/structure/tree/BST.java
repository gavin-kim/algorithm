package structure.tree;

import java.util.Comparator;

public class BST<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int numOfChildren;

        public Node(K key, V value, int numOfChildren) {
            this.key = key;
            this.value = value;
            this.numOfChildren = numOfChildren;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else return node.numOfChildren;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {

        if (node == null) // key doesn't exist in BST
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            return get(node.left, key);   // go left: key is less
        else if (cmp > 0)
            return get(node.right, key);  // go right: key is greater
        else
            return node.value;            // find a key
    }

    public void put(K key, V value) {

    }
}
