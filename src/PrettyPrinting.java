import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the Pretty Printing problem (Dynamic Programming)
 * @description : Pretty Printing is the problem of printing neatly on lines. Given a set of n words
 * {w[1], w[2],..., w[n]}, these must be appropriately divided into lines such that the value slack[l] =
 * L - {sum(c[j],..,c[k]) + (k-j-1)} for that line should be minimum. Then the objective is to find an
 * appropriate division of the lines such that the total slack of all lines is mimimized.
 * @notes : the number BIG below is test case specific and it has to be kept sufficiently bigger then L but not
 * Integer.MAX_VALUE because the latter overflows to negative integer, corrupting program logic.
 */

public class PrettyPrinting {

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

    /** this number is test case specific */
    private static final int BIG = 9999999;

    private static int slack(int i, int j, int[] w, int L) {
        int wLength = 0;
        for(int k = i; k <= j; ++k) {
            wLength += w[k-1];
        }
        int slackness = L - (wLength + (j - i));
        return (slackness < 0) ? L + BIG : slackness;
    }

    private static List<Integer> getSplitPoints(int[] w, int n, int L) {
        List<Integer> splitPoints = new ArrayList<>();
        int[] opt = new int[n+1];
        int[] sol = new int[n+1];

        Arrays.fill(opt, L + BIG);
        opt[1] = L - w[0];
        opt[0] = 0;

        for(int j = 2; j < n+1; ++j) {
            for(int i = 1; i <= j; ++i) {
                int cost = opt[i-1] + slack(i, j, w, L);
                if(opt[j] > cost) {
                    opt[j] = cost;
                    sol[j] = i;
                }
            }
        }

        for(int i = n-1; i > 0;) {
            splitPoints.add(0, sol[i]);
            i = sol[i]-1;
        }

        return splitPoints;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of words, the line size(L) and the lengths of words :-");

        int n = in.nextInt();
        int L = in.nextInt();
        int[] w = new int[n];
        for(int i = 0; i < n; ++i) { w[i] = in.nextInt(); }

        out.println("The appropriate split points for lines are :- ");
        getSplitPoints(w, n, L).forEach(p -> out.print(p + " "));
        out.close();
        in.close();
    }
}
