import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of 'Substring with Concatenation of All Words' problem - LeetCode
 */

public class SubstringConcatenation {

  static class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader() {
      tokenizer = null;
      reader = new BufferedReader(new InputStreamReader(System.in), 32768);
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        }
        catch (IOException e) {
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

  private static List<Integer> getSubstringIndexes(String text, String[] words, int n) {
    List<Integer> indexes = new ArrayList<>();


    return indexes;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    String text = in.next();
    String[] words = new String[n];
    for(int i = 0; i < n; ++i) { words[i] = in.next(); }

    out.println(getSubstringIndexes(text, words, n));
    out.close();
    in.close();
  }
}