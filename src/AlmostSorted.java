import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the AlmostSorted problem(Hackerrank)
 * @description: Given an array that is almost sorted, check whether one of the following two
 * operations can sort the array: (1) Swap two elements of the array OR (2) Reverse a subset
 * of the array. Option (1) is given preference over (2), if both options fail the array
 * cannot be sorted. Used Rank Selection to solve the problem, if in the array the out
 * of order index is 'i', then perform RankSelection on the array with index 'i' and
 * try to swap the element at current index 'i' and that returned by RankSelection,
 * if swapping does not help, try reversing the elements in the same range both
 * elements inclusive. Both options may fail.
 */

public class AlmostSorted {

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

    private static int getMedian(int[] arr, int l, int r) {
        Arrays.sort(arr, l, r);
        return arr[(l+r)/2];
    }

    private static int partition(int[] arr, int l, int r, int x) {
        int i = l-1, j = l;
        for(; j < r+1; j++) {
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
        for(j = 0; j < n/5; j++) { median[j] = getMedian(arr, l+(j*5),l+(j*5)+5); }
        if(j*5 < n) { median[j] = getMedian(arr, l+(j*5), l+(j*5) + n%5); j++; }

        int x = (j == 1) ? median[j-1] : ithSmallest(median,0,j-1,j/2);
        int q = partition(arr, l, r, x);
        int k = q - l + 1;

        if(i == k) { return x; }
        else if(i < k) { return ithSmallest(arr, l, q-1, i); }
        else { return ithSmallest(arr, q+1, r, i-k); }
    }

    private static String sortOperation(int[] arr) {
        int length = arr.length;

        int i, j, k;
        for(i = 0; i < length - 1; ++i) { if(arr[i] > arr[i+1]) { break; }}
        if(i == length - 1) { return "yes"; }

        int ithELement = ithSmallest(Arrays.copyOf(arr, length),0,length - 1,i + 1);
        for(j = i; j < length; ++j) { if(ithELement == arr[j]) { break; }}
        if(j == length) { return "no"; }

        /* swap and check */
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        for(k = i; k < length - 1; ++k) {
            if(arr[k] > arr[k+1]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                break;
            }
        }
        if(k == length - 1) { return "yes\nswap " + (i + 1) + " " + (j + 1); }

        /* reverse and check */
        for(k = j; k > i; --k) {
            if(arr[k] > arr[k-1]) { break; }
        }
        if(k == i) {
            if(arr[k] > arr[j+1]) { return "no"; }
            for(k = j+1; k < length - 1; ++k) {
                if(arr[k] > arr[k+1]) { break; }
            }
        }
        if(k == length - 1) { return "yes\nreverse " + (i + 1) + " " + (j + 1); }

        return "no";
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of elements in the array,");
        out.println("followed by the elements themselves: ");

        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) { arr[i] = in.nextInt(); }

        out.println(sortOperation(arr));
        out.close();
        in.close();
    }
}
