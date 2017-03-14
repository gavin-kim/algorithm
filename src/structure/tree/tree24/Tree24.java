package structure.tree.tree24;

import java.util.*;

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
     * NOTE: Deleting elements always starts from a leaf node (after swapping)
     */
    public V delete(K key) {

        Tree24Node target = getNode(key);

        if (target == null) // key is not found
            return null;

        Stack<Tree24Node> path = getPath(key);

        if (!target.children.isEmpty())       // is a leaf node?
            target = swapKeyForRightmost(key, target, path);

        V value = target.deleteKeyValue(key); // delete

        if (target.keys.isEmpty())            // is underflow?
            resolveUnderflow(path);

        size--;
        return value;
    }


    /**
     * Swap the key for the rightmost key and stack nodes to rightmost key
     * return a leaf node that had the rightmost key
     *
     * Node     A X Z        A Y Z
     *         | | | |      | | | |
     *           \            \
     *            \            \
     *          (...Y)       (...X)  <-- swap X for Y and return the node
     */
    private Tree24Node swapKeyForRightmost(K key, Tree24Node node,
                                           Stack<Tree24Node> path) {

        int index = node.indexOf(key);
        Tree24Node rightmostNode = node.children.get(index);
        path.add(rightmostNode);

        while (!rightmostNode.children.isEmpty()) {
            rightmostNode = rightmostNode.children.getLast();
            path.add(rightmostNode);
        }

        // swap key and value
        K tempKey = rightmostNode.keys.pollLast();
        V tempValue = rightmostNode.values.pollLast();

        rightmostNode.keys.addLast(node.keys.get(index));
        rightmostNode.values.addLast(node.values.get(index));

        node.keys.set(index, tempKey);
        node.values.set(index, tempValue);

        return rightmostNode;
    }

    /**
     * Resolve underflow along the path stack
     *
     * These 2 cases occur underflow
     *
     * 1. Deleting the last element in a node
     * 2. Merging keys from the singleton parent node
     */
    private void resolveUnderflow(Stack<Tree24Node> path) {

        Tree24Node node = path.pop();
        Tree24Node child = null;      // store a child node after merging

        while (node.keys.isEmpty()) { // check underflow
            node.children.clear();    // clear children

            if (path.isEmpty()) {     // node is root
                root = child;
                break;
            }

            Tree24Node parent = path.peek();

            if (transfer(parent, node, child))  // true, if it's transferred
                break;

            child = merge(parent, node, child); // return merged child node
            node = path.pop(); // check the parent for the next
        }
    }
    /**
     * When the node has an immediate sibling(2~3 elements)
     *
     * nodeIndex         0 1
     *                  (C D)
     * parentIndex    0/ 1| 2\
     * siblings   (..A)  (-)  (F..)
     *                |   |   |
     *                B  (*)  E
     *
     *   transfer from left      transfer from right
     *         (A D)                   (C F)
     *        /  |  \                 /  |  \
     *    (..)  (C)  (F..)       (..A)  (D)  (..)
     *         /  \  |                  / \
     *        B  (*) E                (*) E
     *
     * Transfer elements from a sibling that has 2~3 elements.
     * If the node is a leaf a child node must be null.
     */
    private boolean transfer(Tree24Node parent, Tree24Node node,
                             Tree24Node child) {
        /*
         *  parentIndex    0 1 2
         *  nodeIndex     0 1 2 3
         *
         *  If nodeIndex: 1, then
         *  left sibling: nodeIndex - 1, parentIndex: nodeIndex - 1
         *  right sibling: nodeIndex + 1, parentIndex: nodeIndex
         */
        int nodeIndex = parent.children.indexOf(node);
        int parentIndex = nodeIndex - 1;

        // transfer from left sibling
        if (parentIndex >= 0 &&
            parent.children.get(parentIndex).keys.size() > 1) {

            Tree24Node leftSibling = parent.children.get(parentIndex);

            // parent to node
            node.keys.add(parent.keys.get(parentIndex));
            node.values.add(parent.values.get(parentIndex));

            // sibling to parent
            parent.keys.set(parentIndex, leftSibling.keys.pollLast());
            parent.values.set(parentIndex, leftSibling.values.pollLast());

            if (child != null) { // If a child exist it's not a leaf node
                node.children.add(leftSibling.children.pollLast());
                node.children.add(child);
            }
            return true;
        }

        parentIndex = nodeIndex;

        // transfer from right sibling
        if (parentIndex < parent.keys.size() &&
            parent.children.get(nodeIndex + 1).keys.size() > 1) {

            Tree24Node rightSibling = parent.children.get(nodeIndex + 1);

            // parent to node
            node.keys.add(parent.keys.get(parentIndex));
            node.values.add(parent.values.get(parentIndex));

            // sibling to parent
            parent.keys.set(parentIndex, rightSibling.keys.pollFirst());
            parent.values.set(parentIndex, rightSibling.values.pollFirst());

            if (child != null) { // If a child exists it's not a leaf node
                node.children.add(child);
                node.children.add(rightSibling.children.pollFirst());
            }
            return true;
        }
        return false;
    }

    /**
     * When the node has no siblings(2~3 elements).
     * (*) is a child node after merging.
     *
     * a single element   (B)        (B)
     *                    / \        / \
     *                  (-) (C)    (A) (-)
     *                   |  / \    / \  |
     *
     *                   (-)         (-)
     *                      \       /
     *                (-) (B C)   (A B) (-)
     *                    / | \   / | \
     *                  (*)          (*)
     *
     *         0 1               0 1             0
     * Steps  (C D)             (C D)           (D)
     *        / | \             / |             / |
     *     (A) (-) (F)  -->  (A) (F)  -->  (A C) (F)  left merge
     *      0   1   2         0   1            |
     *         (*)               (*)          (*)
     *
     *                                          (C)
     *                                          / |
     *                                -->    (A)  (D F)
     *                                            |
     *                                           (*)
     *
     * If the node is a leaf the child node must be null.
     * Return a child node that is combined.
     * After merging the parent can be underflow.
     *
     */
    private Tree24Node merge(Tree24Node parent, Tree24Node node,
                             Tree24Node child) {

        int nodeIndex = parent.children.indexOf(node); // get node's index
        parent.children.remove(nodeIndex);  // remove a link to the node.

        if (nodeIndex > 0) { // left merge

            Tree24Node leftSibling = parent.children.get(nodeIndex - 1);

            leftSibling.keys.addLast(parent.keys.remove(nodeIndex - 1));
            leftSibling.values.addLast(parent.values.remove(nodeIndex - 1));

            if (child != null)
                leftSibling.children.addLast(child);

            return leftSibling;

        } else { // right merge

            Tree24Node rightSibling = parent.children.get(nodeIndex);

            rightSibling.keys.addFirst(parent.keys.remove(nodeIndex));
            rightSibling.values.addFirst(parent.values.remove(nodeIndex));

            if (child != null)
                rightSibling.children.addFirst(child);

            return rightSibling;
        }
    }

    /**
     * Return a number of pairs of key and value.
     */
    public int size() {
        return size;
    }

    public void printStructure() {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }

        printStructure(0, Collections.singletonList(root));
    }

    private void printStructure(int level, List<Tree24Node> list) {

        list.forEach(System.out::print);
        System.out.println();

        LinkedList<Tree24Node> queue = new LinkedList<>();

        list.forEach(node -> node.children.forEach(queue::add));

        if (!queue.isEmpty())
            printStructure(level + 1, queue);
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

        private V deleteKeyValue(K key) {
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            keys.forEach(key -> sb.append(key).append(","));
            return sb.append("\b)").toString();
        }
    }
}
