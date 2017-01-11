package graph;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Count pairs that are from different groups
 *
 * {1, 2} {2, 3} edges mean {1, 2, 3} are in the same group
 */
public class JourneyToTheMoon {

    public static void main(String[] args) throws IOException {
        JourneyToTheMoon jttm = new JourneyToTheMoon(new Scanner(new File("src/graph/data.txt")));
        System.out.println(jttm.countPairs());
    }

    private int n;
    private int i;
    private Set<Integer> vertices = new HashSet<>();
    private Map<Integer, List<Edge>> edges = new HashMap<>();

    public JourneyToTheMoon(Scanner input) {
        initGraph(input);
    }

    private void initGraph(Scanner input) {

        n = input.nextInt();
        i = input.nextInt();

        while (input.hasNextInt()) {
            int u = input.nextInt();
            int v = input.nextInt();


            if (!vertices.contains(u)) {
                vertices.add(u);
                edges.put(u, new ArrayList<>());
            }

            if (!vertices.contains(v)) {
                vertices.add(v);
                edges.put(v, new ArrayList<>());
            }

            edges.get(u).add(new Edge(u, v));
            edges.get(v).add(new Edge(v, u));
        }
    }

    public long countPairs() {
        Set<Integer> unvisited = new HashSet<>(vertices);
        List<Integer> list = new ArrayList<>();
        long sum = 0;

        for (int v : vertices) {
            if (unvisited.contains(v)) {
                List<Integer> group = bfs(v, unvisited);

                list.add(group.size());
            }
        }

        // add singles(no group)
        list.add(n - vertices.size());

        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size(); j++)
                sum += list.get(i) * list.get(j);

        // calculate single's pair each other
        for (int i = n - vertices.size() - 1; i > 0; i--)
            sum += i;

        return sum;
    }

    // check if it's searched
    private List<Integer> bfs(int start, Set<Integer> unvisited) {

        List<Integer> members = new ArrayList<>();        // members connected
        LinkedList<Integer> queue = new LinkedList<>();   // queue to search a graph

        queue.push(start);
        members.add(start);
        unvisited.remove(start);

        while (!queue.isEmpty() && !unvisited.isEmpty()) {

            // visit
            int current = queue.poll();

            // search
            for (Edge edge : edges.get(current))
                if (unvisited.contains(edge.getV())) { // unchecked
                    queue.offer(edge.getV());
                    members.add(edge.getV());
                    unvisited.remove(edge.getV());
                }
        }

        return members;
    }


    private class Edge {

        // u -> v
        private int u;
        private int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public int getU() {
            return u;
        }

        public void setU(int u) {
            this.u = u;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || obj instanceof Edge &&
                u == ((Edge) obj).getU() && v == ((Edge) obj).getV();
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + u;
            result = result * 31 + v;
            return result;
        }

        @Override
        public String toString() {
            return String.format("U: %d, V: %d", u, v);
        }
    }
}
