package structure.tree.redblack;

import structure.tree.bst.BST;

import java.util.TreeMap;

public class RedBlackTree <K extends Comparable<K>, V> extends BST<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private class Node extends BST<K, V>.Node {

        Node parent;
        boolean color;

        protected Node(K key, V value, Node parent, boolean color) {
            super(key, value);
            this.parent = parent;
            this.color = color;
        }
    }

    @Override
    public V get(K key) {
        return null;

    }

    @Override
    public V put(K key, V value) {
        return null;
    }
}
