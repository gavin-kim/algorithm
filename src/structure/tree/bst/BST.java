package structure.tree.bst;

import java.util.*;

public class BST<K extends Comparable<K>, V> {

    protected Node root;
    protected int size;

    protected class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Key: %s, Value: %s", key, value);
        }
    }

    public int size() {
        return size;
    }

    public V get(K key) {

        Node node = root;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) node = node.left;   // go left: key is less
            else if (cmp > 0) node = node.right;  // go right: key is greater
            else return node.value;  // find a key
        }
        return null;
    }

    public V put(K key, V value) {
        // Search for key. Update value if found; grow table if new.
        // Change key's value if key in subtree.
        // Otherwise, add new node to subtree associating key with value

        if (root == null) {
            root = new Node(key, value);
            return null;
        }

        Node parent = null;
        Node node = root;

        while (node != null) {
            parent = node;
            int cmp = key.compareTo(node.key);

            if (cmp < 0) // go left
                node = node.left;
            else if (cmp > 0) // go right
                node = node.right;
            else { // find the key and change value
                V previous = node.value;
                node.value = value;
                return previous;
            }
        }

        if (key.compareTo(parent.key) < 0)
            parent.left = new Node(key, value);
        else
            parent.right = new Node(key, value);
        size++;
        return null;
    }

    public K min() {
        return min(root).key;
    }

    protected Node min(Node node) {

        // go left down until a node doesn't have a left child
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    public K max() {
        return max(root).key;
    }

    protected Node max(Node node) {
        // go right down until a node doesn't have a right child
        if (node.right == null)
            return node;
        else
            return max(node.right);
    }

    public K floor(K key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    protected Node floor(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp == 0)     // the same key
            return node;
        else if (cmp < 0) // target key is smaller
            return floor(node.left, key);
        else {            // target key is bigger: current node is a candidate for floor key
            Node rightChild = floor(node.right, key);

            if (rightChild == null) // Not found closer key in the right subtree
                return node;
            else
                return rightChild;
            // when rightChild is not null in subtree:
            // 1. node < key : find a key closer than current node key.
            // 2. node == key: find the same key.
        }
    }

    public K ceil(K key) {
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    protected Node ceil(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp == 0)     // the same key
            return node;
        else if (cmp > 0) // target key is bigger
            return ceil(node, key);
        else {            // target key is smaller: current node is a candidate for floor key
            Node leftChild = ceil(node.left, key);

            if (leftChild == null)
                return node;
            else
                return leftChild;
        }
    }


    // delete a item that has the same key, return a value if it's found or null
    public V delete(K key) {
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
            else break; // find the key
        }

        Node found = node;

        if (found == null) // not found
            return null;

        // has 0 or 1 child
        if (node.right == null) node = node.left;
        else if (node.left == null) node = node.right;
        else { // has 2 children

            /*
                         found              leftmost
                         /   \               /    \
                        A     B             A      B
                             /             / \    / \
                         leftmost                C
                              \
                               C
            */
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

            node = leftmost;          // replace the node with leftmost
            node.left = found.left;   // copy children nodes
            node.right = found.right;
        }

        // connect the node with its parent
        if (parent == null) root = node; // current node is a root:
        else if (parent.left == found) parent.left = node;
        else parent.right = node;

        size--;
        return found.value; // found
    }

    /*
             target             target
                 \                 \
             target.right       target.right
                /  \              /  \
             node   ?            A    ?
             /  \               / \
           null  A

    remove leftmost node from the node
     */
    private Node deleteMin(Node node) {

        // remove leftmost and replace it to leftmost's right child
        if (node.left == null)
            return node.right;

        node.left = deleteMin(node.left);         // go left
        return node;
    }

    // inorder
    public List<K> keys() {
        return keys(min(), max());
    }

    public List<K> keys(K low, K high) {
        Stack<Node> stack = new Stack<>();
        List<K> list = new ArrayList<>();

        Node node = root;

        while (!stack.isEmpty() || node != null) {

            if (node != null) {
                stack.push(node);

                // low < key -> search left more
                node = low.compareTo(node.key) < 0 ? node.left : null;

            } else {
                node = stack.pop();

                // low <= key <= high
                if (low.compareTo(node.key) <= 0 && high.compareTo(node.key) >= 0)
                    list.add(node.key);

                // key < high -> search right more
                node = node.key.compareTo(high) < 0 ? node.right : null;
            }
        }
        return list;
    }

    public List<K> preOrder() {
        Stack<Node> stack = new Stack<>();
        List<K> list = new ArrayList<>();

        Node node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                list.add(node.key);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right; // right child can be a node or null
            }
        }
        return list;
    }

    public List<K> inOrder() {
        Stack<Node> stack = new Stack<>();
        List<K> list = new ArrayList<>();

        Node node = root;

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.key);
                node = node.right;
            }
        }

        return list;
    }

    public List<K> postOrder() {
        Stack<Node> stack = new Stack<>();
        List<K> list = new ArrayList<>();

        Node node = root;
        Node lastPop = null; // need to check the last pop

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {  // current node is null

                //          peekNode
                //           /  \
                //        left  right
                //
                // peekNode has right child and lastPop is not from the right
                Node peekNode = stack.peek();

                if (peekNode.right != null && peekNode.right != lastPop)
                    node = peekNode.right;   // only change node here
                else {
                    list.add(peekNode.key);  // add peekNode but node is still null
                    lastPop = stack.pop();   // pop the peekNode
                }
            }
        }

        return list;
    }
}
