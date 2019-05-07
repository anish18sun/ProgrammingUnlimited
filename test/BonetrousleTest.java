import org.junit.Assert;
import org.junit.Test;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @class : to test the bonetrousle class
 */

public class BonetrousleTest {

    @Test
    public void testSolver() {
        PrintStream out = System.out;

        long n = 256289567047003499L;
        long k = 58595467307711244L;
        int b = 20239;
        long[] B = Bonetrousle.solve(n, b, k, out);
        out.println("Running tests for Bonetrousle.solve().");

        long sum = 0, length = B.length;
        Set<Long> checkSet = new HashSet<>();
        for(int i = 0; i < length; ++i) {
            if(checkSet.contains(B[i])) {
                Assert.fail("Element repeated: " + B[i]);
                break;
            }
            else { checkSet.add(B[i]); }

            if(B[i] > k) { Assert.fail("Element exceeded : " + B[i]); }
            sum += B[i];
        }

        if(sum != n) { Assert.fail("Insufficient sum : " + sum + " for value of n : " + n); }

        out.println("Test passed !");
        out.close();
    }
}
