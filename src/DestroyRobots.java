import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the Destroy Robots problem (Dynamic Programming)
 * @description : There are n seconds, in our territory, on every second[i], x[i] robots arrive on
 * the boundary of the city and attack. We have to destroy them. For destroying them we have an
 * electronic plasma gun that we can activate on certain instants only. On activation, the gun
 * is able to destroy min(x[i], f[j]) robots only where j is the number of seconds elapsed
 * since the gun was last used. Find the instants when the gun should be activated so as
 * to kill maximum robots.
 */

public class DestroyRobots {

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

    private static int[] getActivationTimes(int[] x, int[] f, int n) {
        int[] opt = new int[n+1];
        int[] sol = new int[n+1];
        opt[1] = Math.min(x[0], f[0]);

        for(int j = 2; j < n+1; j++) {
            for(int i = 1; i <= j; i++) {
                int kills = opt[i-1] + Math.min(x[j-1], f[j-i]);
                if(kills > opt[j]) {
                    opt[j] = kills;
                    sol[j] = i;
                }
            }
        }

        return opt;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of seconds &");
        out.println("then the robots for each second and charge capacity:-");

        int n = in.nextInt();
        int[] x = new int[n];
        int[] f = new int[n];
        for(int i = 0; i < n; ++i) { x[i] = in.nextInt(); }
        for(int i = 0; i < n; ++i) { f[i] = in.nextInt(); }

        out.println("The times when electronic gun should be activated :-");
        out.println(Arrays.toString(getActivationTimes(x, f, n)));
        out.close();
        in.close();
    }
}
