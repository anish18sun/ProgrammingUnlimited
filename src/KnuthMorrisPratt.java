import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Knuth Morris Pratt algorithm (Strings)
 * @description: You are given a string s of length n. The prefix function for this string is
 * defined as an array π of length n, where π[i] is the length of the longest proper prefix of
 * the substring s[0…i] which is also a suffix of this substring. A proper prefix of a string
 * is a prefix that is not equal to the string itself. By definition, π[0]=0.
 *
 * Mathematically the definition of the prefix function can be written as follows:
 * π[i] = max(k=0…i) {k : s[0…k−1] = s[i−(k−1)…i]}
 */

public class KnuthMorrisPratt {

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

  private static int[] getPrefixes(String string) {
    char[] chars = string.toCharArray();
    int length = string.length();
    int[] pi = new int[length];

    for(int i = 1; i < length; ++i) {
      int j = pi[i-1];
      while(j > 0 && chars[j] != chars[i]) { j = pi[j-1]; }
      if(chars[i] == chars[j]) { ++j; }
      pi[i] = j;
    }
    return pi;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String string = in.next();

    out.println(Arrays.toString(getPrefixes(string)));
    out.close();
    in.close();
  }
}
