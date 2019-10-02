import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class:  Implementation of the Rotten Oranges problem
 */

public class RottenOranges {

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

  static class Pair {
    int i, j;
    private Pair(int i, int j) { this.i = i; this.j = j; }
    public static Pair getInstance(int i, int j) { return new Pair(i, j); }

    private static final Pair delimiter = new Pair(-1, -1);
    public static Pair getDelimiter() { return delimiter; }

    @Override
    public boolean equals(Object o) {
      if(o instanceof Pair) {
        Pair other = (Pair) o;
        return i == other.i && j == other.j;
      }
      return false;
    }
  }

  private static void enqueueStartingPoints(int[][] matrix, int n,
                                            int m, ArrayDeque<Pair> queue) {
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        if(matrix[i][j] == 2) {
          queue.addLast(Pair.getInstance(i, j));
        }
      }
    }
    queue.addLast(Pair.getDelimiter());
  }

  private static boolean areAllOrangesRotten(int[][] matrix, int n, int m) {
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        if(matrix[i][j] == 1) { return false; }
      }
    }
    return true;
  }

  private static boolean isValidIndex(int i, int j,
                                      int[][] matrix, int n, int m) {
    if(i < 0 || j < 0 || i >= n || j >= m) { return false; }
    if(matrix[i][j] != 1) { return false; }
    return true;
  }

  private static int getMinTime(int[][] matrix, int n, int m) {
    ArrayDeque<Pair> queue = new ArrayDeque<>();
    enqueueStartingPoints(matrix, n, m, queue);
    int time = 0;

    while (queue.size() > 1) {
      Pair p = queue.removeFirst();

      if (p.equals(Pair.getDelimiter())) {
        queue.addLast(Pair.getDelimiter());
        time++;
      }
      else {
        for (int i = -1; i <= 1; i += 2) {
          if (isValidIndex(p.i + i, p.j, matrix, n, m)) {
            queue.addLast(Pair.getInstance(p.i + i, p.j));
            matrix[p.i + i][p.j] = 2;
          }
        }
        for (int j = -1; j <= 1; j += 2) {
          if (isValidIndex(p.i, p.j + j, matrix, n, m)) {
            queue.addLast(Pair.getInstance(p.i, p.j + j));
            matrix[p.i][p.j + j] = 2;
          }
        }
      }
    }

    return areAllOrangesRotten(matrix, n, m) ? time : -1;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int n = in.nextInt();
    int m = in.nextInt();
    int[][] matrix = new int[n][m];
    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < m; ++j) {
        matrix[i][j] = in.nextInt();
      }
    }

    out.println(getMinTime(matrix, n, m));
    out.close();
    in.close();
  }
}
