import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation the SignalString separation problem(Dynamic Programming)
 * @description: Given a string s of 0 and 1 and two other strings x, y, we have to
 * find whether there exist some half-division of the given string such that the divided
 * halves each form a prefix of some repetition of x or y. A repetition of x is defined
 * as x placed consecutively some number of times, eg: x = 101, then x' = 10110110 is a
 * repetition of x. The divided halves may not be continuous indexes of the given string.
 */

public class SignalString {

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

    static class State {
        int i, j, k;
        private State(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
        public static State getInstance(int i, int j, int k) {
            return new State(i, j, k);
        }
    }

    private static boolean isStringSafe(String x, String y, String s, PrintStream out) {
        ArrayDeque<State> queue = new ArrayDeque<>();
        char[] S = s.toCharArray();
        char[] X = x.toCharArray();
        char[] Y = y.toCharArray();

        if(S[0] == X[0]) {
            queue.offerLast(State.getInstance(1, 0, 1));
        }
        if(S[0] == Y[0]) {
            queue.offerLast(State.getInstance(0, 1, 1));
        }

        while(!queue.isEmpty()) {
            State state = queue.pollFirst();
            if(state.k == S.length) { return true; }

            if(S[state.k] == X[state.i % X.length]) {
                queue.offerLast(State.getInstance(state.i + 1, state.j, state.k + 1));
            }
            if(S[state.k] == Y[state.j % Y.length]) {
                queue.offerLast(State.getInstance(state.i, state.j + 1, state.k + 1));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the string x, y & s :- ");
        String x = in.next();
        String y = in.next();
        String s = in.next();

        out.println("Is string safe: " + isStringSafe(x, y, s, out));
        out.close();
        in.close();
    }
}
