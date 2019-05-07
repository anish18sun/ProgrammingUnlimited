import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Highest Value Palindrome problem (Hackerrank)
 * @description: Given a number as a string and the number of possible changes that are
 * allowed to the string, we must make the string a palindrome and that too the highest
 * valued palindrome possible. For example: number: 3943, and max changes: 1, then the
 * digit 4 can be replaced with 9 to make the string a palindrome. If no such string is
 * possible then print -1.
 */

public class HighestValuePalindrome {

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

    private static String getPalindrome(int n, int k, String S) {
        int D = 0, l = 0, h = n - 1;
        char[] s = S.toCharArray();

        for(; l < h; ++l, --h) {
            if(s[l] != s[h]) { D++; }
        }

        if(k < D) { return "-1"; }
        for(l = 0, h = n - 1; l < h; ++l, --h) {
            if(s[l] != s[h]) {
                if(k == D) {
                    char max = (s[l] > s[h]) ? s[l] : s[h];
                    s[l] = max;
                    s[h] = max;
                } else {
                    s[l] = '9';
                    s[h] = '9';
                    k -= 1;
                }
                D -= 1;
                k -= 1;
            }
        }

        for (l = 0, h = n - 1; l < h && k > 1; ++l, --h) {
            if (s[l] != '9') {
                s[l] = '9';
                s[h] = '9';
                k -= 2;
            }
        }
        if(k == 1 && (n & 1) == 1) {
            s[n / 2] = '9';
        }

        return new String(s);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int k = in.nextInt();
        String S = in.next();

        out.println(getPalindrome(n, k, S));
        out.close();
        in.close();
    }
}
