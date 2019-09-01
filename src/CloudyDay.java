import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author anish
 * @class: Implementation of the CloudyDay problem(Hackerrank)
 * @description: There is a city represented on a one-dimensional line. The city has
 * various towns located on coordinates of the city. All the towns have varying populations.
 * There are clouds in the city, the number of clouds in the city is different from the
 * number of towns. Each cloud has an expanse over a number of cities represented by
 * another array. The city officials can remove only one cloud to make the city sunny.
 * We must find the cloud that should be removed so that maximum number of people are
 * in sunny towns. It may even be that some towns are already not under clouds or the
 * expanse of clouds. The output should be the total maximized number of people not in
 * cloudy cities.
 */

public class CloudyDay {

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

    static final int N = 1001000;

    static class Pair implements Comparable<Pair> {
        int i, j;
        public Pair(int i, int j) { this.i = i; this.j = j; }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.i, o.i);
        }
    }

    private static void computeTimeline(Map<Integer, List<Pair>> timeline, int n, int m,
                                        int[] pop, int[] cities, int[] clouds, int[] extent) {
        for(int i = 0; i < n; ++i) {
            List<Pair> pairs = timeline.computeIfAbsent(cities[i], c -> new ArrayList<>());
            pairs.add(new Pair(2, pop[i]));
        }

        for(int i = 0; i < m; ++i) {
            List<Pair> pairs = timeline.computeIfAbsent(clouds[i] - extent[i], c -> new ArrayList<>());
            pairs.add(new Pair(1, i));

            pairs = timeline.computeIfAbsent(clouds[i] + extent[i] + 1, c -> new ArrayList<>());
            pairs.add(new Pair(-1, i));
        }
    }

    private static long getMaxSunny(Map<Integer, List<Pair>> timeline) {
        TreeSet<Integer> activeClouds = new TreeSet<>();
        long[] cloudOverCity = new long[N];
        long maxSunny = 0;

        for(Map.Entry<Integer, List<Pair>> timelineEntry: timeline.entrySet()) {
            int coordinate = timelineEntry.getKey();
            List<Pair> events = timelineEntry.getValue();

            Collections.sort(events);
            for(Pair event: events) {
                if(event.i == -1) {
                    activeClouds.remove(event.j);
                } else if(event.i == 1) {
                    activeClouds.add(event.j);
                } else {
                    int activeSize = activeClouds.size();
                    if(activeSize == 1) { cloudOverCity[activeClouds.first()] += event.j; }
                    else if(activeSize == 0) { maxSunny += event.j; }
                }
            }
        }

        long sunny = 0;
        for(int i = 0; i < N; ++i) { sunny = Math.max(sunny, cloudOverCity[i]); }

        return maxSunny + sunny;
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        int n = in.nextInt();
        int[] pop = new int[n];
        int[] cities = new int[n];
        for(int i = 0; i < n; ++i) { pop[i] = in.nextInt(); }
        for(int i = 0; i < n; ++i) { cities[i] = in.nextInt(); }

        int m = in.nextInt();
        int[] clouds = new int[m];
        int[] extent = new int[m];
        for(int i = 0; i < m; ++i) { clouds[i] = in.nextInt(); }
        for(int i = 0; i < m; ++i) { extent[i] = in.nextInt(); }

        Map<Integer, List<Pair>> timeline = new TreeMap<>();
        computeTimeline(timeline, n, m, pop, cities, clouds, extent);

        out.println(getMaxSunny(timeline));
        out.close();
        in.close();
    }
}
