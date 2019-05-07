import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implementation of the Nary - tree best path problem(Dynamic Programming)
 * @description : Given an n-ary tree(this program uses a binary tree), there is a way to communicate
 * with the child nodes such that it's the least cost way of doing so. Example :
 *      A     in the tree shown below, the node A wants to send a message to other nodes as fast
 *    /  \    as possible. But one node can send a message to only one other node at a time. So
 *   B    D   node A can choose to send a message to either node B or D first. But one of those
 *   \        choices is optimal. If sent to B first, in the next turn, B sends to C and A sends
 *   C        to D, for a total of two rounds. However, if A sends to D first, the number of rounds
 *            increases to 3. Given an n-ary tree we must find the optimal order of sending messages
 * for each node.
 */

public class NaryPath {

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

    static class TreeNode {
        int cost;
        char value;
        TreeNode left, right;
        private TreeNode(char value) { this.value = value; }
        public static TreeNode getInstance(char value) { return new TreeNode(value); }
    }

    /** method to return a binary tree*/
    private static TreeNode getTree() {
        TreeNode root = TreeNode.getInstance('A');

        TreeNode child1 = TreeNode.getInstance('B');
        TreeNode child2 = TreeNode.getInstance('C');

        TreeNode child11 = TreeNode.getInstance('E');
        TreeNode child12 = TreeNode.getInstance('F');
        TreeNode child21 = TreeNode.getInstance('G');

        TreeNode child111 = TreeNode.getInstance('H');
        TreeNode child211 = TreeNode.getInstance('I');
        TreeNode child212 = TreeNode.getInstance('J');

        root.left = child1;
        root.right = child2;

        child1.left = child11;
        child1.right = child12;
        child2.right = child21;

        child11.left = child111;
        child21.left = child211;
        child21.right = child212;

        return root;
    }

    private static int getCost(TreeNode node) {
        return node == null ? 0 : node.cost;
    }

    private static void optimalPath(TreeNode root) {
        if(root == null) { return; }

        optimalPath(root.left);
        optimalPath(root.right);
        root.cost = Math.max(getCost(root.left), getCost(root.right));
        if(root.cost == 0) {
            if(root.left != null) { root.cost += 1; }
            if(root.right != null) { root.cost += 1; }
        } else {
            root.cost += 1;
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        TreeNode root = getTree();
        optimalPath(root);

        out.println("Optimal cost for the tree: " + root.cost);
        out.close();
        in.close();
    }
}
