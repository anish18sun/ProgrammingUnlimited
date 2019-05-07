import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of computing Combination( nCr )
 */

public class Combination {

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

    private static int nCr(int n, int r, int p) {
        int[] c = new int[r+1];
        c[0] = 1;

        for(int i = 1; i <= n; ++i) {
            for(int j = Math.min(i, r); j > 0; --j) {
                c[j] = (c[j] + c[j-1]) % p;
            }
        }

        return c[r];
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int r = in.nextInt();
        int p = 1000000;

        out.println("Combination: " + nCr(n, r, p));
        out.close();
        in.close();
    }
}
