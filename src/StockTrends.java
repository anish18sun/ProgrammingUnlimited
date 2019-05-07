import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the StockTrends problem (Dynamic Programming)
 * @description : We are given the stock prices for n days. Then starting from the first day, we must
 * only find the days that are greater than the first day price(rising trend) and also the maximum that
 * can be obtained from such a trend
 */

public class StockTrends {

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

    private static int maxPrice(int[] prices, int n) {
        int[] opt = new int[n];
        opt[n-1] = prices[n-1];

        for(int j = n-2; j >= 0; j--) {
            for(int i = j+1; i < n; i++) {
                if(prices[i] > prices[j]) {
                    opt[j] = Math.max(opt[j], prices[j] + opt[i]);
                }
            }
        }

        return opt[0];
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of days & the stock prices of n days :-");

        int n = in.nextInt();
        int[] prices = new int[n];
        for(int i = 0; i < n; i++) { prices[i] = in.nextInt(); }

        out.println("The maximmum price by rising trend: " + maxPrice(prices, n));
        out.close();
        in.close();
    }
}
