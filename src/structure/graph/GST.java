package structure.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Graph Search Tree
 */
public class GST {
    private int root; // The root of the tree
    private int numOfGroups;
    private int[] parentOf; // parents of vertices
    private List<Integer> searchOrder; // search order
    private List<List<Integer>> groups;

    public GST(int root, int numOfGroups,
               int[] parentOf, List<Integer> searchOrder) {
        this.root = root;
        this.numOfGroups = numOfGroups;
        this.parentOf = parentOf;
        this.searchOrder = searchOrder;
    }

    public int getRoot() {
        return root;
    }

    public int numOfGroups() {
        return numOfGroups;
    }

    /**
     * Return the path toward v
     *
     * @param v destination
     * @return The path from root to v, If there is no path return null
     */
    public List<Integer> getPath(int v) {
        List<Integer> path = new ArrayList<>();
        int current = v;

        while (current != -1) {
            if (current == root) {
                path.add(root);
                Collections.reverse(path);
                return path;
            }

            path.add(current);
            current = parentOf[current];
        }

        return null;
    }

    public List<Integer> getSearchOrder() {
        return searchOrder;
    }
}
