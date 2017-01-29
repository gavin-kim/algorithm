package structure.tree;

import designpattern.structural.composite.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class AVLTree<K extends Comparable<K>, V> extends BST<K, V> {

    protected class AVLNode extends Node {
        protected int height; // in order to balance the tree

        public AVLNode(K key, V value) {
            super(key, value);
        }

        @Override
        public String toString() {
            return String.format("Key: %s, Value: %s, Height: %d", key, value, height);
        }
    }

    @Override
    public V put(K key, V value) {

        Node parent = null;
        Node node = root;

        while (node != null) {

            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            else if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            else {
                V previous = node.value;
                node.value = value;
                return previous;
            }
        }

        if (parent == null) root = new AVLNode(key, value);
        else if (key.compareTo(parent.key) < 0) {
            parent.left = new AVLNode(key, value);
            balanceFrom(parent.key); // balance from the node to root
        }
        else {
            parent.right = new AVLNode(key, value);
            balanceFrom(parent.key); // balance from the node to root
        }

        size++;
        return null;
    }

    @Override
    public V delete(K key) {

        Node parent = null;
        Node node = root;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) { // less
                parent = node;
                node = node.left;
            }
            else if (cmp > 0) { // greater
                parent = node;
                node = node.right;
            }
            else break; // found
        }

        if (node == null) return null; // not found

        Node found = node;
        K keyToBalance;

        // has 2 children
        if (node.left != null && node.right != null) {
            Node parentOfLeftmost = node;
            Node leftmost = parentOfLeftmost.right;

            // find leftmost and its parent from found.right
            while (leftmost.left != null) {
                parentOfLeftmost = leftmost;
                leftmost = leftmost.left;
            }

            // eliminate leftmost
            if (parentOfLeftmost.right == leftmost)
                parentOfLeftmost.right = leftmost.right;
            else
                parentOfLeftmost.left = leftmost.right;

            node = leftmost;
            node.left = found.left;
            node.right = found.right;
            keyToBalance = parentOfLeftmost.key; // balance from the parent to root

        } else { // has 0 or 1 child : switch the other child with current node
            keyToBalance = node.key;             // balance from the deleted key
            node = node.left == null ? node.right : node.left;
        }


        if (parent == null) root = node; // current node is root
        else if (parent.left == found) parent.left = node;
        else parent.right = node;

        balanceFrom(keyToBalance); // must balance after any deletion
        size--;
        return found.value;
    }

    public int getHeight() {
        return ((AVLNode) root).height;
    }



    // Reset the height of the specified node.
    private void updateHeight(AVLNode node) {
        if (node.left == null && node.right == null)
            node.height = 0;
        else if (node.left == null)
            node.height = 1 + ((AVLNode) node.right).height;
        else if (node.right == null)
            node.height = 1 + ((AVLNode) node.left).height;
        else
            node.height = 1 +
                Math.max(((AVLNode) node.left).height, ((AVLNode) node.right).height);
    }

    // O(2log n) Balances the nodes in the path from the node to the root (bottom-up)
    private void balanceFrom(K key) {

        List<AVLNode> path = getPathTo(key); // O(log n)

        for (int i = path.size() - 1; i >= 0; i--) { // O(log n) bottom up
            AVLNode node = path.get(i);
            AVLNode parent = node == root ? null : path.get(i - 1);

            updateHeight(node);     // O(1) update height
            balance(node, parent);  // O(1) balance
        }
    }

    // O(log n) get a path from root to a node that has a key
    private List<AVLNode> getPathTo(K key) {
        List<AVLNode> path = new ArrayList<>();

        Node node = root;

        while (node != null) {
            path.add((AVLNode)node);

            int cmp = key.compareTo(node.key);

            if (cmp < 0) node = node.left; // less
            else if (cmp > 0) node = node.right; // greater
            else break; // found
        }
        return path;
    }

    // Return the balance factor of the node (right height - left height)
    private int balanceFactor(AVLNode node) {
        if (node.right == null)
            return -node.height;
        else if (node.left == null)
            return +node.height;
        else
            return ((AVLNode)node.right).height - ((AVLNode)node.left).height;
    }

    // balance the node, node and parent must not null
    private void balance(Node node, Node parent) {
        switch (balanceFactor((AVLNode)node)) {

            case -2: // L
                if (balanceFactor((AVLNode)node.left) < 0)
                    balanceLL(node, parent); // LL -2, -
                else
                    balanceLR(node, parent); // LR -2, +
            break;

            case +2: // R
                if (balanceFactor((AVLNode)node.right) > 0)
                    balanceRR(node, parent); // RR +2, +
                else
                    balanceRL(node, parent); // RL +2, -
            break;
        }
    }


    // Perform LL balance: balance factor -2, -1
    private void balanceLL(Node node, Node parent) {

        /*
                parent             parent
                  |                  |   <--- 3
                 node              child
                 /  \              /   \ <--- 2
              child             grand  node
              /   \             /  \   /  \
           grand   A                  A  <--- 1

          child < A < node
        */

        Node child = node.left;
        node.left = child.right;         // 1
        child.right = node;              // 2

        if (parent == null) root = child; // 3
        else if (parent.left == node) parent.left = child;
        else parent.right = child;

        updateHeight((AVLNode)node);   // lower first
        updateHeight((AVLNode)child);
    }


    // Perform LR balance: balance factor -2, 1
    private void balanceLR(Node node, Node parent) {

        /*
              parent               parent
                |                    |
               node                grand
               /  \        3 --->  /   \ <--- 4
           child               child   node
           /  \                /  \    /  \
              grand        1 --->  A  B  <--- 2
               /  \
              A    B

          child < A < grand, grand < B < node
        */

        Node child = node.left;
        Node grand = child.right;

        child.right = grand.left; // 1
        node.left = grand.right;  // 2
        grand.left = child;       // 3
        grand.right = node;       // 4

        if (parent == null) root = grand; // 5
        else if (parent.left == node) parent.left = grand;
        else parent.right = grand;

        updateHeight((AVLNode) child); // lower first
        updateHeight((AVLNode) node);
        updateHeight((AVLNode) grand);
    }

    // Perform RR balance: balance factor +2, +
    private void balanceRR(Node node, Node parent) {

        Node child = node.right;
        node.right = child.left;
        child.left = node;

        if (parent == null) root = child;
        else if (parent.left == node) parent.left = child;
        else parent.right = child;


        updateHeight((AVLNode)node);
        updateHeight((AVLNode)child);
    }

    // Perform RL balance: balance factor +2, -
    private void balanceRL(Node node, Node parent) {

        Node child = node.right;
        Node grand = child.left;

        child.left = grand.right;
        node.right = grand.left;
        grand.right = child;
        grand.left = node;

        if (parent == null) root = grand;
        else if (parent.left == node) parent.left = grand;
        else parent.right = grand;

        updateHeight((AVLNode) child);
        updateHeight((AVLNode) node);
        updateHeight((AVLNode) grand);
    }
}
