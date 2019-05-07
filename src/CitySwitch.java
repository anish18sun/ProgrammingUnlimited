import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to implement the city switch problem (Dynamic Programming)
 * @description : There are n days. On each day a person can work either in San Fransisco or New York.
 * For all n days, we are given the cost of setting up office in either SF or NY. If we switch office
 * on a particular day, we incur an additional cost M for that switch, we must find the sequence of
 * appropriate office setups(either SF or NY) for n days, such that the total cost is minimized.
 */

public class CitySwitch {

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

    private static int switchSum(int[] office, int i, int j) {
        int officeCost = 0;
        for(; i <= j; i++) {
            officeCost += office[i-1];
        }
        return officeCost;
    }

    private static int[] getSwitches(int[] SF, int[] NY, int n, int M) {
        char[] officeSeq = new char[n+1];
        int[] opt = new int[n+1];
        int[] sol = new int[n+1];
        final char S = 'S';
        final char N = 'N';

        Arrays.fill(opt, Integer.MAX_VALUE);

        /** base cases */
        if(SF[0] < NY[0]) {
            opt[1] = SF[0];
            officeSeq[1] = S;
        } else {
            opt[1] = NY[0];
            officeSeq[1] = N;
        }

        for(int j = 2; j < n+1; j++) {
            for(int i = 1; i <= j; i++) {
                char office;
                int switchInd = 0;
                int officeCost = 0;
                if(i == 1) {
                    int sfCost = switchSum(SF, i, j);
                    int nyCost = switchSum(NY, i, j);
                    if (sfCost < nyCost) {
                        office = S;
                        officeCost = sfCost;
                    } else {
                        office = N;
                        officeCost = nyCost;
                    }
                } else {
                    switchInd = i;
                    if(officeSeq[i-1] == S) {
                        office = N;
                        officeCost += opt[i-1] + M + switchSum(NY, i, j);
                    } else {
                        office = S;
                        officeCost += opt[i-1] + M + switchSum(SF, i, j);
                    }
                }

                if(opt[j] > officeCost) {
                    sol[j] = switchInd;
                    opt[j] = officeCost;
                    officeSeq[j] = office;
                }
            }
        }

        return sol;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter number of days, switching cost(M) &");
        out.println("cost of working in SF & NY for those n days :-");

        int n = in.nextInt();
        int M = in.nextInt();
        int[] SF = new int[n];
        int[] NY = new int[n];
        for(int i = 0; i < n; ++i) { SF[i] = in.nextInt(); }
        for(int i = 0; i < n; ++i) { NY[i] = in.nextInt(); }

        out.println("The sequence of office switches for all n days :- ");
        out.println(Arrays.toString(getSwitches(SF, NY, n, M)));
        out.close();
        in.close();
    }
}
