import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Distinct Substrings in a String(usng String Hashing)
 */

public class DistinctSubstrings {

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

  private static final int P = 31;
  private static final int M = 1000000000 + 9;

  private static int getDistinctSubstringCount(String string) {
    int length = string.length();
    char[] c = string.toCharArray();
    long[] h = new long[length + 1];
    long[] pPower = new long[length];

    pPower[0] = 1;
    for(int i = 1; i < length; ++i) {
      pPower[i] = (pPower[i-1] * P) % M;
    }

    for(int i = 0; i < length; ++i) {
      h[i+1] = (h[i] + (c[i] - 'a' + 1) * pPower[i]) % M;
    }

    int count = 0;
    Set<Long> hSet = new HashSet<>();
    for(int l = 1; l <= length; ++l) {
      for(int i = 0; i <= length - l; ++i) {
        long currentH = (h[i+l] - h[i] + M) % M;
        currentH = (currentH * pPower[length - i - 1]) % M;
        hSet.add(currentH);
      }
      count += hSet.size();
      hSet.clear();
    }

    return count;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String string = in.next();

    out.println(getDistinctSubstringCount(string));
    out.close();
    in.close();
  }
}
