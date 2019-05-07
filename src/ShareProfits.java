import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the share profit maximization problem
 * @description : Given the share prices of trading for n days, find the right days to buy and sell
 * so that net profit can be maximized.
 */

public class ShareProfits {

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

    private static int[] maxProfitDays(int[] prices, int n) {
        int[][] min = new int[n][2];
        int minPrice = prices[0];
        int[] days = new int[2];
        int minPriceDay = 0;

        for(int i = 0; i < n; ++i) {
            min[i][0] = minPrice;
            min[i][1] = minPriceDay;
            if(minPrice > prices[i]) {
                minPrice = prices[i];
                minPriceDay = i;
            }
        }

        int profit = 0;
        for(int i = 1; i < n; ++i) {
            if(profit < (prices[i] - min[i][0])) {
                profit = prices[i] - min[i][0];
                days[0] = min[i][1];
                days[1] = i;
            }
        }

        return days;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of days and the share prices for each day:-");

        int n = in.nextInt();
        int[] prices = new int[n];
        for(int i = 0; i < n; ++i) { prices[i] = in.nextInt(); }

        int[] days = maxProfitDays(prices, n);
        out.println("Sell on : " + days[1]);
        out.println("Buy on : " + days[0]);
        out.close();
        in.close();
    }
}
