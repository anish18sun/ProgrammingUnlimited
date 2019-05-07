import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class : Implmentation of the UpdatePath Problem(Graph)
 * @description : Given a graph(DAG), and a sink vertex(t), then for all vertices
 * that go to 't', update the length of their shortest path to 't'. Since we need
 * shortest path from all vertices to get to 't', reverse the edges of the  graph
 * and apply Dijkstra starting from the vertex 't'.
 */

public class UpdatePath {

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
        int value, key;
        List<Vertex> adjList = new ArrayList<>();
        private Vertex(int value) {
            this.value = value;
            this.key = Integer.MAX_VALUE;
        }

        public static Vertex getInstance(int value) { return new Vertex(value); }
    }

    private static void reverse(Vertex[] graph, int n) {
        Vertex[] reverseGraph = new Vertex[n];
        for(int i = 0; i < n; i++) { reverseGraph[i] = Vertex.getInstance(i+1); }

        for(Vertex v: graph) {
            for(Vertex u: v.adjList) {
                reverseGraph[u.value - 1].adjList.add(reverseGraph[v.value - 1]);
            }
        }

        for(int i = 0; i < n; i++) { graph[i] = reverseGraph[i]; }
    }

    private static String join(char delimiter, List<Vertex> adjList) {
        int length = adjList.size();
        if(length == 0) { return ""; }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length-1; i++) {
            builder.append(adjList.get(i).value + "" + delimiter);
        }

        builder.append(adjList.get(length-1).value);
        return builder.toString();
    }

    static class VertexComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            int result = Integer.compare(o1.key, o2.key);
            return (result != 0) ? result : Integer.compare(o1.value, o2.value);
        }
    }

    private static void dijkstra(Vertex[] graph, int t) {
        PriorityQueue<Vertex> minQ = new PriorityQueue<>(new VertexComparator());
        graph[t].key = 0;
        minQ.add(graph[t]);

        while(!minQ.isEmpty()) {
            Vertex v = minQ.poll();
            for(Vertex u : v.adjList) {
                int edgeW = v.key + 1;
                if(u.key > edgeW) {
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

        out.println("Please enter the number of vertices and edges in the graph:-");

        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; i++) { graph[i] = Vertex.getInstance(i+1); }

        out.println("Please enter the edges in adjacency list format:- ");
        for(int i = 0; i < m; i++) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            v.adjList.add(u);
        }

        out.println("Please enter the value of the sink vertex:- ");
        int t = in.nextInt() - 1;
        reverse(graph, n);

        out.println("The values of the reversed graph:- ");
        for(Vertex v: graph) { out.println("Node:" + v.value +
                ", adjList: " + join(',', v.adjList)); }

        dijkstra(graph, t);
        for(Vertex v: graph) {
            if(v.parent != null) {
                out.println(v.value + "'s parent: " + v.parent.value);
            }
        }
        out.close();
        in.close();
    }
}
