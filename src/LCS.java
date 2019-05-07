import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the Longest Common Subsequence problem, dynamic programming
 * @description : to implement the Longest Palindromic Subsequence(non-continuous) problem,
 * given a string just reverse the given string and run the LCS algorithm.
 */

public class LCS {

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

    private static final int D = 2;
    private static final int U = 1;
    private static final int L = 0;

    private static String getLCS(String X, String Y) {
        int n = X.length();
        int m = Y.length();
        char[] x = X.toCharArray();
        char[] y = Y.toCharArray();
        int[][] opt = new int[n+1][m+1];
        int[][] sol = new int[n+1][m+1];

        for(int i = 1; i < n+1; ++i) {
            for(int j = 1; j < m+1; ++j) {
                if(x[i-1] == y[j-1]) {
                    opt[i][j] = opt[i-1][j-1] + 1;
                    sol[i][j] = D;
                }
                else if(opt[i-1][j] > opt[i][j-1]) {
                    opt[i][j] = opt[i-1][j];
                    sol[i][j] = U;
                }
                else {
                    opt[i][j] = opt[i][j-1];
                    sol[i][j] = L;
                }
            }
        }

        int i = n, j = m;
        StringBuilder builder = new StringBuilder();
        while(i > 0) {
            if(sol[i][j] == D) {
                builder.append(x[i-1]);
                i -= 1; j -= 1;
            }
            else if(sol[i][j] == U) { i -= 1; }
            else if(sol[i][j] == L) { j -= 1; }
        }

        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the 2 strings for Longest Common Subsequence:- ");

        String X = in.next();
        String Y = in.next();
        out.println("The LCS: " + getLCS(X, Y));

        out.close();
        in.close();
    }
}
