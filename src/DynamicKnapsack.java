import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to solve the dynamic knapsack problem in which the items cannot be divided.
 * @input :
 * 5 6
 * 3 2
 * 5 4
 * 6 5
 * 2 3
 * 1 1
 */

public class DynamicKnapsack {

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void fillKnapsack(int[][] values, int n, int W, List<Integer> I) {
        int[][] opt = new int[n+1][W+1];
        int[][] sol = new int[n+1][W+1];

        for(int i = 1; i < n+1; ++i) {
            for(int w = 1; w < W+1; ++w) {
                int vi = values[i-1][0];
                int wi = w - values[i-1][1];
                if(wi < 0) { vi = 0; }
                else { vi += opt[i-1][wi]; }
                if(opt[i-1][w] > vi) {
                    opt[i][w] = opt[i-1][w];
                    sol[i][w] = i-1;
                }
                else {
                    opt[i][w] = vi;
                    sol[i][w] = i;
                }
            }
        }

        int w = W;
        for(int j = n; w > 0; --j) {
            if(sol[j][w] == j) {
                I.add(0, sol[j][w]);
                w -= values[j-1][1];
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of items and the total weight,");
        out.println("then please enter the values and weight of single items. ");

        int n = in.nextInt();
        int W = in.nextInt();
        int[][] values = new int[n][2];
        for(int i = 0; i < n; ++i) {
            values[i][0] = in.nextInt();
            values[i][1] = in.nextInt();
        }

        List<Integer> items = new ArrayList<>();
        fillKnapsack(values, n, W, items);

        out.println("The chosen items by index:-");
        items.forEach(i -> out.print(i + " "));
        out.close();
        in.close();
    }
}
