import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implementation of the AstroEvents Problem(Dynamic Programming)
 * @description : We are given a timeline of n seconds when n astro events will occur in
 * night sky, each on a specific second. We have a telescope to view the events but each
 * event must be viewed on the second on which it occurs otherwise we will miss it. Now,
 * the sky is mapped to one-dimensional coordinate system, such that an astro event occurs
 * on a specific point on the coordinate system. When an event occurs, the telescope should
 * be at that coordinate to be able to view it. However, the telescope moves at a speed of
 * 1 coordinate per second.
 */

public class AstroEvents {

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

    private static int[] getEvents(int[] d, int n) {
        int[] opt = new int[n+1];
        int[] sol = new int[n+1];

        for(int j = 1; j < n+1; j++) {
            for(int i = 0; i < j; i++) {
                if((j-i) >= Math.abs(d[j]-d[i])) {
                    if(opt[j] < opt[i] + 1) {
                        opt[j] = opt[i] + 1;
                        sol[j] = i;
                    }
                }
            }
        }

        return sol;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of seconds and the degree of each event :-");

        int n = in.nextInt();
        int[] d = new int[n+1];
        for(int i = 1; i < n+1; i++) { d[i] = in.nextInt(); }
        out.println("Events to view :" + Arrays.toString(getEvents(d, n)));

        out.close();
        in.close();
    }
}
