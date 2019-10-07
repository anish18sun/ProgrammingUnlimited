import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Merge K sorted lists problem, lists in this problem are
 * simple lists and not linked-lists.
 */

public class MergeLists {

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
  }

  static class PairComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair o1, Pair o2) {
      return Integer.compare(o1.i, o2.j);
    }
  }

  private static List<Integer> getMergedList(List<List<Integer>> kLists, int[] sizes, int k) {
    int size = 0;
    for(int s : sizes) { size += s; }
    List<Integer> mergedList = new ArrayList<>(size);
    PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, new PairComparator());

    for(int i = 0; i < k; ++i) {
      minHeap.add(Pair.getInstance(kLists.get(i).remove(0), i));
    }

    while(minHeap.size() > 0) {
      Pair minElement = minHeap.poll();
      mergedList.add(minElement.i);

      List<Integer> kthList = kLists.get(minElement.j);
      if(kthList.size() > 0) {
        minElement.i = kthList.remove(0);
        minHeap.add(minElement);
      }
    }

    return mergedList;
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    int k = in.nextInt();
    int[] sizes = new int[k];
    List<List<Integer>> kLists = new ArrayList<>(k);
    for(int i = 0; i < k; ++i) { sizes[i] = in.nextInt(); }

    for(int i = 0; i < k; ++i) {
      List<Integer> list = new ArrayList<>(sizes[i]);
      for(int j = 0; j < sizes[i]; ++j) {
        list.add(in.nextInt());
      }
      kLists.add(list);
    }

    out.println(getMergedList(kLists, sizes, k));
    out.close();
    in.close();
  }
}
