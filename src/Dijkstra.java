import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class : to implement the Dijkstra algorithm on a graph. Single source shortest path problem,
 * to also be able to trace back the path from a vertex to the source vertex, we maintain a pointer
 * that points to the parent vertex of a vertex and every time that we update the key of a vertex we
 * also update the parent of the current vertex.
 * @description : the order of execution of certain statements in the program are really important
 * first, the comparator compares the keys which is used for all operations in the PriorityQueue and
 * so if the key value of two nodes are different, it is easy to resolve the nodes but consider the case
 * of removal of elements from the PriorityQueue, in that case if two nodes have equal keys then arbitrary
 * removal will happen, to prevent that if keys are equal we perform comparison on the values of the nodes.
 * Second, in the implementation of Dijkstra, the order of statements, of removal from the PriorityQueue
 * and then updation and adding back is important because of the first fact that removal will be first
 * attempted on keys, if they are equal then vertex values will be considered.
 */

public class Dijkstra {

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader() {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(System.in), 32768);
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public void close() {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Vertex {
        Vertex parent;
        int key, value;
        Map<Vertex, Integer> adjList = new HashMap<>();

        public Vertex(int value) {
            this.value = value;
            this.key = Integer.MAX_VALUE;
        }
    }

    static class VertexComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            int result = Integer.compare(o1.key, o2.key);
            return (result != 0) ? result : Integer.compare(o1.value, o2.value);
        }
    }

    private static void dijkstra(Vertex[] graph) {
        PriorityQueue<Vertex> minQ = new PriorityQueue<>(new VertexComparator());
        graph[0].key = 0;
        minQ.add(graph[0]);

        while(!minQ.isEmpty()) {
            Vertex v = minQ.poll();
            for(Vertex u : v.adjList.keySet()) {
                int edgeW = v.key + v.adjList.get(u);
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

        out.println("Please enter the number of vertices in the graph & the number of edges :-");

        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for (int i = 0; i < n; ++i) { graph[i] = new Vertex(i + 1); }

        out.println("Please enter the graph in adjacency list format, (v -> u, w) :- ");
        for (int i = 0; i < m; ++i) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            int w = in.nextInt();
            v.adjList.put(u, w);
        }

        dijkstra(graph);
        List<Vertex> shortestPath = new ArrayList<>();
        for(Vertex v = graph[n-1]; v != graph[0]; v = v.parent) { shortestPath.add(0, v); }

        shortestPath.add(0, graph[0]);
        out.println("The shortest path from start to end :-");
        shortestPath.forEach(v -> out.print(v.value + " "));
        out.close();
        in.close();
    }
}


