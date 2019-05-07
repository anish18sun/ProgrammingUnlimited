import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the StringSubsequence problem(Mathematics)
 * @sescription: Given a string comprising only the characters {a, b, c}, we must find the number of
 * combinations of the form: [a^i b^j c^k] that can be formed from the given input string. The i,j,k
 * values have a minima of 1. Example: abcabc, gives us strings: abc four times, then we have aabc,
 * abbc and abcc for a total of four combinations possible from the given input string, so ans: 4.
 */

public class StringSubsequence {

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

    private static int getCombinations(String S) {
        int aCount = 0, bCount = 0, cCount = 0;
        char[] s = S.toCharArray();

        for(char c : s) {
            if(c == 'a') {
                aCount = 1 + 2 * aCount;
            } else if(c == 'b') {
                bCount = aCount + 2 * bCount;
            } else if(c == 'c') {
                cCount = bCount + 2 * cCount;
            }
        }

        return cCount;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        String S = in.next();
        out.println(getCombinations(S));

        out.close();
        in.close();
    }
}
