import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the CommonChild problem(Hackerrank - Dynamic Programming)
 * @description: Given a string, we can get a child of that string by deleting 0 or more characters
 * of that string. Given two strings, we have to find the length of the longest child that can be
 * formed from the two strings, a common child.
 */

public class CommonChild {

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

    /** Longest Common Subsequence - implementation */
    private static int longestLength(String A, String B) {
        int n = A.length();
        int m = B.length();
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int[][] opt = new int[n+1][m+1];

        for(int i = 1; i < n+1; ++i) {
            for(int j = 1; j < m+1; ++j) {
                if(a[i-1] == b[j-1]) {
                    opt[i][j] = opt[i-1][j-1] + 1;
                } else if(opt[i-1][j] > opt[i][j-1]) {
                    opt[i][j] = opt[i-1][j];
                } else {
                    opt[i][j] = opt[i][j-1];
                }
            }
        }

        return opt[n][m];
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        String A = in.next();
        String B = in.next();
        out.println(longestLength(A, B));

        out.close();
        in.close();
    }
}
