import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the FindSubstring problem (Google)
 * @description: Given a set of strings and a string as key, find the key string in
 * the set of strings in the most efficient way. (Using KMP algorithm O(m + n))
 */

public class FindSubstring {

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

  private static final int N = 100;
  private static final int[] pi = new int[N];

  private static void computeKeyPrefix(String key) {
    int length = key.length();
    char[] chars = key.toCharArray();
    for(int i = 1; i < length; ++i) {
      int j = pi[i-1];
      while(j > 0 && chars[i] != chars[j]) {
        j = pi[j-1];
      }
      if(chars[i] == chars[j]) { ++j; }
      pi[i] = j;
    }
  }

  private static boolean contains(String string, String key, int m) {
    String prefixString = key + "#" + string;
    char[] chars = prefixString.toCharArray();

    for(int i = m + 1; i < chars.length; ++i) {
      int j = pi[i-1];
      while(j > 0 && chars[i] != chars[j]) {
        j = pi[j-1];
      }
      if(chars[i] == chars[j]) { ++j; }
      if(j == m) {
        return true;
      } else {
        pi[i] = j;
      }
    }
    return false;
  }

  private static List<String> getStringsWithKey(String[] strings, String key) {
    List<String> stringList = new ArrayList<>();
    int keyLength = key.length();
    computeKeyPrefix(key);

    for(String string: strings) {
      if(contains(string, key, keyLength)) {
        stringList.add(string);
      }
    }
    return stringList;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    String key = in.next();
    String[] strings = new String[n];
    for(int i = 0; i < n; ++i) { strings[i] = in.next(); }

    out.println(getStringsWithKey(strings, key));
    out.close();
    in.close();
  }
}
