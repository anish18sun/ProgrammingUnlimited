import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Prim Algorithm
 * @description: Used PriorityQueue in the implementation, could have used TreeSet instead
 */

public class Prim {

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

    private static class Vertex {
        Vertex parent;
        int key, value;
        Map<Vertex, Integer> adjList = new HashMap<>();
        public Vertex(int value) {
            this.value = value;
            this.key = Integer.MAX_VALUE;
        }
    }

    private static class VertexComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            int result = Integer.compare(o1.key, o2.key);
            return (result != 0) ? result : Integer.compare(o1.value, o2.value);
        }
    }

    private static void prim(Vertex[] graph, int n) {
        boolean[] selected = new boolean[n];
        PriorityQueue<Vertex> minQ = new PriorityQueue<>(new VertexComparator());

        graph[0].key = 0;
        minQ.add(graph[0]);

        for(int i = 0; i < n; ++i) {
            if(minQ.isEmpty()) {
              return;
            }
            Vertex v = minQ.poll();
            selected[v.value] = true;

            for(Vertex u: v.adjList.keySet()) {
                int edgeW = v.adjList.get(u);
                if(!selected[u.value] && edgeW < u.key) {
                    minQ.remove(u);
                    u.key = edgeW;
                    u.parent = v;
                    minQ.add(u);
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of vertices and edges in the graph");
        out.println("and then please enter the graph in adjacency list format: ");

        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; i++) { graph[i] = new Vertex(i); }

        for(int i = 0; i < m; i++) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            int w = in.nextInt();
            v.adjList.put(u, w);
        }

        prim(graph, n);
        out.println("Please enter vertex from which we want to trace path: ");
        List<Vertex> graphPath = new ArrayList<>();
        Vertex v = graph[in.nextInt() - 1];
        for(; v != graph[0]; v = v.parent) {
            graphPath.add(0, v);
        }
        graphPath.add(graph[0]);

        out.println("Vertices in the minimum spanning tree: ");
        graphPath.forEach(u -> out.print(u.value + " "));
        out.println();
        out.close();
        in.close();
    }
}
