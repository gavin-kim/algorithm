package graph;

public class Edge {
    private int u;
    private int v;

    Edge() {}

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Edge &&
            ((Edge) obj).getU() == u && ((Edge) obj).getV() == v;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + u;
        result = 31 * result + v;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Edge[u: %d, v: %d]", u, v);
    }
}