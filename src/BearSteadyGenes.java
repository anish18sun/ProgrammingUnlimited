import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of Bear And Steady Strings problem(Hacker-rank)
 * @description: Given a string which represents gene sequence, a gene string can contain only
 * four characters {A,C,G,T}, so it might look like this : ACCTTGGA. A steady gene string is one
 * that has each of the component count(each of {A,C,G,T}) occur exactly n/4 times where 'n' is the
 * length of the string. We might have unsteady strings, so our objectve is to make steady strings
 * out of unsteady ones by removing the extra characters. However, only substrings can be removed,
 * so we must find a substring that has the minimum length and the removal of which would make the
 * count of each character in the sting equal to (n/4). One more point is that removal is not just
 * the removal of characters it's the replacement of characters, so a substring of characters gets
 * replaced by another appropriate substring of characters.
 */

public class BearSteadyGenes {

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

    private static boolean countSame(Map<Character, Integer> countMap, int n) {
        for(int i : countMap.values()) {
            if(i > n/4) { return false; }
        }
        return true;
    }

    private static int getSubstringCount(char[] genes, int n) {
        Map<Character, Integer> countMap = new HashMap<>();
        countMap.put('A', 0);
        countMap.put('C', 0);
        countMap.put('G', 0);
        countMap.put('T', 0);

        for(char c: genes) {
            countMap.put(c, countMap.get(c) + 1);
        }

        int left = 0, right = 0, min = Integer.MAX_VALUE;
        while(right < n - 1) {
            char rc = genes[right++];
            countMap.put(rc, countMap.get(rc) - 1);
            while(countSame(countMap, n)) {
                min = Math.min(min, right - left);
                char lc = genes[left++];

                countMap.put(lc, countMap.get(lc) + 1);
            }
        }

        return min;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        String gene = in.next();
        out.println(getSubstringCount(gene.toCharArray(), n));

        out.close();
        in.close();
    }
}
