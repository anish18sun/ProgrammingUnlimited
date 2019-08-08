import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of MaxMin problem (HackerRank)
 * @description: Given an array of numbers, find a sub-array of length 'k' that whose
 * maximum and minimum have minimum difference i.e. max(sub-array) - min(sub-array) is
 * minimized.
 */

public class MaxMin {

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

    private static int getMinDiff(int[] arr, int n, int k) {
        int min, max, diff = Integer.MAX_VALUE;
        Arrays.sort(arr);

        for(int i = k - 1; i < n; ++i) {
            max = arr[i]; min = arr[i - (k - 1)];
            int kDiff = max - min;

            diff = (kDiff < diff) ? kDiff : diff;
        }
        return diff;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i) { arr[i] = in.nextInt(); }

        out.println(getMinDiff(arr, n, k));
        out.close();
        in.close();
    }
}
