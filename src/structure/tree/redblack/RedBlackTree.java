package structure.tree.redblack;

public class RedBlackTree <K extends Comparable<K>, V>  {

    private Node root;
    private int size;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private class Node {

        K key;
        V value;
        Node left;
        Node right;
        Node parent;
        boolean color;

        Node(K key, V value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }

    public V put(K key, V value) {
        return null;
    }

    public V delete(K key) {
        return null
    }

    /**
     *    p             r
     *   / \           / \
     *  x  r    ->    p  z
     *    / \        / \
     *   y  z       x  y
     */
    private void rotateLeft(Node p) {

        Node r = p.right;
        p.right = r.left;

        if (r.left != null)
            r.left.parent = p;

        r.parent = p.parent;
        if (p.parent == null)
            root = r;
        else if (p.parent.left == p) // p is a left child
            p.parent.left = r;
        else
            p.parent.right = r;

        r.left = p;
        p.parent = r;
    }

    /**
     *      p            l
     *     / \          / \
     *    l  z   ->    x   p
     *   / \              / \
     *  x  y             y  z
     */
    private void rotateRight(Node p) {
        Node l = p.left;

        p.left = l.right;
        if (p.left != null)
            p.left.parent = p;

        l.parent = p.parent;
        if (p.parent == null)
            root = l;
        else if (p.parent.left == p)
            p.parent.left = l;
        else
            p.parent.right = l;

        l.right = p;
        p.parent = l;
    }
}
