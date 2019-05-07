import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the stress jobs problem
 * @description : For n days, we are given a list of jobs; high stress jobs{h1, h2,...,hn} and low stress
 * jobs{l1, l2,...,ln}. These arrays of low and high stress jobs indicate the revenue earned on that day
 * by doing that job. For a hish stress job done on day i, the previous day should be no work day,
 * however for low stress job no such restriction holds. By these restrictions, find the maximum
 * revenue that can be obtained by selecting a sequence of jobs. (Dynamic Programming)
 */

public class StressJobs {

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

    private static char[] getJobSeq(int[] l, int[] h, int n) {
        char[] seq = new char[n];
        int[] opt = new int[n];
        final char L = 'l';
        final char H = 'h';
        final char N = 'n';

        /** base cases */
        if(h[0] + l[1] > h[1]) {
            seq[0] = H;
            seq[1] = L;
            opt[0] = h[0];
            opt[1] = l[1];
        }
        else {
            seq[0] = N;
            seq[1] = H;
            opt[1] = h[1];
        }

        for(int i = 2; i < n; ++i) {
            int low = l[i] + opt[i-1];
            int high = h[i] + opt[i-2];
            if(high > low) {
                seq[i] = H;
                opt[i] = high;
            }
            else {
                seq[i] = L;
                opt[i] = low;
            }
        }

        for(int i = n-1; i >= 0; --i) {
            if((seq[i] == H) && (i-1 >= 0)) { seq[--i] = N; }
        }

        return seq;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of days and low and high stress job revenues: ");

        int n = in.nextInt();
        int[] l = new int[n];
        int[] h = new int[n];
        for(int i = 0; i < n ; ++i) {
            l[i] = in.nextInt();
            h[i] = in.nextInt();
        }

        out.println("The sequence of jobs :- ");
        for(char c : getJobSeq(l, h, n)) { out.print(c + " "); }

        out.close();
        in.close();
    }
}
