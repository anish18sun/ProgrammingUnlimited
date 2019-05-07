import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the Rank Selection problem, choosing the ith smallest element in 0(n) worst case
 */

public class RankSelection {

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

    private static int getMedian(int[] arr, int l, int r) {
        Arrays.sort(arr, l, r);
        return arr[(l+r)/2];
    }

    private static int partition(int[] arr, int l, int r, int x) {
        int i = l-1, j = l;
        for(; j < r+1; ++j) {
            if(arr[j] < x) {
                ++i;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        i++;
        for(j = i; j < r+1; ++j) { if(arr[j] == x) { break; }}
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;

        return i;
    }

    private static int ithSmallest(int[] arr, int l, int r, int i) {
        if(i < 0 || i > (r-l+1)) { return Integer.MAX_VALUE; }

        int j, n = r-l+1;
        int[] median = new int[(int) Math.ceil(n/5.0)];
        for(j = 0; j < n/5; ++j) { median[j] = getMedian(arr, l+(j*5),l+(j*5)+5); }
        if(j*5 < n) { median[j] = getMedian(arr, l+(j*5), l+(j*5) + n%5); j++; }

        int x = (j == 1) ? median[j-1] : ithSmallest(median, 0, j-1, j/2);
        int q = partition(arr, l, r, x);
        int k = q - l + 1;

        if(i == k) { return x; }
        else if(i < k) { return ithSmallest(arr, l, q-1, i); }
        else { return ithSmallest(arr, q+1, r, i-k); }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the size of array, the ith index and then the elements of the array:- ");

        int n = in.nextInt();
        int i = in.nextInt();
        int[] arr = new int[n];
        for(int j = 0; j < n; ++j) { arr[j] = in.nextInt(); }

        int element = ithSmallest(arr, 0, n-1, i);
        out.println("ith Smallest: " + element);
        out.close();
        in.close();
    }
}
