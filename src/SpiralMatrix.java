import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the spiral matrix problem: given a matrix, print it in spiral form
 */

public class SpiralMatrix {

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

  private static void printSpiral(int[][] matrix, int n, int m, PrintStream out) {
    int top = 0, left = 0, right = m - 1, bottom = n - 1;

    while(true) {
      if(left > right) { break; }

      for(int i = left; i <= right; ++i) {
        out.print(matrix[top][i] + " ");
      }
      top++;

      if(top > bottom) { break; }

      for(int i = top; i <= bottom; ++i) {
        out.print(matrix[i][right] + " ");
      }
      right--;

      if(left > right) { break; }

      for(int i = right; i >= left; --i) {
        out.print(matrix[bottom][i] + " ");
      }
      bottom--;

      if(top > bottom) { break; }

      for(int i = bottom; i >= top; --i) {
        out.print(matrix[i][left] + " ");
      }
      left++;
    }
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    int m = in.nextInt();
    int[][] matrix = new int[n][m];
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        matrix[i][j] = in.nextInt();
      }
    }

    printSpiral(matrix, n, m, out);
    out.close();
    in.close();
  }
}
