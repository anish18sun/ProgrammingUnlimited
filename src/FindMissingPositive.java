import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Find Missing Number (LeetCode)
 */

public class FindMissingPositive {

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

  private static int getMinimum(int[] arr) {
    int min = Integer.MAX_VALUE;
    for(int number: arr) {
      if(number > 0) {
        min = Math.min(min, number);
      }
    }
    return min;
  }

  private static int getMissingNumber(int[] arr, int n) {
    int min = getMinimum(arr);
    if(min == Integer.MAX_VALUE) { return 1; }
    int index = partition(arr,0, n-1 , min);

    for(int i = index; i < n; ++i) {
      int x = Math.abs(arr[i]);
      if((x-1) < (n - index) && (arr[x-1 + index] > 0)) {
        arr[x-1 + index] = -arr[x-1 + index];
      }
    }

    for(int i = index; i < n; ++i) {
      if(arr[i] > 0) {
        return i - index + 1;
      }
    }
    return n - index + 1;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; ++i) { arr[i] = in.nextInt(); }

    out.println(getMissingNumber(arr, n));
    out.close();
    in.close();
  }
}
