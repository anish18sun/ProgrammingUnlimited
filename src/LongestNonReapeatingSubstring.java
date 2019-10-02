import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Longest NonRepeating Substring problem(using KMP algorithm)
 * @description: Given a string, print the longest substring that has non-repeating characters
 */

public class LongestNonReapeatingSubstring {

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

  private static String getLongestSubstring(String string) {
    char[] chars = string.toCharArray();
    int[] array = new int[26];
    Arrays.fill(array,-1);

    int maxLength = 0, maxI = 0, maxJ = 0;
    int i = 0, j = 0;

    while(j < chars.length) {
      if(array[chars[j] - 'a'] > -1) {
        int length = j - i;
        if(maxLength < length) {
          maxLength = length;
          maxI = i;
          maxJ = j;
        }
        i = array[chars[j] - 'a'] + 1;
        array[chars[j] - 'a'] = j;
      } else {
        array[chars[j] - 'a'] = j;
      }
      ++j;
    }

    if(j - i > maxLength) {
      maxI = i; maxJ = j;
    }
    return string.substring(maxI, maxJ);
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String string = in.next();

    out.println(getLongestSubstring(string));
    out.close();
    in.close();
  }
}
