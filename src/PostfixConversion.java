import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author anish
 * @class: Conversion of Infix Expression to Postfix Expression (f+g*h)-i
 * @Notes: To convert to prefix, reverse the infix expression and convert to postfix
 * then reverse the postfix as it is to get the prefix expression.
 */

public class PostfixConversion {

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
        }
        catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() { return Integer.parseInt(next()); }

    public void close() {
      try {
        reader.close();
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static final Map<Character, Integer> precedence = new HashMap<>();

  private static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '/' || c == '*';
  }

  private static boolean isParenthesis(char c) {
    return c == '(' || c == ')';
  }

  public static String convertToPostfix(String infix) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    StringBuilder postfix = new StringBuilder();

    for(char c : infix.toCharArray()) {
      if(isOperator(c) || isParenthesis(c)) {
        if(stack.isEmpty()) {
          stack.addFirst(c);
        } else if(c == '(') {
          stack.addFirst(c);
        } else if(c == ')') {
          while(stack.peekFirst() != '(') {
            postfix.append(stack.removeFirst());
          }
          stack.removeFirst();
        } else if(isParenthesis(stack.peekFirst())) {
          stack.addFirst(c);
        } else if(precedence.get(c) < precedence.get(stack.peekFirst())) {
          stack.addFirst(c);
        } else {
          while(!stack.isEmpty() && stack.peekFirst() != '(' &&
              precedence.get(c) >= precedence.get(stack.peekFirst())) {
            postfix.append(stack.removeFirst());
          }
          stack.addFirst(c);
        }
      } else {
        postfix.append(c);
      }
    }

    while(!stack.isEmpty()) {
      postfix.append(stack.removeFirst());
    }

    return postfix.toString();
  }

  private static void generateOperatorPrecedence() {
    precedence.put('/', 0);
    precedence.put('*', 1);
    precedence.put('+', 2);
    precedence.put('-', 3);
  }

  public static void main(String[] args) {
    PrintStream out = System.out;
    InputReader in = new InputReader();

    String expression = in.next();
    generateOperatorPrecedence();

    out.println(convertToPostfix(expression));
    out.close();
    in.close();
  }
}
