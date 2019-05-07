import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Maximum Subarray problem(Hackerrank)
 * @description: Given an array, find the maximum sum that can be obtained in
 * (1) Any subsequence of the array(non-contiguous elements).
 * (2) Any subarray of the array(contiguous elements).
 */

public class MaximumSubarray {

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

    private static int[] getMaxSubarray(int[] a) {
        int maxAllArray = Integer.MIN_VALUE;
        int[] max = new int[2];
        int maxSubarray = 0;

        for(int i: a) {
            if((max[0] + i) > max[0]) { max[0] += i; }

            maxSubarray += i;
            maxAllArray = Math.max(maxAllArray, i);
            if(maxSubarray < 0) { maxSubarray = 0; }
            if(max[1] < maxSubarray) { max[1] = maxSubarray; }
        }

        if(max[0] == 0) { max[0] = maxAllArray; }
        if(max[1] == 0) { max[1] = maxAllArray; }
        return max;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();

        while(t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.nextInt();
            }

            int[] max = getMaxSubarray(a);
            out.println(max[1] + " " + max[0]);
        }
        out.close();
        in.close();
    }
}
