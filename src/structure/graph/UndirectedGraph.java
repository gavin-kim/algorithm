package structure.graph;

import java.io.File;
import java.util.*;

public class UndirectedGraph<V> implements Graph<V> {

    private Map<Integer, V> nodes = new HashMap<>();
    private Map<Integer, List<Edge>> edges = new HashMap<>(); // edges.get(u).get(v)
    private int numOfEdges; // size of edges

    protected UndirectedGraph() { }

    public UndirectedGraph(File file) {

        try (Scanner input = new Scanner(file)) {
            int numOfNode = input.nextInt();
            numOfEdges = input.nextInt(); // number of edges

            for (int i = 0; i < numOfNode; i++) {
                addNode(i, null);
            }

            while (input.hasNext()) {
                int u = input.nextInt();
                int v = input.nextInt();
                input.nextDouble();
                addEdge(u, v);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // O(1)
    @Override
    public int nodeSize() {
        return nodes.size();
    }

    // O(1)
    @Override
    public int edgeSize() {
        return numOfEdges;
    }

    // O(1)
    @Override
    public V getNode(int index) {
        return nodes.get(index);
    }

    // O(1)
    @Override
    public Iterable<V> getNodes() {
        return nodes.values();
    }

    // O(1)
    @Override
    public Iterable<Edge> getEdges(int index) {
        return edges.get(index);
    }

    // O(1)
    @Override
    public int getDegree(int index) {
        return edges.get(index).size();
    }

    // O(1)
    @Override
    public V addNode(int index, V vertex) {

        if (nodes.containsKey(index))
            return nodes.put(index, vertex);  // return previous vertex
        else {
            nodes.put(index, vertex);
            edges.put(index, new ArrayList<>()); // add a new list for edges
            return null;                         // return null
        }
    }

    // O(1 + degree(index) * (degree(u) + degree(v)))
    @Override
    public V removeNode(int index) {
        if (nodes.containsKey(index)) // remove edges associated with the vertex
            edges
                .get(index)
                .forEach((edge) -> removeEdge(edge.getU(), edge.getV()));

        return nodes.remove(index); // remove vertex and return removed one
    }

    // O(1)
    @Override
    public boolean addEdge(int u, int v) {
        if (nodes.containsKey(u) && nodes.containsKey(v)) {
            // Add a new edge (undirected u <--> v)
            edges.get(u).add(new Edge(u, v));
            edges.get(v).add(new Edge(v, u));
            numOfEdges++;
            return true;
        } else
            return false;
    }

    // O(1 + degree(u) + degree(v))
    @Override
    public boolean removeEdge(int u, int v) {
        // Remove an edge (undirected u <--> v)
        if (nodes.containsKey(u) && nodes.containsKey(v) &&
            edges.get(u).remove(v) != null && // u -> v
            edges.get(v).remove(u) != null){  // v -> u
            numOfEdges--;
            return true;
        } else
            return false;
    }

    // Depth first search from vertex v
    @Override
    public List<Edge> dfs(int root) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> unvisited = new HashSet<>(nodes.keySet());
        List<Edge> path = new ArrayList<>();

        // create iteratorMap
        Map<Integer, Iterator<Edge>> iteratorMap = new HashMap<>();
        edges.forEach((index, list) -> iteratorMap.put(index, list.iterator()));

        int current = root;
        unvisited.remove(current);// visit

        do {
            // find edge to visit in current vertex
            if (iteratorMap.get(current).hasNext()) {

                Edge edge = iteratorMap.get(current).next();

                if (unvisited.remove(edge.getV())) {
                    current = edge.getV();     // move to new vertex
                    stack.push(current);       // stack current
                    path.add(edge);  // add current in searchOrder
                }

            } else if (!stack.isEmpty()) // no edge to visit
                current = stack.pop();

        } while (!stack.isEmpty());

        return path;
    }

    // Breath First Search from vertex v
    @Override
    public List<Edge> bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> unvisited = new HashSet<>(nodes.keySet());
        List<Edge> path = new ArrayList<>();

        int current = root;
        queue.offer(current);
        unvisited.remove(current);

        while(!queue.isEmpty()) {
            current = queue.poll();

            for (Edge edge : edges.get(current)) {
                if (unvisited.remove(edge.getV())) { // true: visited
                    path.add(edge);  // add current in path
                    queue.offer(edge.getV());
                }
            }
        }

        return path;
    }

    @Override
    public void clear() {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        numOfEdges = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        nodes.forEach((from, vertex) -> {
            builder.append("Node Index: ").append(from).append(", ");
            edges.get(from).forEach((edge) -> builder.append(edge).append(", "));
            builder.append("\n");
        });

        return builder.toString();
    }

}
