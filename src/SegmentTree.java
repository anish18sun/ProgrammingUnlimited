import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implementation of SegmentTree
 */

public class SegmentTree {

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

    private static class SegTree {
        private int[] tree;

        public SegTree(int[] arr) {
            int n = arr.length;
            int x = (int) Math.ceil(Math.log(n)/Math.log(2));
            int m = 2 * (int) (Math.pow(x, 2)) - 1;
            tree = new int[m];

            constructSTUtil(arr, 0, n-1, 0);
        }

        private int getMid(int ss, int se) { return ss + (se - ss)/2; }

        private int constructSTUtil(int[] arr, int ss, int se, int si) {
            if(ss == se) {
                tree[si] = arr[ss];
                return arr[ss];
            }
            int mid = getMid(ss, se);
            tree[si] = constructSTUtil(arr, ss, mid, 2*si + 1) +
                    constructSTUtil(arr, mid + 1, se, 2*si + 2);
            return tree[si];
        }

        public int getSum(int n, int qs, int qe) {
            if(qs < 0 || qe > n-1 || qs > qe) { return -1; }
            return getSumUtil(0, n-1, qs, qe, 0);
        }

        private int getSumUtil(int ss, int se, int qs, int qe, int si) {
            if(qs > se || qe < ss) { return 0; }
            if(qs <= ss && qe >= se) { return tree[si]; }

            int mid = getMid(ss, se);
            return getSumUtil(ss, mid, qs, qe, 2*si + 1) +
                    getSumUtil(mid + 1, se, qs, qe, 2*si + 2);
        }

        public void updateValue(int[] arr, int n, int i, int newValue) {
            if(i < 0 || i >= n) { return; }
            int diff = newValue - arr[i];
            arr[i] = newValue;

            updateValueUtil(0, n-1, diff, i, 0);
        }

        private void updateValueUtil(int ss, int se, int diff, int i, int si) {
            if(i < ss || i > se) { return; }
            tree[si] += diff;
            if(ss != se) {
                int mid = getMid(ss, se);
                updateValueUtil(ss, mid, diff, i, 2*si + 1);
                updateValueUtil(mid + 1, se, diff, i, 2*si + 2);
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of elements and then the elements :");

        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) { arr[i] = in.nextInt(); }

        out.println("Please enter the ranges for the range sum query: ");
        SegTree tree = new SegTree(arr);
        int i = in.nextInt();
        int j = in.nextInt();

        out.println("The sum in the range: " + tree.getSum(n, i, j));
        out.println("Please enter the index to be updated and the new value: ");

        i = in.nextInt();
        int newVal = in.nextInt();
        tree.updateValue(arr, n, i, newVal);
        out.println("Please enter the ranges for the range sum query: ");

        i = in.nextInt();
        j = in.nextInt();
        out.println("The sum in the range: " + tree.getSum(n, i, j));

        out.close();
        in.close();
    }
}
