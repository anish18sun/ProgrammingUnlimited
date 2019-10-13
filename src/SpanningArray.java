import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Spanning Array problem
 * @description: Given an array of numbers, find the span of each element. The span of an
 * element is defined as the number of consecutive elements that are less than or equal to
 * the current element to the left of it.
 */

public class SpanningArray {

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

  private static int[] getSpanningArray(int[] arr, int n) {
    int[] span = new int[n];

    int prevValue, index;
    Arrays.fill(span, 1);

    for(int i = 1; i < n; ++i) {
      prevValue = arr[i - 1];
      index = i - 1;
      while(prevValue < arr[i]) {
        span[i] += span[index];
        index -= span[index];

        if(index < 0) { break; }
        prevValue = arr[index];
      }
    }

    return span;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    int[] arr = new int[n];
    for(int i = 0 ; i < n; ++i) { arr[i] = in.nextInt(); }

    out.println(Arrays.toString(getSpanningArray(arr, n)));
    out.close();
    in.close();
  }

}
