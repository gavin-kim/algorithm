package structure.tree.tree24;

import java.util.ArrayList;
import java.util.List;

public class Tree24<K extends Comparable<K>, V> {

    private Tree24Node root;
    private int size;

    /**
     * Return value associated with the key if the key is in the tree
     */
    public V get(K key) {

        return null;
    }

    /**
     * Return previous value if the key already exists in the tree
     */
    public V put(K key, V value) {
        return null;
    }

    /**
     * Delete the key and return its value if the key exists in the tree
     */
    public V delete(K key) {
        return null;
    }

    /**
     * Return true if the key is in the node
     */
    public boolean match(K key, Tree24Node node) {
        return true;
    }

    /**
     * Return the next child node to search for the key
     */
    public Tree24Node getChildNode(K key, Tree24Node node) {
        return null;
    }

    /**
     * Insert key and value along with the reference to its right child to 2~3-node
     */
    public void insert23(K key, Tree24Node rightChildOfKey) {

    }

    /**
     * Split 4-node into two nodes. Return the median key
     *
     *   ( A B C D )            ( C ) <-- median
     *    / | | | \            /    \
     *   a  b c d e         (A B)  (D)
     *                      / | \  / \
     *                     a  b c d  e
     */
    public K split(K key, Tree24Node rightChildOfKey) {
        return null;
    }

    /**
     * Locate the insertion point of the key in the node
     */
    public int locate(K key, Tree24Node node) {
        return 0;
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
    public List<K> path(K key) {
        return null;
    }

    protected class Tree24Node {
        private List<K> keys = new ArrayList<>();
        private List<V> values = new ArrayList<>();
        private List<Tree24Node> children = new ArrayList<>();
    }
}
