import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author anish
 * @class : to generate the permutations of a given string in lexicographical order
 */

public class Permutations {

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader() {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(System.in), 37268);
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

        public void close() {
            try {
                reader.close();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void permute(String string, PrintStream out) {
        Set<String> permutations = new TreeSet<>();
        permute(permutations, "", string);
        permutations.forEach(System.out::println);
    }

    private static void permute(Set<String> permutations, String prefix, String remaining) {
        int n = remaining.length();
        if(n == 0) { permutations.add(prefix); }
        else {
            for(int i = 0; i < n; i++) {
                permute(permutations,prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i+1));
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the string for permutations: ");
        String input = in.next();

        permute(input, out);
        out.close();
        in.close();
    }
}
