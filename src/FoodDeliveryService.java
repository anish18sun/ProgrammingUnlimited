import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

/**
 * @author anish
 * @class: Implementation of the Food Delivery Service problem(Hackerrank)
 * @description: There is a list of N cities and we have to deliver food from city 1 to city X.
 * The move from city 'u' to city 'v' is possible only if the following conditions are met:
 * v = (u - 1) | (u + 1) | (u * 2) | (u / 2) | v can be achieved by sorting the digits of
 * 'u' in descending order. Find the minimum number of steps to get to city X from city 1.
 * Implemented using Breadth First Search for all possible ways of reaching a city.
 */

public class FoodDeliveryService {

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

    private static final int MAX_N = 1000123;
    private static List<Integer> digitList = new ArrayList<>();

    private static int sortDesc(int n) {
        digitList.clear();
        while(n > 0) {
            digitList.add(n % 10);
            n /= 10;
        }

        n = 0;
        Collections.sort(digitList, Collections.reverseOrder());
        for(int i : digitList) {
            n = n * 10 + i;
        }

        return n;
    }

    private static int getMinSteps(int X, int N) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] city = new int[MAX_N];
        Arrays.fill(city, -1);
        queue.add(1);
        city[1] = 0;

        while(!queue.isEmpty()) {
            int v = queue.removeFirst();
            if(v + 1 <= N && city[v + 1] == -1) {
                city[v + 1] = city[v] + 1;
                queue.addLast(v + 1);
            }
            if(v - 1 >= 1 && city[v - 1] == -1) {
                city[v - 1] = city[v] + 1;
                queue.addLast(v - 1);
            }
            if(v * 2 <= N && city[v * 2] == -1) {
                city[v * 2] = city[v] + 1;
                queue.addLast(v * 2);
            }
            if(v % 2 == 0 && city[v / 2] == -1) {
                city[v / 2] = city[v] + 1;
                queue.addLast(v / 2);
            }

            int u = sortDesc(v);
            if(u <= N && city[u] == -1) {
                city[u] = city[v] + 1;
                queue.addLast(u);
            }
        }

        return city[X];
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int t = in.nextInt();
        while(t-- > 0) {
            int N = in.nextInt();
            int X = in.nextInt();
            out.println(getMinSteps(X, N));
        }
        out.close();
        in.close();
    }
}
