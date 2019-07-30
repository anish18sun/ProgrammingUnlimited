import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Password Cracker problem(Hackerrank - Recursion)
 * @description: Given a dictionary of words and a long word, we must find whether the long
 * word is only made up of the dictionary of words, if so we must separate the words out of
 * the long word and print them separately. If not possible, print 'WRONG PASSWORD'.
 */

public class PasswordCracker {

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

    static class Trie {
        private class TrieNode {
            char value;
            boolean isEnd;
            Map<Character, TrieNode> children = new HashMap<>();
            public TrieNode(char value) { this.value = value; }
        }

        private TrieNode root = null;
        public Trie() { root = new TrieNode('\0'); }

        public void insert(String word) {
            if(word == null || word.length() == 0) { return; }

            TrieNode node = root;
            for(char c : word.toCharArray()) {
                TrieNode child = node.children.get(c);
                if(child == null) {
                    child = new TrieNode(c);
                    node.children.put(c, child);
                }
                node = child;
            }
            node.isEnd = true;
        }

        public List<String> search(char[] checkers, int i) {
            if(checkers == null || checkers.length == 0) { return null; }
            List<String> words = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            TrieNode node = root;

            for(TrieNode child = node.children.get(checkers[i]);
                child != null; ++i, child = node.children.get(checkers[i])) {
                sb.append(child.value);
                if(child.isEnd) {
                    words.add(sb.toString());
                }
                if(i == checkers.length - 1) { break; }
                node = child;
            }

            return words;
        }
    }

    private static boolean isPossible(char[] checkers, List<String> checkerTokens, Trie trie, int i) {
        if(i == checkers.length) { return true; }
        List<String> words = trie.search(checkers, i);
        if(words.size() == 0) { return false; }

        for(String word: words) {
            if(isPossible(checkers, checkerTokens, trie,i + word.length())) {
                checkerTokens.add(word);
                return true;
            }
        }

        return false;
    }

    private static List<String> getPasswords(String[] passwords, String checker) {
        Trie trie = new Trie();
        char[] charCheckers = checker.toCharArray();
        List<String> checkerTokens = new ArrayList<>();
        for(String password: passwords) {
            trie.insert(password);
        }

        if(!isPossible(charCheckers, checkerTokens, trie,0)) {
            checkerTokens.clear();
            checkerTokens.add("WRONG OPERATION");
        }

        Collections.reverse(checkerTokens);
        return checkerTokens;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        String checker = in.next();
        String[] passwords = new String[n];
        for(int i = 0; i < n; ++i) {
            passwords[i] = in.next();
        }

        List<String> passTokens = getPasswords(passwords, checker);
        for(String passToken: passTokens) {
            out.print(passToken + " ");
        }
        out.println();
        out.close();
        in.close();
    }
}
