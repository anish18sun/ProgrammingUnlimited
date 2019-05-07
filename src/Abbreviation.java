import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Abbreviation problem(Hackerrank - Dynamic Programming)
 * @description: Given two strings: 'A' and 'B', we have to check if 'A' can be converted to 'B' by
 * only performing the following operations on 'A':
 * (1) Convert zero or more characters of 'A' to Uppercase.
 * (2) Delete remaining small-case characters of 'A'.
 * If using only these two operations, we can convert 'A' to 'B', print 'yes' else print 'no'.
 */

public class Abbreviation {

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

    private static boolean isConvertible(String A, String B) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        boolean[][] opt = new boolean[a.length + 1][b.length + 1];

        int n = a.length;
        int m = b.length;
        opt[0][0] = true;
        for(int j = 1; j < m + 1; ++j) { opt[0][j] = false; }

        int count = 0;
        for(int k = 1; k < n + 1; ++k) {
            int i = k-1;
            if((a[i] >= 'A' && a[i] <= 'Z') || count == 1) {
                count = 1;
                opt[k][0] = false;
            } else {
                opt[k][0] = true;
            }
        }

        for(int k = 1; k < n + 1; ++k) {
            int i = k - 1;
            for(int l = 1; l < m + 1; ++l) {
                int j = l - 1;

                if(a[i] == b[j]) {
                    opt[k][l] = opt[k-1][l-1];
                } else if(a[i] >= 'A' && a[i] <= 'Z') {
                    opt[k][l] = false;
                } else if(Character.toUpperCase(a[i]) == b[j]) {
                    opt[k][l] = opt[k-1][l-1] | opt[k-1][l];
                } else {
                    opt[k][l] = opt[k-1][l];
                }
            }
        }

        return opt[n][m];
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();
        while(t-- > 0) {
            String A = in.next();
            String B = in.next();
            out.println(isConvertible(A, B) ? "YES" : "NO");
        }

        out.close();
        in.close();
    }
}
