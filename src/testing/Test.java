package testing;


import com.sun.org.apache.bcel.internal.util.ByteSequence;
import structure.hashtable.SeparateChainingHashTable;
import structure.tree.bst.BST;
import structure.tree.redblack.RedBlackTree;
import structure.tree.tree24.Tree24;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("resources/data/test.txt"));
        int n = in.nextInt();
        int m = in.nextInt();
        int matrix[][] = new int[n][m];


        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                matrix[grid_i][grid_j] = in.nextInt();
            }
        }

        Graph graph = new Graph(matrix, n, m);
        graph.printNodes();
        graph.printEdges();
        List<List<Integer>> groupList = graph.getGroups();

        List<Integer> list = groupList.stream()
            .max((list1, list2) -> list1.size() < list2.size() ? -1 : list1.size() > list2.size() ? 1 : 0)
            .orElseGet(Collections::emptyList);

        list.stream().forEach(System.out::println);
        System.out.println(list.size());
    }

}
class Graph {

    HashMap<Integer, HashMap<Integer, Edge>> edges = new HashMap<>();

    Graph(int[][] matrix, int h, int l) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if (matrix[i][j] == 1) {
                    int id = i * l + j;
                    edges.put(id, getConnectedEdges(matrix, id, i, j, h, l));
                }
            }
        }

    }

    HashMap<Integer, Edge> getConnectedEdges(int[][] matrix, int id, int i, int j, int h, int l) {
        HashMap<Integer, Edge> edges = new HashMap<>();
        int minX = i > 0 ? i - 1 : i;
        int minY = j > 0 ? j - 1 : j;
        int maxX = i < h - 1 ? i + 2 : h;
        int maxY = j < l - 1 ? j + 2 : l;

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                int neighborId = x * l + y;
                if (matrix[x][y] == 1 && neighborId != id) {
                    edges.put(neighborId, new Edge(id, neighborId));
                }
            }
        }
        return edges;
    }

    List<List<Integer>> getGroups() {
        List<List<Integer>> groupList = new ArrayList<>();

        for (int index : edges.keySet()) {
            groupList.add(dfs(index));
        }
        return groupList;
    }


    List<Integer> dfs(int root) {
        HashSet<Integer> unvisited = new HashSet<>(edges.keySet());
        Stack<Integer> stack = new Stack<>();
        List<Integer> path = new ArrayList<>();

        int current = root;
        unvisited.remove(current);
        path.add(current);

        do {
            boolean notFound = true;

            for (int i : edges.get(current).keySet()) {
                if (unvisited.remove(i)) {
                    stack.push(current);
                    current = i;
                    path.add(i);
                    notFound = false;
                    break;
                }
            }
            if (notFound && !stack.empty())
                current = stack.pop();

        } while (!stack.empty());


        return path;
    }

    void printNodes() {
        for (int index : edges.keySet()) {
            System.out.println(index);
        }
    }

    void printEdges() {
        for (Map<Integer, Edge> map : edges.values()) {
            for (Edge edge : map.values())
                System.out.println(edge);
        }
    }

    class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public String toString() {
            return String.format("[%d, %d]", u, v);
        }
    }

}




