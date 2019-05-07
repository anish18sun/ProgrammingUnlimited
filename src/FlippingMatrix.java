import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/***
 * @author anish
 * @class : class to solve the Flipping Matrix problem - Hackerrank
 */

public class FlippingMatrix {

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

    private static void solve(int n2Length, int[][] matrix, PrintStream out) {
        int n2 = n2Length - 1;
        int nLength = n2Length / 2;

        int sum = 0, max = 0;
        for(int i = 0; i < nLength; ++i) {
            for(int j = 0; j < nLength; ++j) {
                max = matrix[i][j];
                max = Math.max(max, matrix[i][n2-j]);
                max = Math.max(max, matrix[n2-i][j]);
                max = Math.max(max, matrix[n2-i][n2-j]);
                sum += max;
            }
        }

        out.println(sum);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the n value, followed by n rows of n values: ");

        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                matrix[i][j] = in.nextInt();
            }
        }

        solve(n, matrix, out);
        out.close();
        in.close();
    }
}
