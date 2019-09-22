import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Lena Sort problem (Hackerrank)
 */

public class LenaSort {

  static class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader() {
      tokenizer = null;
      reader = new BufferedReader(new InputStreamReader(System.in),32768);
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

  private static int[] getArray(int len, int c) {

    return new int[0];
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();


    int q = in.nextInt();
    while(q-- > 0) {
      int len = in.nextInt();
      int c = in.nextInt();
      out.println(Arrays.toString(getArray(len, c)));
    }
    out.close();
    in.close();
  }
}
