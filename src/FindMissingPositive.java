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

  private static final int DIVISOR = 63;
  private static final int N = 100000007;

  private static void setBit(long[] check, int index, long offset) {
    long bitSet = 1 << offset;
    check[index] |= bitSet;
  }

  private static boolean isBitSet(long[] check, int index, long offset) {
    long bitSet = 1 << offset;
    return (check[index] & bitSet) > 0;
  }

  private static int getMissingNumber(int[] arr, int n) {
    long[] check = new long[N];

    for(int number: arr) {
      if(number > 0) {
        setBit(check, number / DIVISOR, number % DIVISOR);
      }
    }

    for(int i = 1; i < N; ++i) {
     if(!isBitSet(check, i / DIVISOR, i % DIVISOR)) {
       return i;
     }
    }
    return 0;
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
