import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class : Implmentation of the Opportunity Cycles problem (Dynamic Programming)
 * @description : Given a trading graph G(V, E) where the vertices represent companies and edges
 * represent trade ratios between companies, we must find the opportunity cycles in the graph.
 * Example: A -> B, is an edge going from vertex A to B, then the edge represents trade ratio
 * between these companies. If the trade ratio is positive, then trading shares of company A
 * with company B will be good. Consider a cycle of trades, A -> B -> C -> A; if the product
 * of trades in this cycles(product of all edges involved) leads to a positive value, then
 * we have an opportunity cycle. Problem reduced to finding all simple cycles in graph. The
 * problem of finding overlapping cycles in graph is NP. So it's difficult to find that in
 * polynomial time.
 * @input :
 * 13 16
 * 1 2
 * 2 3
 * 3 4
 * 4 5
 * 5 3
 * 4 6
 * 6 7
 * 7 8
 * 8 4
 * 3 9
 * 9 10
 * 10 11
 * 11 12
 * 12 9
 * 10 13
 * 13 2
 */

public class OpportunityCycles {

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

    static class Vertex {
        Vertex parent;
        int value, color;
        Map<Vertex, Integer> adjList = new HashMap<>();
        private Vertex(int value) { this.value = value; }

        private static Set<Set<Vertex>> cycles = new HashSet<>();
        public static Vertex getInstance(int value) { return new Vertex(value); }

        public void captureCycle(Vertex v, Vertex u) {
            Set<Vertex> cycle = new HashSet<>();
            for(Vertex w = v; w != u; w = w.parent) { cycle.add(w); }

            cycle.add(u);
            cycles.add(cycle);
        }

        public void dfs() {
            color = 1;
            for(Vertex u : adjList.keySet()) {
                if(u.color == 1) { captureCycle(this, u); }
                else { u.parent = this; u.dfs(); }
            }
        }

        public static Set<Set<Vertex>> getCycles() { return cycles; }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of vertices & the number of edges in graph :-");

        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; i++) { graph[i] = Vertex.getInstance(i + 1); }
        out.println("Please enter the edges of the graph in adjacency list format :- ");

        for(int i = 0; i < m; i++) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            int w = in.nextInt();
            v.adjList.put(u, w);
        }

        graph[0].dfs();
        out.println("The cycles found the graph :- ");
        for(Set<Vertex> cycle : Vertex.getCycles()) {
            cycle.forEach(v -> out.print(v.value + " "));
            out.println();
        }
        /* calculation of the product of edges in these cycles is a trivial matter */
        out.close();
        in.close();
    }
}
