import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Number Subsets
 * @description: Given an integer N, the task is to print all the substring of N.
 * Eg: N = 123, will output the set = {1, 12, 123, 2, 23, 3}
 */

public class NumberSubsets {

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

  private static Set<String> getAllSubstrings(String number) {
    Set<String> substringSet = new HashSet<>();
    char[] numChars = number.toCharArray();
    int limit = (1 << number.length()) - 1;
    StringBuilder sb = new StringBuilder();

    for(int i = 1; i <= limit; ++i) {
      for(int j = i, l = numChars.length - 1;
          l >= 0; j = j >> 1, --l) {
        if((j & 1) == 1) {
          sb.append(numChars[l]);
        }
      }
      substringSet.add(sb.toString());
      sb.delete(0, sb.length());
    }
    return substringSet;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String number = in.next();
    out.println("For information, highest number of bits possible: " +
                    Long.toBinaryString(Long.MAX_VALUE));

    out.println(String.join(",", getAllSubstrings(number)));
    out.close();
    in.close();
  }
}
