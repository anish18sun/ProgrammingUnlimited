import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Sherlock And Anagrams problem (Hackerrank - Strings)
 * @description: Given a string, find all substrings in the string that are anagrams of each other.
 * @notes: Takeaways: (1) you can use "accumulator" arrays to store the number of occurences of each
 * letter from 0 to an index, in O(N). Then you can lookup the number of letters between i and j in O(1)
 * subsequently, by taking a difference. (2) You can use these to create an array of 26 integers for each
 * substring, noting the count of each letter, which only needs to be done once, in O(N^2), because there
 * are N*(N+1)/2 substrings. (3) You can sort this array once, which is therefore done in O(log N * N^2),
 * which is the time complexity. (4) You can then go through this array once, counting the number of times
 * there is a repeated anagram (equal arrays). (5) Finally you can get the final answer by summing R*(R-1)/2
 * for each repeated anagram, which is a math formula based on the sum from 1, 2, 3 .. R - 1.
 */

public class SherlockAnagrams {

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
            int count;
            char value;
            boolean isEnd;
            Map<Character, TrieNode> children = new HashMap<>();

            TrieNode(char value) { this.value = value; }

            void insert(char[] s) {
                TrieNode node = this;
                Arrays.sort(s);

                for(char c: s) {
                    TrieNode child = node.children.get(c);
                    if(child == null) {
                        child = new TrieNode(c);
                        node.children.put(c, child);
                    }
                    node = child;
                }

                node.count++;
                node.isEnd = true;
            }

            boolean search(char[] s) {
                TrieNode node = this;
                Arrays.sort(s);

                for(char c: s) {
                    TrieNode child = node.children.get(c);
                    if(child == null) { return false; }
                    node = child;
                }

                node.count++;
                return node.isEnd;
            }
        }

        TrieNode root;

        Trie() { root = new TrieNode('\0'); }

        int getCount(TrieNode node) {
            int anagramCount = 0;
            if(node.isEnd) { anagramCount += nCr(node.count, 2); }

            for(TrieNode child: node.children.values()) {
                anagramCount += getCount(child);
            }

            return anagramCount;
        }
    }

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            char[] c1 = o1.toCharArray();
            char[] c2 = o2.toCharArray();
            Arrays.sort(c1);
            Arrays.sort(c2);

            String s1 = new String(c1);
            String s2 = new String(c2);
            return s1.compareToIgnoreCase(s2);
        }
    }

    private static long nCr(int n, int r) {
        int[] c = new int[r + 1];
        c[0] = 1;

        for(int i = 1; i <= n; ++i) {
            for(int j = Math.min(i, r); j > 0; --j) {
                c[j] = (c[j] + c[j-1]);
            }
        }

        return c[r];
    }

    private static int anagramCountV2(String S) {
        Map<String, Integer> stringMap = new TreeMap<>(new StringComparator());
        int length = S.length();
        int anagramCount = 0;

        for(int i = 0; i < length; ++i) {
            for(int l = 1; (i + l) <= length; ++l) {
                int j = i + l;
                String substring = S.substring(i, j);
                int count = stringMap.getOrDefault(substring, 0);
                stringMap.put(substring, count + 1);
            }
        }

        for(int count: stringMap.values()) {
            anagramCount += nCr(count, 2);
        }

        return anagramCount;
    }

    private static int anagramCount(String S) {
        char[] s = S.toCharArray();
        Trie trie = new Trie();

        for(int i = 0; i < s.length; ++i) {
            for(int l = 1; (i + l) <= s.length; ++l) {
                int j = i + l;
                char[] sCopy = Arrays.copyOfRange(s, i, j);
                if(!trie.root.search(sCopy)) {
                    trie.root.insert(sCopy);
                }
            }
        }

        return trie.getCount(trie.root);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();
        while(t-- > 0) {
            String S = in.next();
            out.println(anagramCountV2(S));
        }

        out.close();
        in.close();
    }
}