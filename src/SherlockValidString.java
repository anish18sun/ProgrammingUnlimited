import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Sherlock and Valid String problem(Hackerrank)
 * @description: Given a string all characters of the String should occur same number of times
 * for the string to be considered valid, if its possible to remove a character from the string
 * such that all the remaining characters also occur the same number of times even then the
 * string is considered valid.
 */

public class SherlockValidString {

    static class InputReader{
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



    private static int getMinElement(int[] freq) {
        int minElement = Integer.MAX_VALUE;
        for(int f: freq) {
            if(f != 0) { minElement = Math.min(minElement, f); }
        }
        return minElement;
    }

    private static int getMaxElement(int[] freq) {
        int maxElement = 0;
        for(int f: freq) {
            maxElement = Math.max(maxElement, f);
        }
        return maxElement;
    }

    private static boolean isValid(String S) {
        char[] s = S.toCharArray();
        if(s.length == 1) {
            return true;
        }

        int minCount = 0, maxCount = 0;
        int[] freq = new int[26];
        for(char c: s) {
            freq[c - 'a']++;
        }

        int freqCount = 0;
        int minFreq = getMinElement(freq);
        int maxFreq = getMaxElement(freq);
        for(int f: freq) {
            if(f == minFreq) { minCount++; }
            else if(f == maxFreq) { maxCount++; }

            if(f != 0) { freqCount++; }
        }

        if(maxFreq == minFreq) {
            return true;
        } else if(maxCount + minCount != freqCount) {
            return false;
        } else if(minCount == 1 && minFreq == 1) {
            return true;
        } else if(maxCount == 1 && maxFreq == minFreq + 1) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        String S = in.next();
        out.println(isValid(S) ? "YES" : "NO");

        out.close();
        in.close();
    }
}
