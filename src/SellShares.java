import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implementation of the SellShares problem(Dynamic Programming)
 * @description : We are given the prices of shares for the next 'n' days & the
 * total number of shares that we have for selling, We must decide how many shares
 * must be sold on each day so as to maximize profit. If we sell y1 shares on day1
 * then its price per share becomes (p[i] - y1), so total amount : (p[i] - f(y1)).y1,
 * if we sell y2 number of shares on day2, the price becomes : (p[i] - f(y1) - f(y2)).y2
 * and so on till the nth day. Considering these factors we must find maximum amount of
 * profit possible by selling shares and also the number of shares sold on each day.
 */

public class SellShares {

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

    private static int f(int y) {
        return (y <= 40) ? 1 : 20;
    }

    private static int[] prevDayShares(int[][]sol, int i, int x, int X) {
        int[] prevDayShares = new int[i];
        prevDayShares[i-1] = x;
        x = X - x;

        for(i -= 1 ; i > 0 && x > 0; i--) {
            prevDayShares[i-1] = sol[i][x];
            x -= sol[i][x];
        }
        return prevDayShares;
    }

    private static int prevDayDepreciation(int[][] sol, int i, int x, int X) {

        int depreciation = 0;
        int[] prevDayShares = prevDayShares(sol, i, x, X);
        for(int s : prevDayShares) { depreciation += f(s); }

        return depreciation;
    }

    private static int[] shareSell(int[] prices, int n, int X) {
        int[][] opt = new int[n+1][X+1];
        int[][] sol = new int[n+1][X+1];
        int[] share = new int[n];

        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < X+1; j++) {
                for(int l = 1; l <= j; l++) {
                    int sellCost = (prices[i-1] - prevDayDepreciation(sol, i, l, j)) * l
                        + opt[i-1][j-l];
                    if(sellCost > opt[i][j]) {
                        opt[i][j] = sellCost;
                        sol[i][j] = l;
                    }
                }
            }
        }

        int x = X;
        for(int i = n; i > 0 && x > 0; i--) {
            share[i-1] = sol[i][x];
            x -= sol[i][x];
        }

        return share;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of days, total number of shares");
        out.println("& the prices of each day :-");

        int n = in.nextInt();
        int X = in.nextInt();
        int[] prices = new int[n];
        for(int i = 0; i < n; i++) { prices[i] = in.nextInt(); }

        out.println("The number of shares sold each day: "
                + Arrays.toString(shareSell(prices, n, X)));
        out.close();
        in.close();
    }
}
