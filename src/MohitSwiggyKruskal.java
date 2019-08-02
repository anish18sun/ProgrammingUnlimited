import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Mohit & Swiggy problem(Hackerrank)
 * @description: Given a graph of 'n' cities and the distance between each city as the edge
 * weight between those cities, we must find the shortest distance that Mohit must cover in
 * order to cover all the cities. Find minimum weight MST using kruskal's algorithm then
 * total weight to cover all junctions and return will be double of that weight.
 */

public class MohitSwiggyKruskal {

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader() {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(System.in), 32768);
        }

        public String next() {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() { return Integer.parseInt(next()); }

        public void close() {
            try {
                reader.close();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    private static void makeSet(int[] parent, int N) {
        for(int i = 0; i < N; ++i) { parent[i] = i; }
    }

    private static int findSet(int[] parent, int v) {
        if(parent[v] == v) { return v; }
        return parent[v] = findSet(parent, parent[v]);
    }

    private static void unionSet(int[] parent, int u, int v) {
        u = findSet(parent, u);
        v = findSet(parent, v);
        if(u != v) { parent[u] = v; }
    }

    private static int kruskal(Set<Edge> graph, int N) {
        List<Edge> mst = new ArrayList<>();
        int[] parent = new int[N];
        makeSet(parent, N);
        int cost = 0;

        for(Edge edge: graph) {
            if(findSet(parent, edge.u) != findSet(parent, edge.v)) {
                mst.add(edge);
                cost += edge.w;
                unionSet(parent, edge.u, edge.v);
            }
        }
        return cost;
    }

    private static int getDistance(Set<Edge> graph, int N) {
        return kruskal(graph, N) * 2;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int N = in.nextInt() + 1;
        int M = in.nextInt();
        Set<Edge> graph = new TreeSet<>();
        for(int i = 0; i < M; ++i) {
            graph.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        }

        out.println(getDistance(graph, N));
        out.close();
        in.close();
    }
}
