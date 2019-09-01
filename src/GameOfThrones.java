import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Game of Thrones problem(Hackerrank)
 */

public class GameOfThrones {

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

  private static final int CHAR_SIZE = 26;

  private static boolean isPalindrome(String string) {
    int[] check = new int[CHAR_SIZE];
    int oddCount = 0;

    for(char c: string.toCharArray()) {
      check[c - 'a']++;
    }

    for(int count: check) {
      if(count % 2 != 0) { oddCount++; }
    }

    return (oddCount <= 1);
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String string = in.next();

    out.println(isPalindrome(string) ? "YES" : "NO");
    out.close();
    in.close();
  }
}
