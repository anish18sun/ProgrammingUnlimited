import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Spiral-Level Order traversal of a tree.
 * @description: Given a tree, traverse the tree level-by-level spirally, that is,
 * on level 1 (only root), print left to right. On level two, print right to left.
 * On level 3 print the nodes left to right and so on.
 */

public class SpiralLevelOrder {

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
        private class TreeNode {
            int value;
            TreeNode left, right;
            public TreeNode(int value) { this.value = value; }
        }

        private TreeNode root;

        public void insert(int value) {
            if(root == null) {
                root = new TreeNode(value);
                return;
            }
            TreeNode x = root, y = root;
            while(x != null) {
                y = x;
                if(value < x.value) {
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

        public void delete(int value) {
            TreeNode delNode = search(value);
            if(delNode == null) { return; }

            if(delNode.left == null) {
                transplant(delNode, delNode.right);
            } else {

            }
        }

        private void transplant(TreeNode u, TreeNode v) {
            if(root.value == u.value) { root = v; return; }

            TreeNode y = root, x = root;
            while(x.value != u.value) {
                y = x;
                if(x.value > u.value) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }

            if(y.left.value == u.value) {
                y.left = v;
            } else {
                y.right = v;
            }
        }

        public TreeNode search(int value) {
            if(root == null) { return null; }

            TreeNode node = root;
            while(node != null) {
                if(node.value == value) { return node; }
                if(node.value > value) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return null;
        }

        public TreeNode getRoot() { return root; }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.close();
        in.close();
    }
}
