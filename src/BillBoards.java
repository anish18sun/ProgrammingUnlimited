import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the billboard placing problem, given a set of locations all of which are less
 * than some value M, such that each site gives some revenue of placing a billboard at that point, we
 * must provide the appropriate positions to place the billboards such that two billboards are not placed
 * within 5 miles of one another. (Dynamic Programming)
 */

public class BillBoards {

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

    private static List<Integer> placeBoards(int[][] x, int n) {
        List<Integer> positions = new ArrayList<>();
        int[] opt = new int[n];
        int[] sol = new int[n];
        opt[0] = x[0][1];

        for(int i = 1; i < n; ++i) {
            int p = i;
            int rev = x[i][1];
            while((p >= 0) && (x[i][0] - x[p][0] < 5)) p--;
            if(p >= 0) { rev += opt[p]; }
            if(opt[i-1] > rev) {
                opt[i] = opt[i-1];
                sol[i] = i-1;
            }
            else {
                opt[i] = rev;
                sol[i] = i;
            }
        }

        /** below loop : O(n) */
        for(int i = n-1; i >= 0;) {
            if(sol[i] == i) {
                int j = i;
                positions.add(0, x[i][0]);
                while((i >= 0) && (x[j][0] - x[i][0] < 5)) i--;
            }
            else { i -=1; }
        }

        return positions;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of cities, their distances and revenues :- ");

        int n = in.nextInt();
        int[][] x = new int[n][2];
        for(int i = 0; i < n; ++i) {
            x[i][0] = in.nextInt();
            x[i][1] = in.nextInt();
        }

        out.println("The boards may be placed at locations:- ");
        placeBoards(x, n).forEach(i -> out.print(i + " "));
        out.close();
        in.close();
    }
}
