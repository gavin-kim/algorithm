package graph;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {

    private double weight;

    public WeightedEdge(int u, int v, double weight) {
        super(u, v);
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightedEdge edge) {
        return Double.compare(weight, edge.weight);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && weight == weight;
    }

    @Override
    public int hashCode() {
        long f = Double.doubleToLongBits(weight);
        return 31 * super.hashCode() + (int)(f ^ (f >>> 32));
    }

    @Override
    public String toString() {
        return String.format("Weighted Edge[u: %d, v: %d, weight: %.2f]",
            getU(), getV(), weight);
    }
}
