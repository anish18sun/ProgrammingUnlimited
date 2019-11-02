import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class OneTwoThree {

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

  private static String findQualifiedNumbers(int[] numberArray) {
    Set<String> numberSet = new TreeSet<>();

    for(int number: numberArray) {
      if(isValid(number)) {
        numberSet.add(String.valueOf(number));
      }
    }

    if(numberSet.isEmpty()) { return "-1"; }
    return String.join(",", numberSet);
  }

  private static boolean isValid(int number) {
    boolean one = false, two = false, three = false;
    for(; number > 0; number /= 10) {
      int digit = number % 10;
      if(digit == 1) {
        one = true;
      } else if(digit == 2) {
        two = true;
      } else if(digit == 3) {
        three = true;
      }
    }
    return one && two && three;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; ++i) { arr[i] = in.nextInt(); }

    out.println(findQualifiedNumbers(arr));
    out.close();
    in.close();

  }

}
