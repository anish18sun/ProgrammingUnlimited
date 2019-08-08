import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Candies problem (Hackerrank)
 * @description: Given a set of labels of students(eg: [5,4,3,2,1,2,3,4]), a teacher must
 * distribute candies to students such that those with greater labels get more candies than
 * those with less-valued labels.The objective is to minimize the total cost of candies handed out.
 */

public class Candies {

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

    private static int getMinCandies(int[] arr, int n) {
        int[] aux = new int[n];
        Arrays.fill(aux,1);
        int cost = 0;

        for(int i = 1; i < n; ++i) {
            if(arr[i] > arr[i - 1]) {
                aux[i] = aux[i - 1] + 1;
            }
        }

        for(int i = n - 2; i >= 0; --i) {
            if(arr[i] > arr[i + 1]) {
                int value = aux[i + 1] + 1;
                aux[i] = (value > aux[i]) ? value : aux[i];
            }
        }

        for(int count: aux) { cost += count; }
        return cost;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i) { arr[i] = in.nextInt(); }

        out.println(getMinCandies(arr, n));
        out.close();
        in.close();
    }
}
