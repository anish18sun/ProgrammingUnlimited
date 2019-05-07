import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author anish
 * @class : class to represent the tickets problem, given a list of strings of numbers, for each string, divide
 * the string into 7 parts such that each of the numbers is between 1 and 59, if there are multiple such divisions
 * of a number then print them in lexicographically sorted order
 */

public class StringTickets {

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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() { return Integer.parseInt(next()); }

        public long nextLong() { return Long.parseLong(next()); }

        public void close() {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void computeTickets(Set<String> tcktSet, long tcktString, String thisTckt) {
        if(tcktString == 0) {
            tcktSet.add(thisTckt);
            return;
        }

        int base = 10;
        while(tcktString % base < 60) {
            int tckt = (int) (tcktString % base);
            computeTickets(tcktSet, tcktString / base,
                    (thisTckt.length() == 0) ? tckt + "" : tckt + " " + thisTckt);

            base *= 10;
            if(tckt == tcktString % base) { break; }
        }
    }

    private static void trimTicketSet(Set<String> tckts) {
        Iterator<String> tcktIterator = tckts.iterator();

        while(tcktIterator.hasNext()) {
            int spaceCount = 0;
            String tckt = tcktIterator.next();
            /** fastest way to count the occurance of ' '(blank) */
            for(char c : tckt.toCharArray()) { if(c == ' ') { spaceCount++; } }
            if(spaceCount != 6) { tcktIterator.remove(); }
        }
    }

    private static void solve(long[] tcktStrings, PrintStream out) {
        Set<String> tckts = new TreeSet<>();
        Set<String> allTckts = new TreeSet<>();

        for(long tckt : tcktStrings) {
            computeTickets(tckts, tckt, "");
            trimTicketSet(tckts);
            allTckts.addAll(tckts);
            tckts.clear();
        }

        allTckts.forEach(out::println);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of strings(n) and then the strings :- ");

        int n = in.nextInt();
        long[] tckts = new long[n];
        for(int i = 0; i < n; ++i) { tckts[i] = in.nextLong(); }

        solve(tckts, out);
        out.close();
        in.close();
    }
}
