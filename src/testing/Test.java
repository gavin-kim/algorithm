package testing;

import com.sun.javaws.util.JavawsConsoleController;
import graph.GST;
import graph.UndirectedGraph;
import structure.tree.BST;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Random;


public class Test {

    private static final int ARRAY_SIZE = 1000000;
    private static final int RANGE = 10000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) {

        File file = new File("resources/data/10000EWD.txt");
        UndirectedGraph<Integer> graph = new UndirectedGraph<>(file);
        GST gstFromDfs = graph.dfs(0);
        GST gstFromBfs = graph.bfs(0);


        for(int v : gstFromDfs.getSearchOrder())
            System.out.print(v + " ");

        System.out.println();

        for(int v : gstFromBfs.getSearchOrder())
            System.out.print(v + " ");
    }


    public static void testBST(int number) {
        BST<String, String> bst = new BST<>();
        bst.put("F", "F");
        bst.put("B", "B");
        bst.put("G", "G");
        bst.put("A", "A");
        bst.put("D", "D");
        bst.put("C", "C");
        bst.put("E", "E");
        bst.put("I", "I");
        bst.put("H", "H");

        System.out.println(bst.size());

        for (String k : bst.postOrder())
            System.out.println(k);
    }




    // N, M, K
    // N: number of shopping centers, M: number of roads, K: number of types of fish

    // Ti(number of types), T(type)

    // Edge: U(Node), V(Node), W(Weight)
}











