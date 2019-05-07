import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Journey to the Moon problem(Hackerrank - Graph)
 * @description: UN wants to send 2 astronauts from different countries to the moon and we are provided
 * with a list of pair of numbers of astronaut ids such that the ids in a pair have astronauts belonging
 * to the same country. We must find the number of pairs possible for the UN to choose astronauts from.
 * Eg: for astronaut ids {0, 1, 2, 3}, the pairs given are {(1, 2), (2, 3)}, then the total number of
 * combinations possible are [0, 1], [0, 2], [0, 3].
 */

public class JourneyToMoon {

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

        public void dfs(int[] count) {
            color = 1;
            count[0]++;
            for(Vertex u: adjList) {
                if(u.color == 0) { u.dfs(count); }
            }
        }
    }

    private static long getPossiblePairs(Vertex[] graph) {
        List<Integer> components = new ArrayList<>();
        int count[] = {0}, compSum = 0;
        for(Vertex v: graph) {
            if (v.color == 0) {
                count[0] = 0;
                v.dfs(count);
                compSum += count[0];
                components.add(count[0]);
            }
        }

        long pairs = 0;
        for(int comp: components) {
            compSum -= comp;
            pairs += comp * compSum;
        }

        return pairs;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int p = in.nextInt();
        Vertex[] graph = new Vertex[n];
        for(int i = 0; i < n; ++i) { graph[i] = new Vertex(i + 1); }

        for(int i = 0; i < p; ++i) {
            Vertex v = graph[in.nextInt()];
            Vertex u = graph[in.nextInt()];
            v.adjList.add(u);
            u.adjList.add(v);
        }

        out.println(getPossiblePairs(graph));
        out.close();
        in.close();
    }
}
