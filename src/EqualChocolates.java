import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the EqualChocolates problem(Hackerrank)
 * @description: Given a distribution of chocolates to the employees of a company, there may be
 * differences in the number of chocolates that each employee has. We have to make the number of
 * chocolates for each employee equal(same number). However, this has to be done in rounds: in one
 * round we can only add 1, 2 or 5 chocolates to all but one employee and the objective is to achieve
 * this equal distribution with the minimum number of operations(rounds).
 */

public class EqualChocolates {

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

    private static int minElement(int[] arr) {
        int min = Integer.MAX_VALUE;
        for(int n: arr) { min = Math.min(min, n); }

        return min;
    }

    private static int minOperations(int[] chocs, int n) {
        int numOps = 0;
        int min = minElement(chocs);

        for(int i = 0; i < n; i++) {
            int nChocs = (int)(Math.floor((chocs[i] - min) / 5.0));
            chocs[i] -= (5 * (int)(Math.floor((chocs[i] - min) / 5.0)));
            numOps += nChocs;
        }

        min = minElement(chocs);
        int[] freq = new int[5];
        for(int choc: chocs) { freq[choc - min]++; }

        int extraOps = (freq[1] + freq[2]) + 2*(freq[3] + freq[4]);
        int extraOps1 = (freq[0] + freq[1] + freq[4]) + 2*(freq[2] + freq[3]);
        int extraOps2 = (freq[0] + freq[3]) + 2*(freq[1] + freq[2] + freq[4]);

        if(extraOps > extraOps1) { extraOps = extraOps1; }
        if(extraOps > extraOps2) { extraOps = extraOps2; }
        return numOps + extraOps;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();

        while(t-- > 0) {
            int n = in.nextInt();
            int[] chocs = new int[n];
            for (int i = 0; i < n; i++) {
                chocs[i] = in.nextInt();
            }
            out.println(minOperations(chocs, n));
        }
        out.close();
        in.close();
    }
}
