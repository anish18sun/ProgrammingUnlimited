import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to solve the New Year Chaos problem - hackerrank
 */

public class NewYearChaos {

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

    private static void solve(int[] q, PrintStream out) {
        int bribes = 0;
        int length = q.length;
        int[] pos = new int[length+1];
        for(int i = 0; i < length+1; ++i) { pos[i] = i; }
        int[] orig = Arrays.copyOf(pos, length+1);

        for(int i = 0; i < length; ++i) {
            if(pos[q[i]] != (i+1)) {
                int diff = pos[q[i]] - (i+1);
                if(diff > 2) { out.println("Too chaotic"); return; }
                if(diff == 2) {
                    bribes += 2;
                    int temp = orig[pos[q[i]]];
                    orig[pos[q[i]]] = orig[pos[q[i]]-1];
                    orig[pos[q[i]]-1] = temp;
                    pos[orig[pos[q[i]]]]++;
                    pos[q[i]]--;

                    temp = orig[pos[q[i]]];
                    orig[pos[q[i]]] = orig[pos[q[i]]-1];
                    orig[pos[q[i]]-1] = temp;
                    pos[orig[pos[q[i]]]]++;
                    pos[q[i]]--;
                }
                else if(diff == 1) {
                    bribes += 1;
                    int temp = orig[pos[q[i]]];
                    orig[pos[q[i]]] = orig[pos[q[i]]-1];
                    orig[pos[q[i]]-1] = temp;
                    pos[orig[pos[q[i]]]]++;
                    pos[q[i]]--;
                }
            }
        }

        out.println(bribes);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the size of the array and the array elements:- ");

        int n = in.nextInt();
        int[] queue = new int[n];
        for(int i = 0; i < n; ++i) { queue[i] = in.nextInt(); }

        solve(queue, out);
        out.close();
        in.close();
    }
}
