import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: The CoinChange problem(Hackerrank - Dynamic Programmming)
 * @description: Given a number of units(n) and few denominations of coins, we have to determine
 * the number of possible ways in which we can form the change using those coins(repetition allowed).
 */

public class CoinChange {

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

    private static long possibleCoinChanges(int[] coins, int m, int n) {
        long[] opt = new long[n + 1];

        opt[0] = 1;
        for(int i = 0; i < m; ++i) {
            for(int j = coins[i]; j <= n; ++j) {
                opt[j] += opt[j - coins[i]];
            }
        }

        return opt[n];
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int m = in.nextInt();
        int[] coins = new int[m];
        for(int i = 0; i < m; i++) { coins[i] = in.nextInt(); }

        out.println(possibleCoinChanges(coins, m, n));
        out.close();
        in.close();
    }
}
