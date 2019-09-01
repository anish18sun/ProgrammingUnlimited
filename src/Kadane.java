import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Kadane {

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

    private static boolean isNumeric(String s) {
        try {
           Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private static boolean validate(String[] earnings) {
        for(String earning: earnings) {
            earning = earning.trim();
            if(!isNumeric(earning) || earning.length() == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        String input = in.next();
        String[] earnings = input.split(",");

        if(!validate(earnings)) {
            out.println(0);
            System.exit(0);
        }

        int sum = 0, maxSum = 0;
        for(int i = 0; i < earnings.length; ++i) {
            sum += Integer.parseInt(earnings[i]);
            if(maxSum < sum) { maxSum = sum; }
            if(sum < 0) { sum = 0; }
        }

        out.println(maxSum);
        out.close();
        in.close();
    }
}
