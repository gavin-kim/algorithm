package structure.tree;

import java.util.*;

public class BST<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int numOfChildren; // numOfChildren including its own

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
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        // Change key's value if key in subtree.
        // Otherwise, add new node to subtree associating key with value

        // Not found, create a new node and return
        if (node == null)
            return new Node(key, value, 1);

        int cmp = key.compareTo(node.key);

        if (cmp < 0) // go left
            node.left = put(node.left, key, value);
        else if (cmp > 0) // go right
            node.right = put(node.right, key, value);
        else // find the key and change value
            node.value = value;

        // count children in case a new node is added (size(null) returns 0)
        node.numOfChildren = size(node.left) + size(node.right) + 1;
        return node;
    }

    public K min() {
        return min(root).key;
    }

    private Node min(Node node) {

        // go left down until a node doesn't have a left child
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    public K max() {
        return max(root).key;
    }

    private Node max(Node node) {
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

    private Node floor(Node node, K key) {
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

    private Node ceil(Node node, K key) {
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

    // find a key associated with the index (index starts from 0)
    public K select(int index) {
        return select(root, index).key;
    }

    // node index : size - rightSize - 1
    private Node select(Node node, int index) {
        // Return Node containing key of index
        if (node == null)
            return null;

        int leftSize = size(node.left); // index of current node is left size

        if (index < leftSize)       // index is less
            return select(node.left, index);
        else if (index > leftSize)  // index is greater (index - leftSize - 1(current))
            return select(node.right, index - leftSize - 1); // subtract size of (left + current)
        else
            return node; // find a node
    }

    // rank starts from 1
    public int rank(K key) {
        return rank(root, key);
    }
    /*  when key = L
           J   <- rank(node.right, key) + 1 = 3
          / \
         B   N
        /   /
       A   M   <- rank(node.left, key) returns 0
     */
    private int rank(Node node, K key) {
        // Return size less than node.key in the subtree rooted at node
        if (node == null)
            return 0;

        int cmp = key.compareTo(node.key);

        if (cmp < 0)      // key is smaller
            return rank(node.left, key);
        else if (cmp > 0) // key is bigger (stack a size of left subtree + 1)
            return size(node.left) + 1 + rank(node.right, key);
        else
            return size(node.left); // key is the same
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    // remove leftmost node and shift up nodes until root
    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right; // right child exists or can be null

        node.left = deleteMin(node.left); // shift up nodes from leftmost node
        node.numOfChildren = size(node.left) + size(node.right) + 1; // update size
        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        // find a key
        else {
            // Case 1: 0 or 1 child
            if (node.right == null)
                return node.left;
            else if (node.left == null)
                return node.right;

            // Case 2: 2 children
            Node removedNode = node;
            node = min(node.right); // find minimum key in the right subtree
            node.right = deleteMin(removedNode.right); // delete a node
            node.left = removedNode.left;
        }

        node.numOfChildren = size(node.left) + size(node.right) + 1;
        return node;
    }

    // inorder
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K low, K high) {
        Stack<Node> stack = new Stack<>();
        Queue<K> queue = new LinkedList<>();

        Node node = root;

        while (node != null) {
            int cmpLow = low.compareTo(node.key);
            int cmpHigh = high.compareTo(node.key);

            // low <= key <= high
            if (cmpLow <= 0 && 0 <= cmpHigh)
                queue.add(node.key);

            // low < node.key < high
            //        /    \
            //     left   right
            if (cmpLow < 0) // low < key -> search left
                stack.push(node.left);
            if (cmpHigh > 0) // key < high -> search right
                stack.push(node.right);

            node = stack.pop();
        }

        return queue;
    }

    public Iterable<K> preOrder() {
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

    public Iterable<K> inOrder() {
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

    public Iterable<K> postOrder() {
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
