import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the SamAndSubstrings problem(Hackerrank - Dynamic Programming)
 * @description: Given a number as a string, we need to provide the sum of all the substrings of
 * the string when each is considered as an individual number.
 */

public class SamSubstrings {

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

    private static final int MOD = 1000000007;

    private static long sumSubstring(String number) {
        char[] numChars = number.toCharArray();
        long sum = 0, f = 1;

        for(int i = numChars.length - 1; i >= 0; --i) {
            sum = (sum + (numChars[i] - '0') * f * (i + 1)) % MOD;
            f = (f * 10 + 1) % MOD;
        }

        return sum;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader();
        PrintStream out = System.out;
        String number = in.next();

        out.println(sumSubstring(number));
        out.close();
        in.close();
    }
}
