package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UndirectedGraph<V> implements Graph<V> {

    private Map<Integer, V> vertices = new HashMap<>();
    private Map<Integer, List<Edge>> edges = new HashMap<>(); // edges.get(u).get(v)
    private int numOfEdges; // size of edges

    protected UndirectedGraph() { }

    public UndirectedGraph(File file) {

        try (Scanner input = new Scanner(file)) {
            int numOfVertices = input.nextInt();
            numOfEdges = input.nextInt(); // number of edges

            for (int i = 0; i < numOfVertices; i++) {
                addVertex(i, null);
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
    public int vertexSize() {
        return vertices.size();
    }

    // O(1)
    @Override
    public int edgeSize() {
        return numOfEdges;
    }

    // O(1)
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    // O(1)
    @Override
    public Iterable<V> getVertices() {
        return vertices.values();
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
    public V addVertex(int index, V vertex) {

        if (vertices.containsKey(index))
            return vertices.put(index, vertex);  // return previous vertex
        else {
            vertices.put(index, vertex);
            edges.put(index, new ArrayList<>()); // add a new list for edges
            return null;                         // return null
        }
    }

    // O(1 + degree(index) * (degree(u) + degree(v)))
    @Override
    public V removeVertex(int index) {
        if (vertices.containsKey(index)) // remove edges associated with the vertex
            edges
                .get(index)
                .forEach((edge) -> removeEdge(edge.getU(), edge.getV()));

        return vertices.remove(index); // remove vertex and return removed one
    }

    // O(1)
    @Override
    public boolean addEdge(int u, int v) {
        if (vertices.containsKey(u) && vertices.containsKey(v)) {
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
        if (vertices.containsKey(u) && vertices.containsKey(v) &&
            edges.get(u).remove(v) != null && // u -> v
            edges.get(v).remove(u) != null){  // v -> u
            numOfEdges--;
            return true;
        } else
            return false;
    }


    @Override
    public GST dfs(int v) {
        return null;
    }

    @Override
    public GST bfs(int v) {
        return null;
    }

    @Override
    public void clear() {
        vertices = new HashMap<>();
        edges = new HashMap<>();
        numOfEdges = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        vertices.forEach((from, vertex) -> {
            builder.append("Vertex Index: ").append(from).append(", ");
            edges.get(from).forEach((edge) -> builder.append(edge).append(", "));
            builder.append("\n");
        });

        return builder.toString();
    }

}
