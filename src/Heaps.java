import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  @author anish
 * @class: Implementation of Heap
 */

public class Heaps {

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader() {
            tokenizer = null;
            reader = new BufferedReader(new InputStreamReader(System.in), 32768);
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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

    private static int left(int i) { return i << 1; }

    private static int right(int i) { return (i << 1) + 1; }

    private static int parent(int i) { return (int)(Math.floor(i >> 1)); }

    private static void maxHeapify(int[] heap, int heapSize, int i) {
        int largest;
        int l = left(i);
        int r = right(i);

        if(l < heapSize && heap[l] > heap[i]) { largest = l; }
        else { largest = i; }
        if(r < heapSize && heap[r] > heap[largest]) { largest = r; }

        if(largest != i) {
            int temp = heap[largest];
            heap[largest] = heap[i];
            heap[i] = temp;
            maxHeapify(heap, heapSize, largest);
        }
    }

    private static void buildMaxHeap(int[] heap) {
        for(int i = (int)(Math.floor(heap.length >> 1)) - 1; i >= 0; i--) {
            maxHeapify(heap, heap.length, i);
        }
    }

    private static void heapSort(int[] heap) {
        buildMaxHeap(heap);
        int heapSize = heap.length;
        for(int i = heap.length; i > 0; i--) {
            int temp = heap[0];
            heap[0] = heap[heapSize - 1];
            heap[heapSize - 1] = temp;

            heapSize -= 1;
            maxHeapify(heap, heapSize, 0);
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        InputReader in = new InputReader();

        out.println("Please enter the size of the heap & then the elements of the heap: ");

        int heapSize = in.nextInt();
        int[] heap = new int[heapSize];
        for(int i = 0; i < heapSize; ++i) { heap[i] = in.nextInt(); }

        heapSort(heap);
        out.println("The elements in the heap-sorted order: ");
        out.println(Arrays.toString(heap));
        out.close();
        in.close();
    }
}
