import java.io.PrintStream;

/**
 * @author anish
 * @class: Implementation of the Print In Order problem(LeetCode - Concurrency)
 */

public class PrintInOrder {

  static class Foo {

    private int counter = 3;

    synchronized void first(PrintStream out) {
      if(counter == 3) {
        out.print("first");
        --counter;
        notifyAll();
      }
    }

    synchronized void second(PrintStream out) {
      while(counter > 2) {
        try {
          wait();
        } catch(InterruptedException e) {
          out.println("Thread Exception: " + e.getMessage());
        }
      }
      out.print("second");
      --counter;
      notifyAll();
    }

    synchronized void third(PrintStream out) {
      while(counter > 1) {
        try {
          wait();
        } catch(InterruptedException e) {
          out.println("Thread Exception: " + e.getMessage());
        }
      }
      out.print("third");
      --counter;
      notifyAll();
    }
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    Foo foo = new Foo();

    Thread t1 = new Thread(() -> foo.first(out));
    Thread t3 = new Thread(() -> foo.third(out));
    Thread t2 = new Thread(() -> foo.second(out));

    t2.start();
    t3.start();
    t1.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch(InterruptedException e) {
      out.println("Exception: " + e.getMessage());
    }
  }
}
