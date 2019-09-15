import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implementation of the Longest Common Prefix problem
 * @description: Given an array of Strings, find the longest common prefix in all
 * of the strings in the array.
 */

public class LongestCommonPrefix {

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

  private static String getLongestCommonPrefix(String[] strings, int n) {
    if(n == 1) { return strings[0]; }
    int length = strings[0].length();

    int i;
    for(i = 1; i < n; ++i) {
      length = Math.min(length, strings[i].length());
    }

    boolean foundBreak = false;
    for(i = 0; i < length; ++i) {
      char c = strings[0].charAt(i);
      for(int j = 1; j < n; ++j) {
        if(c != strings[j].charAt(i)) {
          foundBreak = true;
          break;
        }
      }
      if(foundBreak) {
        break;
      }
    }

    return strings[0].substring(0, i);
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    String[] strings = new String[n];
    for(int i = 0; i < n; ++i) { strings[i] = in.next(); }

    out.println(getLongestCommonPrefix(strings, n));
    out.close();
    in.close();
  }
}
