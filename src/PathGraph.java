import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the Path Graph problem of finding the maximum set of independent vertices
 * in the given graph. A path graph is one in which there is an edge between two vertices i,j iff
 * |i-j| = 1. An independent set of vertices in a graph is a set such that no two vertices have
 * direct edges between them. Similar to the 'BillBoards' problem (Dynamic Programming)
 */

public class PathGraph {

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

    private static List<Integer> getIndependentSet(int[] path, int n){
        List<Integer> positions = new ArrayList<>();
        int[] opt = new int[n];
        int[] sol = new int[n];

        sol[1] = 1;
        opt[0] = path[0];
        opt[1] = Math.max(path[0], path[1]);
        for(int i = 2; i < n; ++i) {
            int w = path[i] + opt[i-2];
            if(opt[i-1] > w) {
                opt[i] = opt[i-1];
                sol[i] = i-1;
            }
            else {
                opt[i] = w;
                sol[i] = i;
            }
        }

        for(int i = n-1; i >= 0; --i) {
            if(sol[i] == i) {
                positions.add(0, i+1);
                i -= 1;
            }
        }

        return positions;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of vertices and then their weights in Graph path :-");

        int n = in.nextInt();
        int[] path = new int[n];
        for(int i = 0; i < n; ++i) { path[i] = in.nextInt(); }

        out.println("The independent set of max weight vertices :- ");
        getIndependentSet(path, n).forEach(v -> out.print(v + " "));
        out.close();
        in.close();
    }
}
