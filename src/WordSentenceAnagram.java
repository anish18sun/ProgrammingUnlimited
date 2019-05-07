import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Word Sentence Anagram problem(Hackerrank)
 * @description: Given a list of words, some of the words may be anagrams of each other and a list
 * of sentences, we have to find the number of possible sentence
 * @input: 5 1
 * cat act the bats tabs
 * cat_the_bats
 */

public class WordSentenceAnagram {

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

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            char[] c1 = o1.toCharArray();
            char[] c2 = o2.toCharArray();

            Arrays.sort(c1);
            Arrays.sort(c2);
            o1 = new String(c1);
            o2 = new String(c2);
            return o1.compareToIgnoreCase(o2);
        }
    }

    private static Map<String, Integer> computeCounts(String[] words) {
        Map<String, Integer> countMap = new TreeMap<>(new StringComparator());
        for(String word: words) { countMap.put(word, countMap.getOrDefault(word, 0) + 1); }

        return countMap;
    }

    private static int[] getAnagramCount(String[] words, int n, String[] sentences, int m) {
        int[] anagramCount = new int[m];
        Map<String, Integer> countMap = computeCounts(words);

        int i = 0;
        for(String sentence: sentences) {
            anagramCount[i] = 1;
            String[] wordsInSentence = sentence.split("_");
            for(String word: wordsInSentence) {
                anagramCount[i] *= countMap.get(word);
            }
            i++;
        }

        return anagramCount;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int m = in.nextInt();
        String[] words = new String[n];
        String[] sentences = new String[m];
        for(int i = 0; i < n; ++i) { words[i] = in.next(); }
        for(int i = 0; i < m; ++i) { sentences[i] = in.next(); }

        int[] anagramCounts = getAnagramCount(words, n, sentences, m);
        out.println(Arrays.toString(anagramCounts));

        out.close();
        in.close();
    }
}
