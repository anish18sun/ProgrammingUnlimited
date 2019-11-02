import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 15 3
 * a b c d e e e e d d c b f g f
 */

public class BurstString {

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

  private static List<String> getShrunkArray(List<String> input, int burstLength) {
    int length = input.size();
    int i = 0, j = 1;

    while(true) {
      for (; i < length - 1; ++i, j = i + 1) {
        String ith = input.get(i);
        String jth = input.get(j);

        while (ith.equalsIgnoreCase(jth)) {
          if (j < length) {
            jth = input.get(j);
            ++j;
          }
        }
        --j;
        if (j - i >= burstLength) {
          break;
        }
      }
      if(j - i >= burstLength) {
        for(int k = i; k < j; ++k) { input.remove(i); }
        length = input.size();
      } else {
        break;
      }
    }
    return input;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    int burstLength = in.nextInt();
    List<String> input = new ArrayList<>();
    for(int i = 0; i < n; ++i) { input.add(in.next()); }

    out.println(getShrunkArray(input, burstLength));
    out.close();
    in.close();
  }
}
