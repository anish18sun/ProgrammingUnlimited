import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class : to implement the Trie Structure, implement the delete method also
 */

public class Trie {

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

    static class TrieNode {
        char value;
        boolean isEnd;
        Map<Character, TrieNode> children;
        public TrieNode(char value) {
            this.value = value;
            children = new HashMap<>();
        }
    }

    private static void insert(String string, TrieNode node) {
        if(string == null || string.length() <= 0) { return; }

        for(char c : string.toCharArray()) {
            TrieNode next = node.children.get(c);
            if(next == null) {
                TrieNode newNode = new TrieNode(c);
                node.children.put(c, newNode);
                node = newNode;
            }
            else { node = next; }
        }
        node.isEnd = true;
    }

    private static void delete(String string, TrieNode node) {
        if(string == null || string.length() <= 0) { return; }

        int i = 0;
        for(char c : string.toCharArray()) {
            TrieNode next = node.children.get(c);
            if(next == null) { break; }
            else { node = next; i++; }
        }

        if(i == string.length() && node.isEnd) { node.isEnd = false; }
    }

    private static boolean searchWord(String string, TrieNode node) {
        if(string == null || string.length() <= 0) { return false; }

        int i = 0;
        for(char c : string.toCharArray()) {
            TrieNode next = node.children.get(c);
            if(next == null) { break; }
            else { node = next; i++; }
        }

        return (i == string.length() && node.isEnd);
    }

    /** method to search the words closest to input string */
    private static List<String> search(String string, TrieNode node) {
        if(string == null || string.length() <= 0) { return null; }

        int i = 0;
        List<String> results = new ArrayList<>();
        for(char c : string.toCharArray()) {
            TrieNode next = node.children.get(c);
            if(next == null) { break; }
            else { node = next; i++; }
        }

        if(i == string.length() && node.isEnd) {
            results.add(string);
            return results;
        }

        /** return all possible matchings */
        ArrayDeque<TrieNode> nodeStack = new ArrayDeque<>();
        ArrayDeque<String> stringStack = new ArrayDeque<>();

        nodeStack.addFirst(node);
        stringStack.addFirst(string.substring(0, i-1));

        while (!nodeStack.isEmpty()) {
            TrieNode thisNode = nodeStack.removeFirst();
            String thisString = stringStack.removeFirst();

            if (thisNode.children.size() == 0) {
                results.add(thisString + "" + thisNode.value);
            } else {
                for (TrieNode child : thisNode.children.values()) {
                    nodeStack.addFirst(child);
                    stringStack.addFirst(thisString + "" + thisNode.value);
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of strings and the strings themselves :-");

        int n = in.nextInt();
        String[] strings = new String[n];
        for(int i = 0; i < n; ++i) { strings[i] = in.next(); }

        TrieNode root = new TrieNode('\0');
        for(String string : strings) { insert(string, root); }

        out.println("Please enter exact word that you want to be searched :- ");
        String exactWord = in.next();

        out.println("Please enter some random word to be searched :- ");
        String randomWord = in.next();

        out.println((searchWord(exactWord, root)) ? "Exact word found." : "Exact word not found.");
        out.println("Search results from random words :- ");
        search(randomWord, root).forEach(word -> out.print(word + " "));
    }
}
