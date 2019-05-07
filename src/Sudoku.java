import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Implementation of the Sudoku Puzzle
 */

public class Sudoku {

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

    private enum RowType { ROW, COLUMN }

    private static boolean isValid(int[][] arr, int r, int limit, RowType rowType) {
        boolean[] dupCheck = new boolean[9];
        if(rowType == RowType.ROW) {
            for(int i = 0; i <= limit; i++) {
                if(dupCheck[arr[r][i] - 1]) { return false; }
                else { dupCheck[arr[r][i] - 1] = true; }
            }
        }
        else if(rowType == RowType.COLUMN) {
            for(int i = 0; i <= limit; i++) {
                if(dupCheck[arr[i][r] - 1]) { return false; }
                else { dupCheck[arr[i][r] - 1] = true; }
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] arr, int i, int j, int n, int length) {
        if(n == (length * length + 1)){ return true; }
        if(i < 0 || i >= length || j < 0 || j >= length) { return false; }

        boolean valid = false;
        for(int k = 1; k <= 9; k++) {
            arr[i][j] = k;

            valid = isValid(arr, i, j, RowType.ROW);
            if(!valid) { continue; }
            valid = isValid(arr, j, i, RowType.COLUMN);
            if(!valid) { continue; }

            if(n % length == 0) {
                valid = solveSudoku(arr, i + 1, 0, n + 1, length);
            } else {
                valid = solveSudoku(arr, i, j + 1, n + 1, length);
            }
            if(valid) { break; }
        }

        if(!valid) { arr[i][j] = 0; }
        return valid;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int[][] arr = new int[3][3];
        out.println("Creating a Suduko Matrix.");
        out.println("Possible to solve: " + solveSudoku(arr, 0, 0, 1, 3));

        out.println("Solved Sudoku Matrix: ");
        for(int i = 0; i < 3; i++) { out.println(Arrays.toString(arr[i])); }

        out.close();
        in.close();
    }
}
