import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the StringConstuction problem(Hackerrank)
 * @description: Given a string 'S', we have to replicate it to another string 'P', by following steps:
 * (1) Append a character to the end of 'P' at the cost of 1 dollar.
 * (2) Choose any substring of 'P' and append it to the end of 'P' at no charge.
 */

public class StringConstruction {

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader() {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(System.in), 32678);
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

    private static int getRelicationCost(String S) {
        boolean[] repeatCheck = new boolean[26];
        char[] s = S.toCharArray();

        int cost = 0;
        for(char c: s) {
            if(!repeatCheck[c - 'a']) {
                repeatCheck[c - 'a'] = true;
                cost++;
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();
        while(t-- > 0) {
            String S = in.next();
            out.println(getRelicationCost(S));
        }

        out.close();
        in.close();
    }
}
