import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement height-balanced AVL tree
 */

public class AVLTree {

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

    static class AVL {
        private class AVLNode {
            int value, height;
            AVLNode left, right;
            public AVLNode(int value) {
                this.value = value;
            }
        }

        private AVLNode root;

        private int getHeight(AVLNode node) {
            if(node == null) { return 0; }
            return node.height;
        }

        private int getBalance(AVLNode node) {
            if(node == null) { return 0; }
            return getHeight(node.left) - getHeight(node.right);
        }

        private AVLNode leftRotate(AVLNode node) {
            if(node == null) { return null; }

            if(node == root) { root = node.right; }
            AVLNode y = node.right;
            node.right = y.left;
            y.left = node;

            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
            return y;
        }

        private AVLNode rightRotate(AVLNode node) {
            if(node == null) { return null; }

            if(node == root) { root = node.left; }
            AVLNode x = node.left;
            node.left = x.right;
            x.right = node;

            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
            return x;
        }

        public void insert(int value) { root = insert(root, value); }

        private AVLNode insert(AVLNode root, int value) {
            if(root == null) { return new AVLNode(value); }

            if(root.value > value) {
                root.left = insert(root.left, value);
            } else if(root.value < value) {
                root.right = insert(root.right, value);
            } else {
                return root;
            }

            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
            int balance = getBalance(root);

            if(balance > 1 && value < root.left.value) {
                return rightRotate(root);
            } else if(balance > 1 && value > root.left.value) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            } else if(balance < -1 && value < root.right.value) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            } else if(balance < -1 && value > root.right.value) {
                return leftRotate(root);
            }

            return root;
        }

        public void delete(int value) { root = delete(root, value); }

        private AVLNode delete(AVLNode root, int value) {
            if(root == null) { return root; }

            if(root.value > value) {
                root.left = delete(root.left, value);
            } else if(root.value < value) {
                root.right = delete(root.right, value);
            } else {
                if(root.left == null) {
                    return root.right;
                } else if(root.right == null) {
                    return root.left;
                } else {
                    AVLNode y = root.right;
                    while(y.left != null) { y = y.left; }

                    root.value = y.value;
                    root.right = delete(root.right, y.value);
                }
            }

            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
            int balance = getBalance(root);

            if(balance > 1 && getBalance(root.left) >= 0) {
                return rightRotate(root);
            } else if(balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            } else if(balance < -1 && getBalance(root.right) <= 0) {
                return leftRotate(root);
            } else if(balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        public void printLevelOrder(PrintStream out) {
            AVLNode spaceNode = new AVLNode(0);
            ArrayDeque<AVLNode> queue = new ArrayDeque<>();

            queue.offerLast(spaceNode);
            queue.offerLast(root);

            while(queue.size() != 1) {
                AVLNode node = queue.pollFirst();
                if(node == spaceNode) {
                    queue.offerLast(spaceNode);
                    out.println();
                } else {
                    out.print(node.value + " ");
                }

                if(node.left != null) { queue.offerLast(node.left); }
                if(node.right != null) { queue.offerLast(node.right); }
            }
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of nodes and then the node values: ");

        int n = in.nextInt();
        AVL tree = new AVL();
        for(int i = 0; i < n; i++) { tree.insert(in.nextInt()); }

        out.println("The Level Order Traversal of the tree: ");
        tree.printLevelOrder(out);

        out.println("Please enter the element to delete from the tree: ");
        int delElement = in.nextInt();
        tree.delete(delElement);

        out.println("The Level Order Traversal of the tree: ");
        tree.printLevelOrder(out);

        out.close();
        in.close();
    }
}
