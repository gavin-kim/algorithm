package structure.graph;


public interface Graph<V> {

    /** Return the number of vertices in the graph */
    int vertexSize();

    /** Return the number of edges in the graph */
    int edgeSize();

    /** Return the object for the specified vertex index */
    V getVertex(int index);

    /** Return the vertices in the graph */
    Iterable<V> getVertices();

    /** Return edges associated with the index */
    Iterable<Edge> getEdges(int index);

    /** Return the degree for a specified vertex */
    int getDegree(int index);

    /** Clear the graph */
    void clear();

    /**
     * Add a vertex to the graph, If the vertex is new, create a new list to
     * store edges
     *
     * @param index index where a vertex is added
     * @param vertex vertex to be associated with the index
     * @return the previous vertex associated with index,
     *         or null if there was no mapping
     */
    V addVertex(int index, V vertex);

    /**
     * Remove a vertex to the graph. Edges related to the vertex will be removed
     *
     * @param index index of the vertex to be removed from the graph
     * @return the previous vertex associated with index, or null
     *         if there was no vertex
     */
    V removeVertex(int index);

    /**
     * Add an edge between u and v
     *
     * @param u index of the vertex, stating point
     * @param v index of the vertex, end point
     * @return true if it's added, or false if there was no existing vertex
     *         associated with index u or v
     */
    boolean addEdge(int u, int v);

    /**
     * Remove an Edge to the graph
     *
     * @param u index of the vertex, starting point
     * @param v index of the vertex, end point
     * @return true if it's removed, or false if there was no existing vertex
     *         or there was no edge between u and v
     */
    boolean removeEdge(int u, int v);

    /** Obtain a graph search tree using a depth-first search starting from v */
    GST dfs(int v);

    /** Obtain a graph search tree using a breadth-first search starting from v */
    GST bfs(int v);
}
