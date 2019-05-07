import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the SherlockAndCost problem(Hackerrank - Dynamic Programming)
 * @description: An array 'B' is given and we have to form another array 'A', such that
 * A[i] is in [1, B[i]] and the sum |A[i] - A[i-1]| is maximised.
 */

public class SherlockCost {

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

    private static int maxCost(int[] B, int n) {
        int low = 0, high = 0;

        for(int i = 1; i < n; i++) {
            int iLow = Math.max(low, high + Math.abs(B[i-1] - 1));
            int iHigh = Math.max(high + Math.abs(B[i] - B[i-1]), low + Math.abs(B[i] - 1));

            low = iLow;
            high = iHigh;
        }

        return Math.max(low, high);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of elements and then the elements: ");

        int n = in.nextInt();
        int[] B = new int[n];
        for(int i = 0; i < n; i++) { B[i] = in.nextInt(); }

        out.println(maxCost(B, n));
        out.close();
        in.close();
    }
}
