import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Goodland Electricity(Hackerrank)
 * @description: Given a list of cities in the form [0,0,1,1,1,0,0] where '0' indicates
 * that a city cannot have a power plant while '1' indicates that a city can have a power
 * plant. For a given number 'k', a power plant built in a city can cover up to 'k' cities
 * left and right of it. We must find the minimum number of cities in which to build power
 * plants so that all cities are supplied with electricity.
 */

public class GoodlandElectricity {

    static class InputReader{
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

    private static int getMinCities(int[] pos, int k, int n) {
        int cost = 0;

        for(int i = 0; i < n;) {
            int range = i + (k - 1);
            range = (range >= n) ? n - 1 : range;

            int powerPos = pos[range];
            if((powerPos + k) <= i || powerPos == -1) { return -1; }

            i = powerPos + k;
            cost += 1;
        }
        return cost;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int powerPos = -1;
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        int[] pos = new int[n];
        Arrays.fill(pos, -1);

        for(int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
            if(arr[i] == 1) {
                powerPos = i;
            }
            pos[i] = powerPos;
        }

        out.println(getMinCities(pos, k, n));
        out.close();
        in.close();
    }
}
