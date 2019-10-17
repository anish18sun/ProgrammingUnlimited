import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Longest Valid Parenthesis problem(LeetCode)
 */

public class LongestValidParenthesis {

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

  private static int getParenthesisCount(String string) {
    int leftCount, rightCount, validCount, maxCount = 0;
    char[] chars = string.toCharArray();

    for(int i = 0; i < chars.length - 1; ++i) {
      for (int j = i + 1; j < chars.length; j += 2) {
        leftCount = 0; validCount = 0; rightCount = 0;
        for(int k = i; k <= j; ++k) {
          char c = chars[k];
          if (c == '(') {
            leftCount++;
          }
          else if (c == ')' && leftCount > 0) {
            leftCount--;
            validCount++;
          } else {
            rightCount++;
          }
        }
        if(leftCount == 0 && rightCount == 0) {
          maxCount = Math.max(maxCount, validCount);
        }
      }
    }
    return maxCount * 2;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    String string = in.next();

    out.println(getParenthesisCount(string));
    out.close();
    in.close();
  }
}
