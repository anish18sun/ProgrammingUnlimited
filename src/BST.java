import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of Binary Search Tree
 */

public class BST {

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
            public TreeNode(int value) {
                this.value = value;
            }
        }

        private TreeNode root;

        /** transplant method replaces the node 'u' with node 'v' */
        public void transplant(TreeNode u, TreeNode v) {
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

        public void delete(int value) {
            TreeNode delNode = search(value);
            if(delNode == null) { return; }

            if(delNode.left == null) {
                transplant(delNode, delNode.right);
            } else if(delNode.right == null) {
                transplant(delNode, delNode.left);
            } else {
                TreeNode y = delNode.right;
                while(y.left != null) { y = y.left; }

                if(y != delNode.right) {
                    transplant(y, y.right);
                    y.right = delNode.right;
                }
                transplant(delNode, y);
                y.left = delNode.left;
            }
        }

        public TreeNode search(int value) {
            if(root == null) { return null; }

            TreeNode temp = root;
            while(temp != null) {
                if(temp.value == value) { return temp; }
                else if(temp.value > value) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            return null;
        }

        public void printTree(PrintStream out) { inOrder(root, out); }

        public void inOrder(TreeNode node, PrintStream out) {
            if(node == null) { return; }

            inOrder(node.left, out);
            out.print(node.value + " ");
            inOrder(node.right, out);
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please number of elements & then the elements: ");

        int n = in.nextInt();
        Tree bst = new Tree();
        for(int i = 0; i < n; i++) { bst.insert(in.nextInt()); }

        out.println("The elements of the tree: ");
        bst.printTree(out);

        out.println("Please enter the element to delete: ");
        bst.delete(in.nextInt());

        out.println("The elements of the tree: ");
        bst.printTree(out);

        out.close();
        in.close();
    }
}
