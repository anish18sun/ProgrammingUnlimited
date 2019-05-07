import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class : to solve the rules based permutation problem
 * @description : given a set of rules that map some alphabets to other alphabets, create a string with that mapping
 *  intact and the remaining characters sorted in lexicographical order. Example: b->x, c->y, d->z; so in the result
 *  string the chars{x,y,z} would appear in place of {b,c,d} and the remaining characters would take the rest of positions
 *  in the Kth sorted order, ie, of all lexicographic permutations we chose the Kth permutation in order.
 *
 *  this program generates all the permutations of the string: may not be a good idea; if the string is of 23 chars then
 *  the number of possible permutations is 23! - a very large number. So either the kth permutation must be calculated
 *  manually or the permutation generating method must generate in lexicographic order but in the latter case the worst
 *  case still requires calculation of 23! possible strings - an overwhelming number. So the Kth order permutation must
 *  be generated more efficiently. Implemented in Kth lexicographic permutation of string.
 */

public class PermutationRules {

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

        public char nextChar() { return next().charAt(0); }

        public int nextInt() { return Integer.parseInt(next()); }

        public void close() {
            try {
                reader.close();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void solve(Map<Character, Character> charMap, int k, PrintStream out) {
        char[] string = new char[26];
        Arrays.fill(string, '\0');

        StringBuilder builder = new StringBuilder();
        charMap.forEach((c1, c2) -> string[c1 - 'a'] = c2);
        Collection<Character> mappedValues = charMap.values();
        for(int i = 0; i < 26; ++i) {
            char c = (char) ('a' + i);
            if(! mappedValues.contains(c))
            { builder.append((char) ('a' + i)); }
        }

        List<String> permutationList = permute(builder.toString());
        Collections.sort(permutationList);
        k = permutationList.size() % k;
        int j = 0;

        for(char c : permutationList.get(k).toCharArray()) {
            while(string[j] == '\0') { j++; }
            string[j] = c;
        }

        out.println(string);
    }

    private static List<String> permute(String string) {
        List<String> permutationList = new ArrayList<>();
        permute(permutationList, "", string);
        return permutationList;
    }

    private static void permute(List<String> permutationList, String prefix, String remaining) {
        int n = remaining.length();
        if(n == 0) { permutationList.add(prefix); }
        else {
            for(int i = 0; i < n; ++i) {
                permute(permutationList, prefix + remaining.charAt(i),
                        remaining.substring(0,i) + remaining.substring(i+1));
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        Map<Character, Character> charMap = new HashMap<>();
        out.println("Please enter the number of mappings, the value K, followed by the mappings themselves, eg:- ");
        out.println("3");
        out.println("b x");
        out.println("c y");
        out.println("d z");

        int n = in.nextInt();
        int k = in.nextInt();
        while(n-- > 0) { charMap.put(in.nextChar(), in.nextChar()); }

        solve(charMap, k, out);
        out.close();
        in.close();
    }
}
