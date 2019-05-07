import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the vertex coloring problem
 * @description : given a graph, check whether it's possible to color the vertices of the graph using
 * minimum number of colors such that no two vertices have the same color.
 */

public class VertexColoring {

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
        int value, color;
        List<Vertex> adjList = new ArrayList<>();
        public Vertex(int value) {
            this.color = 0;
            this.value = value;
        }

        /** correct this method - not coloring good */
        private void colorDfs(int u, int skip) {
            color = u;
            int nxt = 1;
            while(nxt == u || nxt == skip) { nxt++; }
            for(Vertex v : adjList) {
                if(v.color == 0) { v.colorDfs(nxt, u); }
                while(nxt == u || nxt == skip) { nxt++; }
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of vertices and the edges :- ");

        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; ++i) { graph[i] = new Vertex(i+1); }

        out.println("Please enter the edges of the graph :- ");
        for(int i = 0; i < m; ++i) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            v.adjList.add(u);
        }

        graph[0].colorDfs(1, 2);
    }
}
