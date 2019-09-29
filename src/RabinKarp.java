import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Rabin Karp String Matching algorithm
 * @description: Given two strings - a pattern s and a text t, determine if the pattern
 * appears in the text and if it does, enumerate all its occurrences in O(|s|+|t|) time.
 */

public class RabinKarp {

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

  private static final int P = 31;
  private static final int M = 1000000000 + 9;

  private static List<Integer> getOccurances(String pattern, String text) {
    char[] patternChars = pattern.toCharArray();
    char[] textChars = text.toCharArray();
    int patternLength = pattern.length();
    int textLength = text.length();

    int maxLength = Math.max(patternLength, textLength);
    long[] pPower = new long[maxLength];
    long[] h = new long[textLength + 1];
    long hPattern = 0;

    pPower[0] = 1;
    for(int i = 1; i < maxLength; ++i) {
      pPower[i] = (pPower[i-1] * P) % M;
    }

    for(int i = 0; i < textLength; ++i) {
      h[i+1] = (h[i] + (textChars[i] - 'a' + 1) * pPower[i]) % M;
    }
    for(int i = 0; i < patternLength; ++i) {
      hPattern = (hPattern + (patternChars[i] - 'a' + 1) * pPower[i]) % M;
    }

    List<Integer> occurances = new ArrayList<>();
    for(int i = 0; i + patternLength - 1 < textLength; ++i) {
      long currentH = (h[i + patternLength] - h[i] + M) % M;
      if(currentH == hPattern * pPower[i] % M) {
        occurances.add(i);
      }
    }

    return occurances;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String text = in.next();
    String pattern = in.next();

    out.println(getOccurances(pattern, text));
    out.close();
    in.close();
  }
}
