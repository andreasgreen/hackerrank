package javas;

import java.util.*;

/**
 * Created by andreas on 2018-08-18.
 */
public class JavaStack {

    public static void main(String [] argh)
    {
        Scanner sc = new Scanner(System.in);



        while (sc.hasNext()) {
            String input = sc.next();

            Stack<Character> stack = new Stack<>();

            for(Character c : input.toCharArray()) {

                if(stack.size() > 0) {
                    Character last = stack.peek();

                    boolean popLast =
                            (last == '(' && c == ')') ||
                            (last == '{' && c == '}') ||
                            (last == '[' && c == ']');

                    if(popLast) {
                        stack.pop();
                    } else {
                        stack.add(c);
                    }

                } else {
                    stack.add(c);
                }
            }

            if(stack.isEmpty()) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }

        }

    }
}
