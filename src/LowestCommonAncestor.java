import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Lowest Common Ancestor problem
 * @description: Given a binary search tree and two nodes, find the lowest common
 * ancestor node of the two nodes.
 */

public class LowestCommonAncestor {

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

    static class BST {
      class TreeNode {
        int value;
        TreeNode left, right;
        TreeNode(int value) { this.value = value; }
      }

      private TreeNode root;
      BST(int value) { root = new TreeNode(value); }

      void insert(int value) {
        TreeNode newNode = new TreeNode(value);
        TreeNode y = root, x = root;
        while(x != null) {
          y = x;
          if(x.value < value) {
            x = x.right;
          } else {
            x = x.left;
          }
        }

        if(y.value < value) {
          y.right = newNode;
        } else {
          y.left = newNode;
        }
      }

      boolean search(int value) {
        if(root != null) {
          TreeNode traverse = root;
          while(traverse != null) {
            if(traverse.value == value) {
              return true;
            } else if(traverse.value < value) {
              traverse = traverse.right;
            } else {
              traverse = traverse.left;
            }
          }
        }
        return false;
      }

      TreeNode getLowestCommonAncestor(int node1, int node2) {
        if(root != null && search(node1) && search(node2)) {
          TreeNode traverse = root;
          while(isNotLCA(traverse, node1, node2)) {
            if(traverse.value < node1) {
              traverse = traverse.right;
            } else {
              traverse = traverse.left;
            }
          }
          return traverse;
        }
        return null;
      }

      private boolean isNotLCA(TreeNode traverse, int node1, int node2) {
        if(traverse.value == node1 || traverse.value == node2) { return false; }
        if(traverse.value < node1 && traverse.value > node2) { return false; }
        if(traverse.value > node1 && traverse.value < node2) { return false; }

        return true;
      }
    }

    public static BST getTree() {
      BST bst = new BST(7);
      bst.insert(5);
      bst.insert(9);
      bst.insert(8);
      bst.insert(10);

      return bst;
    }

    public static void main(String[] args) {
      PrintStream out = System.out;
      InputReader in = new InputReader();

      int node1 = in.nextInt();
      int node2 = in.nextInt();
      BST tree = getTree();

      BST.TreeNode lowestCommonAncestor = tree.getLowestCommonAncestor(node1, node2);
      int value = (lowestCommonAncestor != null) ? lowestCommonAncestor.value : -1;
      out.println(value);
      out.close();
      in.close();
    }
}
