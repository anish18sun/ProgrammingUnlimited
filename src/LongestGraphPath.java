import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the longest path in a graph problem
 * @description : Consider a graph G(V,E) where the vertices are such that there is an edge(v[i], v[j]) iff
 * i < j. From all vertices except n, i.e. {v[1], v[2],..., v[n-1]} , there is atleast one outgoing edge.
 * Then we must find the longest path from the start vertex to the end vertex of the graph. (Dynamic Programming)
 */

public class LongestGraphPath {

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
        int value;
        Vertex parent;
        List<Vertex> adjList = new ArrayList<>();
        public Vertex(int value) { this.value = value; }
    }

    private static List<Vertex> getLongestPath(Vertex[] graph, int n) {
        ArrayDeque<Vertex> Q = new ArrayDeque<>();
        List<Vertex> lPath = new ArrayList<>();
        int[] opt = new int[n];
        Q.addLast(graph[0]);

        while(!Q.isEmpty()) {
            Vertex v = Q.pollFirst();
            for(Vertex u : v.adjList) {
                if(opt[u.value - 1] < opt[v.value - 1] + 1) {
                    opt[u.value - 1] = opt[v.value - 1] + 1;
                    u.parent = v;
                    Q.addLast(u);
                }
            }
        }

        for(Vertex v = graph[n-1]; v != graph[0]; v = v.parent) { lPath.add(0, v); }
        lPath.add(0, graph[0]);
        return lPath;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of vertices & edges in the graph:- ");

        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; ++i) { graph[i] = new Vertex(i+1); }

        for(int i = 0; i < m; ++i) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            v.adjList.add(u);
        }

        out.println("The vertices in the longest path of the graph :-");
        getLongestPath(graph, n).forEach(v -> out.print(v.value + " "));
        out.close();
        in.close();
    }
}
