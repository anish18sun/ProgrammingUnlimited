import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : Implmentation of the Course Grades problem(Dynamic Programming)
 * @description : Given n courses(subjects) and total cumulative time(H), we have to figure
 * out the total time to be spent on each subject so as to maximize the total grades we get
 * in all the courses.  Given a function f[i](h) that gives the grades we get if we spend h
 * hours on course i.
 */

public class CourseGrades {

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

    private static int[] getMaxGrades(int[][] f, int n, int H) {
        int[][] opt = new int[n+1][H+1];
        int[][] sol = new int[n+1][H+1];
        int[] hours = new int[n];

        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < H+1; j++) {
                for(int l = 1; l <= j; l++) {
                    int grade = f[i-1][l-1] + opt[i-1][j-l];
                    if(opt[i][j] < grade) {
                        opt[i][j] = grade;
                        sol[i][j] = l;
                    }
                }
            }
        }

        int h = H;
        for(int i = n; i > 0 && h > 0; i--) {
            hours[i-1] = sol[i][h];
            h -= sol[i][h];
        }

        return hours;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the number of courses, the total number of hours");
        out.println("& then please enter the matrix for getting grades from hours spent on a course:-");

        int n = in.nextInt();
        int H = in.nextInt();
        int[][] f = new int[n][H];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < H; j++) {
                f[i][j] = in.nextInt();
            }
        }

        out.println("Course wise hour allotment: " +
                Arrays.toString(getMaxGrades(f, n, H)));
        out.close();
        in.close();
    }
}
