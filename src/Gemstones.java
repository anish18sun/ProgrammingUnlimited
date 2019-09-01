import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Gemstones problem(Hackerrank - Strings)
 */

public class Gemstones {

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

  private static final int CHAR_SIZE = 26;

  private static int getMineralCount(String[] rocks, int n) {
    boolean[] checkAux = new boolean[CHAR_SIZE];
    int[] check = new int[CHAR_SIZE];

    for(String rock: rocks) {
      Arrays.fill(checkAux,false);
      for(char c: rock.toCharArray()) {
        checkAux[c - 'a'] = true;
      }
      for(int i = 0; i < CHAR_SIZE; ++i) {
        if(checkAux[i]) { check[i] += 1; }
      }
    }

    int count = 0;
    for(int checkCount: check) {
      if(checkCount == n) { count++; }
    }
    return count;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    String[] rocks = new String[n];
    for(int i = 0; i < n; ++i) { rocks[i] = in.next(); }

    out.println(getMineralCount(rocks, n));
    out.close();
    in.close();
  }
}
