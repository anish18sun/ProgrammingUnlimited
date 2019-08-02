import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of a graph based on Edge Data Structure
 * @description: A graph based on edge-representation that considers the edge properties
 * as means of performing operations, optimizations. Efficient for sorting on edges, etc
 * bi-directionality in terms of edges may not be needed.
 */

public class EdgeBasedGraph {

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

    static class Edge {
        int u, v, weight;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.weight = w;
        }

        @Override
        public String toString() {
            return "{" + u + ", " + v + ", " + weight + "}";
        }
    }

    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int m = in.nextInt();
        Set<Edge> graph = new TreeSet<>(new EdgeComparator());
        for(int i = 0; i < m; ++i) {
            graph.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        }

        for(Edge edge: graph) {
            out.println("{u, v, w}: " + edge);
        }
        out.close();
        in.close();
    }
}
