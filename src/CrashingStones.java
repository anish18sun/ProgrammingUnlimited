import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CrashingStones {

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
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static int lastStoneWeight(List<Integer> weights) {
    int size = weights.size();
    Collections.sort(weights);
    int index = size - 1;

    for (; index > 0; ) {
      int i = index;
      int j = index - 1;
      int iWeight = weights.get(i);
      int jWeight = weights.get(j);

      if (iWeight == jWeight) {
        --index;
      }
      else {
        int leftStone = Math.abs(iWeight - jWeight);
        while(i >= 0 && weights.get(i) > leftStone) { --i; }
        if(i < 0) {
          weights.add(0, leftStone);
        } else {
          weights.add(i+1, leftStone);
        }
      }
    }

    if (index < 0) {
      return 0;
    }
    else {
      return weights.get(0);
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    int n = in.nextInt();
    List<Integer> weights = new ArrayList<>(n);
    for(int i = 0; i < n; ++i) { weights.add(in.nextInt()); }

    out.println(lastStoneWeight(weights));
    out.close();
    in.close();
  }
}
