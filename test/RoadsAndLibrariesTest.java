import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @class: Test class for the Roads and Libraries problem
 */

public class RoadsAndLibrariesTest {

    private static final String OUTPUT_PATH = "/home/anish/Documents/Googol/test/output.txt";
    private static final String TEST_PATH = "/home/anish/Documents/Googol/test/testcases.txt";

    @Test
    public void testSolver() throws IOException {
        PrintStream out = System.out;
        List<String> outputs = Files.readAllLines(Paths.get(OUTPUT_PATH));
        List<String> testInput = Files.readAllLines(Paths.get(TEST_PATH));

        int line = 1, n = 0, m = 0, cLib = 0, cRoad = 0 ;
        int testCases = Integer.parseInt(testInput.get(0));

        while(testCases-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(testInput.get(line++));
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            cLib = Integer.parseInt(tokenizer.nextToken());
            cRoad = Integer.parseInt(tokenizer.nextToken());
            RoadsAndLibraries.Vertex[] graph = new RoadsAndLibraries.Vertex[n];

            for(int i = 0; i < n; ++i) { graph[i] = new RoadsAndLibraries.Vertex(i + 1); }

            for(int i = 0; i < m; ++i) {
                tokenizer = new StringTokenizer(testInput.get(line++));
                RoadsAndLibraries.Vertex v = graph[Integer.parseInt(tokenizer.nextToken()) - 1];
                RoadsAndLibraries.Vertex u = graph[Integer.parseInt(tokenizer.nextToken()) - 1];
                v.adjList.add(u);
                u.adjList.add(v);
            }

            long costAns = Long.parseLong(outputs.get(10 - (testCases + 1)));
            long cost = RoadsAndLibraries.getMinCost(graph, n, cLib, cRoad);

            if(cost != costAns) {
                out.println("Output mismatch for testCase: " + (11 - (testCases + 1)));
                out.println("My Ans:" + cost + ", correct Soln: " + costAns);
            } else {
                out.println("Output matched for testCase: " + (11 - (testCases + 1)));
            }
        }
    }
}
