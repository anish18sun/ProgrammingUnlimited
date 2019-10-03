import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the TargetSum problem(Dynamic Programming)
 * @description: Given a target sum and an array of numbers, we must find the total number
 * of ways in which the sum can be achieved by adding or subtracting the numbers in array.
 */

public class TargetSum {

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

  private static int getWays(int T, int n, int[] numbers) {
    int sum = 0;
    for(int num: numbers) { sum += num; }
    if (sum == 0) { return 0; }

    int[][] opt = new int[n + 1][2*sum + 1];

    // Initialize i=0, T=0
    opt[0][sum] = 1;
    // Iterate over previous row and update the
    // current row
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < 2 * sum + 1; j++) {
        int prev = opt[i-1][j];
        if (prev != 0) {
          opt[i][j - numbers[i-1]] += prev;
          opt[i][j + numbers[i-1]] += prev;
        }
      }
    }

    for(int i = 0; i <= n; ++i) {
      System.out.println(Arrays.toString(opt[i]));
    }

    return opt[n][sum + T];
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    int T = in.nextInt();
    int[] numbers = new int[n];
    for(int i = 0; i < n; ++i) { numbers[i] = in.nextInt(); }

    out.println(getWays(T, n, numbers));
    out.close();
    in.close();
  }
}
