import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of SuffuxTree
 */

public class SuffixTree {

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

    private static class STree {
        private class TreeNode {
            char value;
            List<Integer> indexes = new ArrayList<>();
            Map<Character, TreeNode> children = new HashMap<>();

            public void insert(String s, int index) {
                indexes.add(index);
                if(s != null && s.length() > 0) {
                    value = s.charAt(0);
                    TreeNode child = null;
                    if(children.containsKey(value)) {
                        child = children.get(value);
                    } else {
                        child = new TreeNode();
                        children.put(value, child);
                    }
                    String remainder = s.substring(1);
                    child.insert(remainder, index);
                }
            }

            public List<Integer> getIndexes(String s) {
                if(s == null || s.length() == 0) { return indexes; }

                else {
                   char first = s.charAt(0);
                   if(children.containsKey(first)) {
                       String remainder = s.substring(1);
                       return children.get(first).getIndexes(remainder);
                   }
                }
                return null;
            }
        }

        private TreeNode root;

        public STree(String s) {
            root = new TreeNode();
            for(int i = 0; i < s.length(); i++) {
                root.insert(s.substring(i), i);
            }
        }

        public List<Integer> getIndexes(String s) {
            return root.getIndexes(s);
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of small words and the big word,");
        out.println("followed by the small words themselves: ");

        int n = in.nextInt();
        String bigWord = in.next();
        String[] smallWords = new String[n];
        for(int i = 0; i < n; i++) { smallWords[i] = in.next(); }

        STree suffixTree = new STree(bigWord);
        for(int i = 0; i < n; i++) {
            List<Integer> indexes = suffixTree.getIndexes(smallWords[i]);
            out.print("indexes for the word " + smallWords[i] + ": ");

            if(indexes != null) { indexes.forEach(index -> out.print(index + " ")); }
            else { out.print("none"); }
            out.println();
        }

        out.close();
        in.close();
    }
}
