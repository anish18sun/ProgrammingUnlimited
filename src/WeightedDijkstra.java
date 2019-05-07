import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class : to implement the weighted dijkstra problem
 * @description : The problem is the same as the single-source shortest path problem only difference
 * is that, now we are given an input weight M, which will keep decreasing as we go through a node(vertex).
 * So we are also given the weights for each vertex. We need to find the shortest path such that the sum of
 * edge weights are reduced and the we also get to the destination without exhausting the total weight.
 * @input :
 * 7 8 5
 * 2 1 2 2 1 3 1
 * 1 2 2
 * 1 3 1
 * 1 6 2
 * 2 5 2
 * 6 5 2
 * 3 4 1
 * 4 5 1
 * 5 7 2
 */

public class WeightedDijkstra {

    static class InputReader{
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
        int value, m;
        Map<Vertex, Integer> adjList = new HashMap<>();
        public Vertex(int value, int m) {
            this.m = m;
            this.value = value;
        }
    }

    /** Singleton */
    static class Pair {
        int i, j;
        private Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
        private static Pair pair = new Pair(0, 0);
        public static Pair getInstance() { return pair; }

    }

    private static void dijkstra(Vertex[] graph, int n, int M, List<Vertex> S) {
       M -= graph[0].m;
       int[][] opt = new int[n][M+1];
       int[][] sol = new int[n][M+1];
       boolean[][] states = new boolean[n][M+1];

       for(int i = 0; i < n; ++i) {
           Arrays.fill(opt[i], Integer.MAX_VALUE);
           Arrays.fill(sol[i], Integer.MAX_VALUE);
       }

       opt[0][M] = 0;
       Pair state = Pair.getInstance();

       while(true) {
           findMinState(opt, states, state);
           if(state.i == -1) { break; }

           for(Vertex u : graph[state.i].adjList.keySet()) {
               int remainingW = state.j - u.m;
               if(remainingW < 0) { continue; }
               int edgeW = opt[state.i][state.j]
                       + graph[state.i].adjList.get(u);
               if(opt[u.value-1][remainingW] > edgeW) {
                   opt[u.value-1][remainingW] = edgeW;
                   sol[u.value-1][remainingW] = state.i;
               }
           }
       }

       int k, i = 0;
       int min = Integer.MAX_VALUE;
       for(k = 0; k < M+1; ++k) {
           if(opt[n-1][k] < min) {
               min = opt[n-1][k];
               i = k;
           }
       }

       for(int j = n-1; j > 0;) {
           S.add(0, graph[j]);
           k = j;
           j = sol[j][i];
           i += graph[k].m;
       }
    }

    private static void findMinState(int[][] opt, boolean[][] states, Pair state) {
        state.i = -1;
        state.j = -1;
        int n = opt.length;
        int m = opt[0].length;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(!states[i][j]) {
                    if(opt[i][j] < min) {
                        state.i = i;
                        state.j = j;
                    }
                }
            }
        }
        if(state.i == -1) { return; }
        states[state.i][state.j] = true;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of vertices, the edges & total money :- ");
        int n = in.nextInt();
        int m = in.nextInt();
        int M = in.nextInt();

        out.println("Please enter the money(weights) for each of the vertex :- ");
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; ++i) { graph[i] = new Vertex(i+1, in.nextInt()); }

        out.println("Please enter the graph in the adjacency list format :- ");
        for(int i = 0; i < m; ++i) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            int w = in.nextInt();
            v.adjList.put(u, w);
            u.adjList.put(v, w);
        }

        List<Vertex> shortestPath = new ArrayList<>();
        long start = System.currentTimeMillis();
        dijkstra(graph, n, M, shortestPath);
        long end = System.currentTimeMillis();
        shortestPath.add(0, graph[0]);

        out.println("The shortest path from start to end :- ");
        shortestPath.forEach(v -> out.print(v.value + " "));
        out.println("Time taken: " + (end - start) + "ms");
        out.close();
        in.close();
    }
}
