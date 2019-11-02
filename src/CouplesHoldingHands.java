import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Couples Holding Hands problem (LeetCode)
 */

public class CouplesHoldingHands {

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

  private static void connect(int[][] parent, int i, int j) {
    if(parent[i][0] == -1) {
      parent[i][0] = j;
    } else {
      parent[i][1] = j;
    }

    if(parent[j][0] == -1) {
      parent[j][0] = i;
    } else {
      parent[j][1] = i;
    }
  }

  private static void traverse(boolean[] check, int[][] parent, int i) {
    check[i] = true;
    if(!check[parent[i][0]]) { traverse(check, parent, parent[i][0]); }
    if(!check[parent[i][1]]) { traverse(check, parent, parent[i][1]); }
  }

  private static int getMinSwaps(int[] arr, int n) {
    boolean[] check = new boolean[n/2];
    int[][] parent = new int[n/2][2];
    int count = 0;

    for(int[] p: parent) { Arrays.fill(p, -1); }

    for(int i = 0; i < n - 1; i += 2) {
      connect(parent, arr[i], arr[i+1]);
    }

    for(int i = 0; i < n/2; ++i) {
      if(!check[i]) {
        traverse(check, parent, i);
        count++;
      }
    }

    return n / 2 - count;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; ++i) { arr[i] = in.nextInt() / 2; }

    out.println(getMinSwaps(arr, n));
    out.close();
    in.close();
  }
}
