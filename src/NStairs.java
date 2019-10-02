import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the N-Stairs problem (Dynamic Programming)
 * @description: Given a number 'n' and another array 'steps', we must find the
 * total number of ways in which a person can climb stairs using only those steps.
 */

public class NStairs {

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

  private static int getTotalWays(int[] steps, int m, int n) {
    int[] opt = new int[n + 1];
    opt[0] = 1;

    for(int i = 1; i <= n; ++i) {
      for(int j = 0; j < m; ++j) {
        if(i - steps[j] >= 0) {
          opt[i] += opt[i - steps[j]];
        }
      }
    }
    return opt[n];
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    int m = in.nextInt();
    int[] steps = new int[m];
    for(int i = 0; i < m; ++i) { steps[i] = in.nextInt(); }

    out.println(getTotalWays(steps, m, n));
    out.close();
    in.close();
  }

}
