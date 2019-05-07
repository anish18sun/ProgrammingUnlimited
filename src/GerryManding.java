import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implementation of the Gerrymanding problem(Dynamic Programming)
 * @description : Gerrymanding is the process of estimation of which of the two
 * political parties - Democrats vs. Republicans is going to come to power. Con
 * -sider 'n' precincts. Let the two parties be labelled as 'A' and 'B' .  From
 * each precinct, we have 'm' voters who vote for either 'A' or 'B'. So 'm' voters
 * will be divided into either 'A' group or 'B' group. Our task is to find whether
 * we can find a suitable half-division of the 'n' precincts into two districts
 * (two groups), such that the majority from both groups is from one party. For
 * eg: we divide 'n' precincts into two groups so that the sum of voters for 'A'
 * is greater in both groups then we have found such a division and party 'A' is
 * likely to win the election - gerrymanding, similarly for party 'B'.
 * @input:
 * 4
 * 55 43 60 47
 * 45 57 40 53
 */

public class GerryManding {

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

    private static void checkValid(int[] opt1, int[] opt2, int[] adv1,
        int[] adv2, int[] ele1, int[] ele2, int i, int j, int[] A, int[] B) {

        int size1 = ele1[i] + 1;
        int size2 = ele2[i] + j - i - 1;
        if((j+1) % 2 == 0 && size1 != size2) { return; }
        else if(Math.abs(size1 - size2) > 1) { return; }

        int opt2Sum = opt2[i];
        int adv2Sum = adv2[i];
        int opt1Sum = opt1[i] + A[j];
        int adv1Sum = adv1[i] + B[j];
        for(int k = i+1; k < j; k++) { opt2Sum += A[k]; }
        for(int k = i+1; k < j; k++) { adv2Sum += B[k]; }
        if(opt1Sum < adv1Sum || opt2Sum < adv2Sum) { return; }

        ele1[j] = size1;
        ele2[j] = size2;
        opt1[j] = opt1Sum;
        opt2[j] = opt2Sum;
        adv1[j] = adv1Sum;
        adv2[j] = adv2Sum;
    }

    private static char gerrymandingDivide(int[] A, int [] B, int n) {
        int[] opt1 = new int[n];
        int[] opt2 = new int[n];
        int[] adv1 = new int[n];
        int[] adv2 = new int[n];
        int[] ele1 = new int[n];
        int[] ele2 = new int[n];
        final char partyA = 'A';
        final char partyB = 'B';
        final char none = 'N';

        ele1[0] = 1;
        opt1[0] = A[0];
        adv1[0] = B[0];
        for(int j = 1; j < n; j++) {
            for(int i = 0; i < j; i++) {
                checkValid(opt1, opt2, adv1, adv2, ele1, ele2, i, j, A, B);
            }
        }

        if(opt1[n-1] > adv1[n-1] && opt2[n-1] > adv2[n-1]) { return partyA; }

        Arrays.fill(opt1, 0);
        Arrays.fill(opt2, 0);
        Arrays.fill(adv1, 0);
        Arrays.fill(adv2, 0);
        Arrays.fill(ele1, 0);
        Arrays.fill(ele2, 0);

        ele1[0] = 1;
        opt1[0] = B[0];
        adv1[0] = A[0];
        for(int j = 1; j < n; j++) {
            for(int i = 0; i < j; i++) {
                checkValid(opt1, opt2, adv1, adv2, ele1, ele2, i, j, B, A);
            }
        }

        if(opt1[n-1] > adv1[n-1] && opt2[n-1] > adv2[n-1]) { return partyB; }

        return none;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of precincts :- ");
        out.println("& then please enter the number of voters in A and B :-");

        int n = in.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        for(int i = 0; i < n; i++) { A[i] = in.nextInt(); }
        for(int i = 0; i < n; i++) { B[i] = in.nextInt(); }

        char winner = gerrymandingDivide(A, B, n);
        out.println("Winner of election: " + winner);
        out.close();
        in.close();
    }
}
