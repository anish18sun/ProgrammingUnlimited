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

  private static int getParenthesisCountDP(String string) {
    char[] chars = string.toCharArray();
    int[] opt = new int[chars.length];
    int maxCount = 0;

    for(int i = 1; i < chars.length; ++i) {
      if(chars[i] == ')') {
        if(chars[i - 1] == '(') {
          opt[i] = ((i >= 2) ? opt[i - 2] : 0) + 2;
        } else if(i - opt[i - 1] > 0 && chars[i - opt[i - 1] - 1] == '(') {
          opt[i] = opt[i - 1] + ((i - opt[i-1] >= 2) ? opt[i - opt[i - 1] - 2] : 0) + 2;
        }
      }
      maxCount = Math.max(maxCount, opt[i]);
    }
    return maxCount;
  }

  private static int getParenthesisCount(String string) {
    int leftCount = 0, rightCount = 0, maxCount = 0;
    char[] chars = string.toCharArray();

    for(int i = 0; i < chars.length; ++i) {
      if(chars[i] == '(') {
        leftCount++;
      } else {
        rightCount++;
      }
      if(leftCount == rightCount) {
        maxCount = Math.max(maxCount, 2 * rightCount);
      } else if(rightCount > leftCount) {
        leftCount = rightCount = 0;
      }
    }

    leftCount = rightCount = 0;
    for(int i = chars.length - 1; i >= 0; --i) {
      if(chars[i] == '(') {
        leftCount++;
      } else {
        rightCount++;
      }
      if(leftCount == rightCount) {
        maxCount = Math.max(maxCount, 2 * leftCount);
      } else if(leftCount > rightCount) {
        leftCount = rightCount = 0;
      }
    }
    return maxCount;
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
