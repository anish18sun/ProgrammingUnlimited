import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Road Repairing problem(Hackerrank)
 * @description: Given a list of cities, some of the road connecting the cities are functional
 * and some of them are damaged. We must repair only enough damaged roads that all cities are
 * connected. Since the edges are not weighted, we can use BFS to find shortest paths.
 */

public class RoadRepairing {

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

    enum RoadState {FUNCTIONAL, DAMAGED}

    static class Vertex {
        int value, color;
        Map<Vertex, RoadState> roadMap = new HashMap<>();
        public Vertex(int value) { this.value = value; }
    }

    private static int repairRoads(Vertex[] graph) {
        ArrayDeque<Vertex> queue = new ArrayDeque<>();
        int repairCount = 0;

        queue.addLast(graph[0]);
        while(!queue.isEmpty()) {
            Vertex v = queue.removeFirst();
            for(Map.Entry<Vertex, RoadState> vEntry: v.roadMap.entrySet()) {
                Vertex u = vEntry.getKey();
                RoadState roadState = vEntry.getValue();
                if(u.color == 0) {
                    u.color = 1;
                    queue.addLast(u);
                    if(roadState == RoadState.DAMAGED) { repairCount++; }
                }
            }
        }

        return repairCount;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int T = in.nextInt();
        Vertex[] graph = new Vertex[T];
        for(int i = 0; i < T; ++i) { graph[i] = new Vertex(i+1); }

        int m = in.nextInt();
        for(int i = 0; i < m; ++i) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            v.roadMap.put(u, RoadState.FUNCTIONAL);
            u.roadMap.put(v, RoadState.FUNCTIONAL);
        }

        int n = in.nextInt();
        for(int i = 0; i < n; ++i) {
            Vertex v = graph[in.nextInt() - 1];
            Vertex u = graph[in.nextInt() - 1];
            v.roadMap.put(u, RoadState.DAMAGED);
            u.roadMap.put(v, RoadState.DAMAGED);
        }

        out.println(repairRoads(graph));
        out.close();
        in.close();
    }
}
