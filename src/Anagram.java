import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Anagrams of a String.
 */

public class Anagram {

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

  private static Set<String> getAnagrams(String string) {
    int length = string.length();
    if(length == 0) {
      return null;
    } else if(length == 1) {
      Set<String> anagramSet = new HashSet<>();
      anagramSet.add(string);
      return anagramSet;
    }

    Set<String> anagrams = new HashSet<>();

    for(int i = 0; i < length; ++i) {
      char c = string.charAt(i);
      Set<String> remainingSet = getAnagrams(string.substring(0, i)
                                                 + string.substring(i + 1));
      if(remainingSet != null) {
        for (String anagram : remainingSet) {
          anagrams.add(c + anagram);
        }
      }
    }
    return anagrams;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String string = in.next();

    out.println(String.join(",", getAnagrams(string)));
    out.close();
    in.close();
  }
}
