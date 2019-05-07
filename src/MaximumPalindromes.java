import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the MaximumPalindromes problem(Hackerrank- Strings)
 * @description: Given a string S and a range [l, r], consider a substring S[l, r], both
 * ends inclusive then we must find the number of maximum length palindromes that can be
 * formed from the characters in that substring even if the characters can be reordered.
 */

public class MaximumPalindromes {

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

    private static final int N = 100001;
    private static final int MOD = 1000000007;
    private static final long[] fact = new long[N];
    private static final long[] rfact = new long[N];

    private static long pow(long x, long y) {
        if(y == 0) { return 1; }
        if((y & 1) == 1) {
            return pow(x, y - 1) * x % MOD;
        } else {
            long tmp = pow(x, y / 2);
            return tmp * tmp % MOD;
        }
    }

    private static void computeFactorial() {
        fact[0] = 1; rfact[0] = 1;
        for(int i = 1; i < N; ++i) {
            fact[i] = fact[i - 1] * i % MOD;
            rfact[i] = pow(fact[i], MOD - 2);
        }
    }

    private static void computeCounts(int[][] cnt, String S) {
        char[] s = S.toCharArray();
        for(int i = 0; i < s.length; ++i) {
            cnt[s[i] - 'a'][i + 1]++;
        }
        for(int i = 0; i < 26; ++i) {
            for(int j = 1; j < s.length + 1; ++j) {
                cnt[i][j] += cnt[i][j - 1];
            }
        }
    }

    private static long getMaxPalindromes(int l, int r, int[][] cnt) {
        long denominator = 1;
        int oddCount = 0, numerator = 0;
        for(int i = 0; i < 26; ++i) {
            int freq = cnt[i][r] - cnt[i][l - 1];

            numerator += freq / 2;
            if((freq & 1) == 1) { oddCount++; }
            denominator = denominator * rfact[freq / 2] % MOD;
        }

        long result = denominator * Math.max(1, oddCount) % MOD;
        result *= fact[numerator] % MOD;
        return result;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        computeFactorial();
        String S = in.next();
        int[][] cnt = new int[26][N];

        computeCounts(cnt, S);
        int t = in.nextInt();
        while(t-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            out.println(getMaxPalindromes(l, r, cnt));
        }

        out.close();
        in.close();
    }
}
