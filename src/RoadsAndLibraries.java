import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Roads and Libraries problem(Hackerrank - Graph)
 * @description: Given a list of cities(nodes) with roads between the cities in broken state, people
 * in every city must have access to a library so either we must build a library in a city at a cost
 * of C_lib or we must repair roads so that people can travel from one city to another city to access
 * library in other cities. A road can be repaired at a cost of C_road. We must choose to build roads
 * or libraries such that the total cost of construction is minimized. Print the minimum cost.
 */

public class RoadsAndLibraries {

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
        public Vertex(int value) { this.value = value; }

        public void dfs(long[] count) {
            color = 1;
            count[0]++;
            for(Vertex v: adjList) {
                if(v.color == 0) { v.dfs(count); }
            }
        }
    }

    public static long getMinCost(Vertex[] graph, int n, int cLib, int cRoad) {
        long allLibs = 0, allRoads = 0;
        long cost = 0, count[] = {0};
        for(Vertex v: graph) {
            if(v.color == 0) {
                count[0] = 0;
                v.dfs(count);
                allLibs = count[0] * cLib;
                allRoads = cLib + (count[0] - 1) * cRoad;
                cost += (allLibs <= allRoads) ? allLibs : allRoads;
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int cLib = in.nextInt();
            int cRoad = in.nextInt();
            Vertex[] graph = new Vertex[n];
            for(int i = 0; i < n; ++i) { graph[i] = new Vertex(i + 1); }

            for(int i = 0; i < m; ++i) {
                Vertex v = graph[in.nextInt() - 1];
                Vertex u = graph[in.nextInt() - 1];
                v.adjList.add(u);
                u.adjList.add(v);
            }

            out.println(getMinCost(graph, n, cLib, cRoad));
        }
        out.close();
        in.close();
    }
}
