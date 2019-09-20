import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author anish
 * @class: Implementation of File Input(Reading input from a File)
 */

public class FileInputOutput {

  public static void main(String[] args) throws IOException {
    PrintStream out = System.out;

    Path path = Paths.get("/home/anish/Documents/simple2.txt");
    BufferedReader reader = Files.newBufferedReader(path);
    String content = reader.readLine();
    out.println(content);

    Stream<String> lines = reader.lines();
    Iterator<String> iterator = lines.iterator();
    for(int i = 0; i < 6; ++i) { out.println(iterator.next()); }

    /* still shorter and the best way */
    List<String> strings = Files.readAllLines(path);
    for(int i = 0; i < 6; ++i) { out.println(strings.get(i)); }

    /* Writing to a file */
    String text = "Balidaan dena hoga.";
    byte[] textBytes = text.getBytes();
    Path writePath = Paths.get("/home/anish/Documents/sacred2.txt");
    if(!Files.exists(writePath)) { Files.createFile(writePath); }
    Files.write(writePath, textBytes);

    reader.close();
  }
}
