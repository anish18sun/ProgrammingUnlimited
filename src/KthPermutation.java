import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @code : program to calculate the Kth lexicographic permutation of string
 * @description : we don't calculate all permutations of string but only Kth lexicograhic permutation of string
 * by first converting the number to the factorial number system and then picking characters in sequence from the
 * ordered/sorted set of characters of the original string. The factorial number system is of the type : bn*n! +
 * b(n-1)*(n-1)! + ... + b0*0! where the 'bn' represents the base and factorials in each term are the multipliers
 */

public class KthPermutation {

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

    /** method to get factoradic representation of length n */
    private static List<Integer> getFactoradic(int k, int n) {
        List<Integer> factoradic = new ArrayList<>();

        int i = 1;
        while(k != 0) {
            factoradic.add(0, k % i);
            k /= i++;
        }
        while(factoradic.size() < n) { factoradic.add(0,0); }
        return factoradic;
    }

    /** the function is implemented for small 'k' */
    private static void solve(int k, String string, PrintStream out) {
        List<Character> charList = new ArrayList<>();
        long factorial = getFactorial(string.length());
        for(char c : string.toCharArray()) { charList.add(c); }

        k = (int) (k % factorial);
        Collections.sort(charList);
        StringBuilder kPermutation = new StringBuilder();
        List<Integer> factoradic = getFactoradic(k, string.length());
        for(int i : factoradic) { kPermutation.append(charList.remove(i)); }

        out.println("kth permutation: " + kPermutation);
    }

    private static long getFactorial(int n) {
        long factorial = 1;
        while(n > 0) { factorial *= n--; }

        return factorial;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the value K and the string: ");

        int k = in.nextInt();
        String string = in.next();

        solve(k-1, string, out);
        out.close();
        in.close();
    }
}
