package structure.graph;

import java.io.File;
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

    // Depth first search from vertex v
    @Override
    public GST dfs(int v) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> unvisited = new HashSet<>(vertices.keySet());

        // for GST
        List<Integer> searchOrder = new ArrayList<>();
        int[] parentOf = new int[vertices.size()];
        int numOfGroups = 1;

        // create iteratorMap
        Map<Integer, Iterator<Edge>> iteratorMap = new HashMap<>();
        edges.forEach((index, list) -> iteratorMap.put(index, list.iterator()));

        int current = v;
        unvisited.remove(current);// visit
        searchOrder.add(current); // add a vertex in search order
        parentOf[current] = -1;   // no parent

        // loop until all is visited
        while (!unvisited.isEmpty()) {

            // find edge to visit in current vertex
            if (iteratorMap.get(current).hasNext()) {

                Edge edge = iteratorMap.get(current).next();

                // visit to connected vertex and stack
                if (unvisited.contains(edge.getV())) {
                    current = edge.getV();     // move to new vertex
                    stack.push(current);       // stack current
                    unvisited.remove(current); // check visited
                    searchOrder.add(current);  // add current in searchOrder
                    parentOf[edge.getV()] = edge.getU(); // store parent of current
                }

            } else { // no edge to visit

                // check the stack is empty
                if (stack.isEmpty()) { // create a new group
                    current = unvisited.stream().findFirst().get(); // choose a vertex in unvisited vertices
                    unvisited.remove(current); // check visited
                    searchOrder.add(current);  // add current in searchOrder
                    numOfGroups++;             // new group
                    parentOf[current] = -1;    // no parent
                } else
                    current = stack.pop(); // go back
            }
        }
        return new GST(v, numOfGroups, parentOf, searchOrder);
    }

    // Breath First Search from vertex v
    @Override
    public GST bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> unvisited = new HashSet<>(vertices.keySet());

        // for GST
        List<Integer> searchOrder = new ArrayList<>();
        int numOfGroups = 1; // number of groups
        int[] parentOf = new int[vertices.size()]; // parent of the vertex

        // visit v
        int current = v;
        unvisited.remove(current);
        searchOrder.add(current);
        parentOf[current] = -1;
        queue.offer(current);

        // loop until all is visited
        while(!unvisited.isEmpty()) {

            // check the queue is empty
            if (queue.isEmpty()) { // create a new group
                current = unvisited.stream().findFirst().get(); // always true
                unvisited.remove(current);
                searchOrder.add(current);
                numOfGroups++; // new group
                parentOf[current] = -1;
            } else
                current = queue.poll();

            for (Edge edge : edges.get(current)) {
                if (unvisited.contains(edge.getV())) {
                    queue.offer(edge.getV());
                    searchOrder.add(edge.getV());  // add current in searchOrder
                    parentOf[edge.getV()] = edge.getU();
                    unvisited.remove(edge.getV()); // visited
                }
            }
        }
        return new GST(v, numOfGroups, parentOf, searchOrder);
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
