Blpackage structure.tree.redblack;

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
        return null;
    }

    /**
     *    p             x
     *   / \           / \
     *  a  x    ->    p  c
     *    / \        / \
     *   b  c       a  b
     */
    private void rotateLeft(Node parent) {

        Node node = parent.right;

        parent.right = node.left;    // relocate b
        if (parent.right != null)
            parent.right.parent = parent;

        node.parent = parent.parent; // grand parent's pointer
        if (node.parent == null)
            root = node;
        else if (parent.parent.left == parent)
            parent.parent.left = node;
        else
            parent.parent.right = node;

        node.left = parent;
        parent.parent = node;
    }

    /**
     *      p            x
     *     / \          / \
     *    x  c   ->    a   p
     *   / \              / \
     *  a  b             b  c
     */
    private void rotateRight(Node parent) {

        Node node = parent.left;

        parent.left = node.right;      // relocate b
        if (parent.left != null)
            parent.left.parent = parent;

        node.parent = parent.parent;   // grand parent's pointer
        if (node.parent == null)
            root = node;
        else if (parent.parent.left == parent)
            parent.parent.left = node;
        else
            parent.parent.right = node;

        node.right = parent;
        parent.parent = node;
    }

    /**
     *       B              *R
     *      / \             / \
     *     R  R    ->     *B *B
     *    /               /
     *   R(x)            R(x)
     */
    private void recolor(Node node, Node uncle) {
        node.parent.color = BLACK;      // parent
        node.parent.parent.color = RED; // grand parent
        uncle.color = BLACK; // uncle
    }


    public void fixAfterInsertion(Node node) {
        node.color = RED;

        // If a parent color is red, it has its parent (black)
        while (node != null && node != root && node.parent.color == RED) {

            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;

                if (uncle == null || uncle.color == BLACK) { // rotate
                    if (node.parent.right == node) { // LR -> LL
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent); // node will be its parent

                } else { // recolor
                    recolor(node, uncle);
                    node = node.parent.parent;
                }

            } else {
                Node uncle = node.parent.parent.left;

                if (uncle == null || uncle.color == BLACK) { // rotate
                    if (node.parent.left == node) { // RL -> RR
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent); // node will be its parent

                } else { // recolor
                    recolor(node, uncle);
                    node = node.parent.parent;
                }
            }
        }
        root.color = BLACK;
    }

    /**
     * Case 1: Node is red
     *
     * P(B)      P(B)
     *   |        |
     * N(R)  ->  N(x)  just delete the node
     *   |
     * C(x)
     *
     * */
    private void case1(Node node) {

    }

    /**
     * Case 2: Node is black and its child is red
     *
     * P(x)      P(x)
     *   |         |
     * N(B)  ->  N(B)  keep black to maintain black-depth
     *   |
     * C(R)
     *
     * */
    private void case2(Node node) {

    }

    /**
     * Case 3: Double-Black Problem
     *
     * P(x)     B       B         B         B
     *   |     / \     / \       / \      /   \
     * N(B)   B  B    B  R      R  B     R    R
     *   |              / \    / \      / \  / \
     * C(B)            B  B   B  B     B  B B  B
     *
     *
     * 1. Sibling is RED
     *
     *       P(B)               (B)                        In 24Tree
     *      /   \               / \
     *   N(B) S(R)   --->    P(R) B                         B R          R B
     *         / \           /  \                          | | |   ==   | | |
     *        B  B        N(B) S(B) <-- new sibling        B B B        B B B
     *
     *
     * NOTE: Now sibling is always black. Check sibling's children.
     *
     * 2. Sibling's children are Black-Black -> decrease sibling's Black-Depth
     *    (It's similar to merge in 24Tree)
     *
     *      ?            ?  <--- need to check x.parent
     *     / \          / \
     *    x  B   -->   x  R
     *      / \          / \
     *     B  B         B  B
     *
     * 3. Make Sibling's
     *
     *      ?            ?             ?
     *     / \          / \           / \
     *    x  B   -->   x  R*   -->   x  B*  <-- a new sibling
     *      / \          / \           / \
     *     R  B         B* B          a  R*
     *                 / \              / \
     *                a  b             b  B
     *
     * 4.   ?            B*              ?*              ?              ?
     *     / \          / \             / \             / \  transfer  / \
     *    x  B   -->   x  ?*    -->    B* B*           x  B    -->    B  B
     *      / \          / \          / \                | |         | |
     *     a  R         a  B*        x  a                a R         x a
     *
     * */
    private void case3(Node node) {

    }
}
