import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Morgan And A String problem(Hackerrank)
 */

public class MorganString {

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

  private static String getLexicographicString(String A, String B) {
    StringBuilder sb = new StringBuilder();
    char[] aChar = A.toCharArray();
    char[] bChar = B.toCharArray();
    int aIndex = 0, bIndex = 0;

    while(aIndex < aChar.length && bIndex < bChar.length) {
      if(aChar[aIndex] < bChar[bIndex]) {
        sb.append(aChar[aIndex++]);
      } else if(bChar[bIndex] < aChar[aIndex]) {
        sb.append(bChar[bIndex++]);
      } else {
        int aTemp = aIndex;
        int bTemp = bIndex;
        while(aTemp < aChar.length && bTemp < bChar.length &&
            aChar[aTemp] == bChar[bTemp]) { aTemp++; bTemp++; }

        if(aTemp == aChar.length) { aTemp--; }
        if(bTemp == bChar.length) { bTemp--; }

        if(aChar[aTemp] < bChar[bTemp]) {
          sb.append(aChar[aIndex++]);
        } else {
          sb.append(bChar[bIndex++]);
        }
      }
    }

    while(aIndex < aChar.length) { sb.append(aChar[aIndex++]); }
    while(bIndex < bChar.length) { sb.append(bChar[bIndex++]); }
    return sb.toString();
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    while(n-- > 0) {
      String A = in.next();
      String B = in.next();
      out.println(getLexicographicString(A, B));
    }
    out.close();
    in.close();
  }
}
