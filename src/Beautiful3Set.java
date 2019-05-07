import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to solve the Beautiful3Set problem - hackerrank
 */

public class Beautiful3Set {

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

    static class Triple {
        int x, y, z;
        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static void solve(int n, PrintStream out) {
        Set<Triple> S = new HashSet<>();
        Set<Integer> X = new HashSet<>();
        Set<Integer> Y = new HashSet<>();
        Set<Integer> Z = new HashSet<>();

        for (int x = 0; x < n; x += 1) {
            if(X.contains(x)) { continue; }

            for(int y = (x+1) % n; y < n; y += 1) {
                int z = n - (x + y);
                if (z < 0) { continue; }

                if(X.contains(x)) { continue; }
                if(Y.contains(y)) { continue; }
                if(Z.contains(z)) { continue; }
                S.add(new Triple(x, y, z));
                X.add(x);
                Y.add(y);
                Z.add(z);

                if(X.contains(z)) { continue; }
                if(Y.contains(x)) { continue; }
                if(Z.contains(y)) { continue; }
                S.add(new Triple(z, x, y));
                X.add(z);
                Y.add(x);
                Z.add(y);

                if(X.contains(y)) { continue; }
                if(Y.contains(z)) { continue; }
                if(Z.contains(x)) { continue; }
                S.add(new Triple(y, z, x));
                X.add(y);
                Y.add(z);
                Z.add(x);
            }
        }

        out.println(S.size());
        S.forEach(triple -> out.println(triple.x + " " + triple.y + " " + triple.z));
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the value of n :-");

        int n = in.nextInt();
        solve(n, out);
        out.close();
        in.close();
    }
}
