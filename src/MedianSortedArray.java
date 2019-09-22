import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Median of two sorted arrays - leetcode
 */

public class MedianSortedArray {

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

  private static int getIthElement(int[] a, int[] b, int i) {
    int aIndex = 0, bIndex = 0, element = 0;

    while(i > 0 && aIndex < a.length && bIndex < b.length) {
      if(a[aIndex] < b[bIndex]) {
        element = a[aIndex++];
      } else if(a[aIndex] > b[bIndex]) {
        element = b[bIndex++];
      } else {
        element = a[aIndex];
        aIndex++;
        bIndex++;
        i--;
      }
      i--;
    }

    while(i > 0 && aIndex < a.length) {
      element = a[aIndex++];
      i--;
    }
    while(i > 0 && bIndex < b.length) {
      element = b[bIndex++];
      i--;
    }

    return element;
  }

  private static float getMedian(int[] a, int[] b) {
    int size = a.length + b.length;

    if((size & 1) == 1) {
      return getIthElement(a, b, (size + 1) / 2);
    } else {
      return (getIthElement(a, b,size / 2) + getIthElement(a, b, (size / 2) + 1)) / 2.0f;
    }
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    int m = in.nextInt();
    int[] a = new int[n];
    int[] b = new int[m];
    for(int i = 0; i < n; ++i) { a[i] = in.nextInt(); }
    for(int i = 0; i < m; ++i) { b[i] = in.nextInt(); }

    out.println(getMedian(a, b));
    out.close();
    in.close();
  }
}
