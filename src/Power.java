import java.io.PrintStream;

/**
 * @author anish
 * @class: Implementation of the Power Method
 */

public class Power {

    private static final int MOD = 1000000007;

    private static long pow(long x, long y) {
        if(y == 0) { return 1; }
        if((y & 1) == 1) {
            return pow(x, y - 1) * x % MOD;
        } else {
            long temp = pow(x, y / 2);
            return temp * temp % MOD;
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        out.println("Power of large number: " + pow(10, 9));
    }
}
