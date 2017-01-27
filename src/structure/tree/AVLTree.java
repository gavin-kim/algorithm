package structure.tree;

public class AVLTree<K extends Comparable<K>, V> extends BST<K, V> {

    protected class AVLNode extends Node {

        protected int height; // in order to balance the tree

        public AVLNode(K key, V value, int numOfChildren) {
            super(key, value, numOfChildren);
        }
    }

    @Override
    public void put(K key, V value) {

    }

    // Reset the height of the specified node.
    private void updateHeight(AVLNode node) {

    }

    // Balances the nodes in the path from the node to the root
    private void balancePath(K key) {

    }

    // Return the balance factor of the node
    private int balanceFactor(AVLNode node) {
        return 0;
    }


    // Perform LL balance: balance factor -2, -1
    private void balanceLL(AVLNode N, AVLNode parentOfN) {

    }

    // Perform LR balance: balance factor -2, 1
    private void balanceLR(AVLNode N, AVLNode parentOfN) {

    }

    // Perform RR balance: balance factor 2, 1
    private void balanceRR(AVLNode N, AVLNode parentOfN) {

    }

    // Perform RL balance: balance factor 2, -1
    private void balanceRL(AVLNode N, AVLNode parentOfN) {

    }
}
