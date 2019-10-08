import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Inverting a binary tree(Mirror Tree)
 */

public class InvertTree {

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

  static class Tree {
    class TreeNode {
      int value;
      TreeNode left, right;
      TreeNode(int value) { this.value = value; }
    }

    private TreeNode root;
    public Tree(int value) { root = new TreeNode(value); }

    public void insert(int value) {
      if(root == null) { root = new TreeNode(value); return; }

      TreeNode y = root, x = root;
      while(x != null) {
        y = x;
        if(x.value > value) {
          x = x.left;
        } else {
          x = x.right;
        }
      }

      if(y.value > value) {
        y.left = new TreeNode(value);
      } else {
        y.right = new TreeNode(value);
      }
    }

    public void invert() {
      if(root == null) { return; }
      ArrayDeque<TreeNode> queue = new ArrayDeque<>();

      queue.add(root);
      while(!queue.isEmpty()) {
        TreeNode node = queue.removeFirst();
        TreeNode right = node.right;
        TreeNode left = node.left;

        if(left != null && right != null) {
          int temp = left.value;
          left.value = right.value;
          right.value = temp;

          queue.addLast(left);
          queue.addLast(right);

        } else if(right != null) {
          node.left = right;
          node.right = null;

          queue.addLast(right);

        } else if(left != null) {
          node.right = left;
          node.left = null;

          queue.addLast(left);
        }
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader();
    PrintStream out = System.out;

    // add tree and invert

    out.close();
    in.close();
  }
}
