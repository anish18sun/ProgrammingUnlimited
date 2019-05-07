import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

/**
 * @author anish
 * @class : solves the Bonetrousle problem on hackerrank
 * @mySolution : compute the min and the max values that are possible for the sum of B values, these are
 * b(b+1)/2 and b(2k-b+1)/2 respectively. If 'n' is outside this range then print -1. Otherwise fill the
 * solution array of size b with numbers B[1,2,..,b]. Calculate the diff = sum(B) - n. Then the objective
 * function is to reduce diff to 0. We loop untill we have remaining elements and diff > 0. For every value
 * in B and remaining, we select a pair that takes sum(B) closest to n. After that pair is selected, we
 * replace update sum(B), replace element in B and remove that element from remaining elements list,
 * calculate diff and repeat loop.
 * @solution : you should calculate the max and the min values those are minValue =b(b+1)/2 and maxValue = b(2k-b+1)/2
 * this is easy to figure out from the AP(arithmetic progression formula) N(a+l)/2 where a is the first term , l is the
 * last term and N is the number of terms in the AP . The min value occurs when you take first b intergers from 1 to k
 * and the max value occurs when you take the last b ie k-b+1 to k.
 * if n in the given problem is between these values (max and min inclusive) then its possible to get a solution
 * otherwise its not.
 * if its possible to get a solution "sol",initialise your solution to the first b values like [1,2,3] if b=3,
 * n just find (k-minValue)%b . let this number be r. let (k-minValue)/b be q. then to each element in " sol" array
 * add q
 * for the remainder r, from sol[len(sol)-1] to sol[len(sol)-r-1] add 1 to each element.
 * (you should always be able to do this !).
 * PS be careful for cases where b=1.
 * @learning : whenever there is chance of overflow because the inputs will always be large numbers, such as
 * calculating the maximum by some value, even 'long' type may be insufficient to hold the value, so use BigInteger.
*/

public class Bonetrousle {

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

    /** method to implement my solution */
    private static void mySolve(int n, int b, int k, PrintStream out) {
        int sumB = 0;
        int[] B = new int[b];
        List<Integer> selectList = new ArrayList<>();
        for(int i = b+1; i <= k; ++i) { selectList.add(i); }
        for(int i = 1; i <= b; ++i) { B[i-1] = i; sumB += i; }

        if(sumB > n) { out.println("-1"); return; }
        if(sumB == n) { out.println(Arrays.toString(B)); return; }

        int diff = n - sumB;
        while(!selectList.isEmpty() && diff > 0) {
            int bi = 0, rj = 0;
            int size = selectList.size();
            int cdiff = Integer.MAX_VALUE;

            for(int i = 0; i < b; ++i) {
                for(int j = 0; j < size; ++j) {
                    int cdiff_ij = Math.abs((diff + B[i]) - selectList.get(j));
                    if(cdiff > cdiff_ij) {
                        cdiff = cdiff_ij;
                        bi = i;
                        rj = j;
                    }
                }
            }

            sumB = sumB - B[bi] + selectList.get(rj);
            B[bi] = selectList.remove(rj);
            diff = n - sumB;
        }

        if(diff != 0) { out.println("-1"); }
        else { out.println(Arrays.toString(B)); }
    }

    /** method to implement the other solution */
    public static long[] solve(long n, long b, long k, PrintStream out) {
        long min = b*(b+1)/2;
        BigInteger TWO = BigInteger.valueOf(2);
        BigInteger nInt = BigInteger.valueOf(n);
        BigInteger bInt = BigInteger.valueOf(b);
        BigInteger kInt = BigInteger.valueOf(k);
        BigInteger minInt = bInt.add(BigInteger.ONE).multiply(bInt).divide(TWO);
        BigInteger maxInt = TWO.multiply(kInt).subtract(bInt).add(BigInteger.ONE).multiply(bInt).divide(TWO);

        int minCompare = minInt.compareTo(nInt);
        int maxCompare = maxInt.compareTo(nInt);
        if(minCompare > 0 || maxCompare < 0) { return new long[]{-1}; }

        long[] B = new long[(int)b];
        for(int i = 1; i <= b; ++i) { B[i-1] = i; }
        if(n == min) { return B; }

        long sumB = min;
        long q = (n - sumB) / b;
        long r = (n - sumB) % b;
        for(int i = 0; i < b; ++i) { B[i] += q; }
        for(int i = (int)b-1; r > 0 && i > 0; --i, --r) { B[i] += 1; }

        return B;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the values n, b, k :- ");

        int n = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();

        solve(n, b, k, out);
        out.close();
        in.close();
    }
}
