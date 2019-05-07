import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class : to solve the robot matrix traversal problem
 * @description : given a matrix with 0, 1, 2 entries where '0' denotes a valid cell, '1' denotes blocked cell
 * and '2' denotes initial position, determine the minimum number of steps it takes to get to the end of the matrix
 * @code : simple breadth-first search
 * @test : input matrix :
 *      1 1 1 0 1
 *      1 2 0 0 1
 *      1 0 1 0 1
 *      1 1 1 0 0
 */

public class RobotMatrix {

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

    static class Pair{
        int i, j, steps;
        public Pair() {}
        public Pair(int i , int j) {
            this.i = i;
            this.j = j;
        }
        public Pair(int i , int j, int steps) {
            this.i = i;
            this.j = j;
            this.steps = steps;
        }
    }

    private static void solve(int[][] grid, int n, int m, PrintStream out) {
        int iStart = -1, jStart = -1;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(grid[i][j] == 2) {
                    iStart = i;
                    jStart = j;
                }
            }
        }

        if(iStart == -1) {
            out.println("Initial position not found !");
            return;
        }

        int steps = -1;
        Pair start = new Pair(iStart, jStart);
        ArrayDeque<Pair> queue = new ArrayDeque<>();

        queue.offerLast(start);
        while(!queue.isEmpty()) {
            Pair pos = queue.pollFirst();
            if(pos.i == 0 || pos.i == n-1 || pos.j == 0 || pos.j == m-1) {
                steps = pos.steps;
                break;
            }
            grid[pos.i][pos.j] = 1;
            for(int i = -1; i <= 1; i+=2) {
                if(grid[pos.i + i][pos.j] == 0)
                { queue.offerLast(new Pair(pos.i + i, pos.j, pos.steps + 1)); }
            }
            for(int j = -1; j <= 1; j+=2) {
                if(grid[pos.i][pos.j + j] == 0)
                { queue.offerLast(new Pair(pos.i, pos.j + j, pos.steps + 1)); }
            }
        }

        out.println(steps);
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the n x m size of the matrix, followed by n rows of m digits:- ");

        int n = in.nextInt();
        int m = in.nextInt();
        int[][] grid = new int[n][m];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                grid[i][j] = in.nextInt();
            }
        }

        solve(grid, n, m, out);
        out.close();
        in.close();
    }
}
