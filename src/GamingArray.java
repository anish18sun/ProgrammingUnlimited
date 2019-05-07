import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to solve the Gaming Array problem - Hackerrank
 * @description : for the input array maintain an array that contains the maxiumum in input array uptill
 * that index, so we will get all maximums in single traversal of the array. One other smart way to do this
 * is to count the number of times maxValue gets updated while traversing the array, if that is even ANDY wins
 * otherwise BOB wins.
 */

public class GamingArray {

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

    private static void solve(int n, int[] arr, PrintStream out) {
        final String BOB = "BOB";
        final String ANDY = "ANDY";

        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            if(max < arr[i]) {
                max = arr[i];
                count++;
            }
        }

        out.println((count % 2 == 0) ? ANDY : BOB);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the size of array, followed by the array:- ");

        int n = in.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; ++i) { arr[i] = in.nextInt(); }

        solve(n, arr, out);
        out.close();
        in.close();
    }
}
