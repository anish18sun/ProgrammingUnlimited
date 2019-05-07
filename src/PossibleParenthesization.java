import java.io.*;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implmentation of the Possible Parenthesization problem
 * @description:  Given a string of boolean terms, count and return the possible parenthesizations
 * of the string such that for each of the parenthesization, the output on evaluation is True(boolean).
 */

public class PossibleParenthesization {

    static class InputReader{
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

        public void close() {
            try {
                reader.close();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static char booleanOp(char op1, char op2, char operator) {
        boolean bop1 = op1 == 'T';
        boolean bop2 = op2 == 'T';
        boolean result = false;

        switch(operator) {
            case '&': result = bop1 & bop2; break;
            case '|': result = bop1 | bop2; break;
            case '^': result = bop1 ^ bop2; break;
        }
        return result ? 'T' : 'F';
    }

    public static int getPossibleParenthesizations(String exp) {
        if(exp == null || exp.length() == 0) { return 0; }
        if(exp.length() == 1 && exp.charAt(0) == 'T') { return 1; }

        int possibleParenthesizations = 0;
        StringBuilder sb = new StringBuilder(exp);
        for(int i = 1; i < sb.length(); i += 2) {
            char op1 = sb.charAt(i - 1);
            char op2 = sb.charAt(i + 1);
            char opResult = booleanOp(op1, op2, sb.charAt(i));

            possibleParenthesizations += getPossibleParenthesizations(exp
                    .substring(0, i - 1) + opResult + exp.substring(i + 2));
        }
        return possibleParenthesizations;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the boolean string: ");
        String boolString = in.next();

        out.println("Number of possible parenthesizations: "
                + getPossibleParenthesizations(boolString));
        out.close();
        in.close();
    }
}
