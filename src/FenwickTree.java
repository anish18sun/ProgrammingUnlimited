import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of FenwickTree
 */

public class FenwickTree {

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

    static class FenTree {
        private int[] arr;

        public FenTree(int size) {
            arr = new int[size + 1];
        }

        public int rangeSumQuery(int ind) {
            if(ind <= 0 || ind >= arr.length) { return 0; }

            int sum = 0;
            while(ind > 0) {
                sum += arr[ind];
                ind -= ind & (-ind);
            }
            return sum;
        }

        public int rangeSumQuery(int i, int j) {
            if(i <= 0 || j >= arr.length || i > j) { return 0; }
            return rangeSumQuery(j) - rangeSumQuery(i-1);
        }

        public void update(int ind, int value) {
            if(ind <= 0 || ind >= arr.length) { return; }

            while(ind < arr.length) {
                arr[ind] += value;
                ind += ind & (-ind);
            }
        }

        public void printTree(PrintStream out) {
            out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of elements for the fenwick Tree,");
        out.println("followed by the elements themselves: ");

        int n = in.nextInt();
        FenTree tree = new FenTree(n);
        for(int i = 0; i < n; i++) { tree.update(i + 1, in.nextInt()); }

        out.println("The elements of the Fenwick Tree are: ");
        tree.printTree(out);

        out.println("Please enter the range for sum : ");
        int r = in.nextInt();

        out.println("Range Sum: " + tree.rangeSumQuery(r));
        out.close();
        in.close();
    }
}
