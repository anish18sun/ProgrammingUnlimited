import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the PowerSum problem(Hackerrank - Recursion)
 * @description: Given a number 'X' and 'N'. We must find all possible combinations of natural numbers
 * raised to power of 'N' that sum to the given number 'X'.
 */

public class PowerSum {

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

    private static int[] getPowers(int X, int N) {
        int[] powers = new int[X + 1]; int i = 1;
        for(; ; ++i) {
            int power = (int) Math.pow(i, N);
            if(power > X) { break; }
            powers[i] = power;
        }
        powers[X] = (N == 1) ? X : i - 1;
        return powers;
    }

    /** Method to get the index <= to the passed value 'X'*/
    private static int getIndex(int X, int N, int[] powers) {
        int high = (N == 1) ? powers.length - 1 : powers[powers.length - 1];
        int low = 0, mid;
        while(low < high) {
            mid = (low + high) >> 1;
            if(powers[mid] == X) {
                return mid;
            } else if(powers[mid] < X) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (powers[low] > X) ? low - 1: low;
    }

    private static int getPossibleCounts(int X, int N, int index, int[] powers) {
        if(X == 0) { return 1; }
        if(X < 0 || index <= 0) { return 0; }

        int possibleCounts = 0;
        for(; index > 0; --index) {
            possibleCounts += getPossibleCounts(X - powers[index], N, index - 1, powers);
        }
        return possibleCounts;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int X = in.nextInt();
        int N = in.nextInt();
        int[] powers = getPowers(X, N);
        int index = (N == 1) ? X : powers[X];
        out.println(getPossibleCounts(X, N, index, powers));

        out.close();
        in.close();
    }
}
