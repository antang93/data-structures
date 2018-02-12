
import java.util.*;

/** Program that determines whether a given expression is balanced.
  * 
  * @author Annie Tang
  */
public class Balance {
  
  /** Returns whether the supplied arithmetic expression is balanced.
    * 
    * An expression is considered balanced if every opening parenthesis,
    * brace or bracket can be correctly matched to a corresponding closing
    * parenthesis, brace or bracket respectively.
    * 
    * @param expression a string containing an arithmetic expression that we
    *        wish to test for balance.
    * @return true iff expression is balanced.
    */
  public static boolean isBalanced(String expression) {
    int rightParenthesis = 0;
    int leftParenthesis = 0;
    int rightBrace = 0;
    int leftBrace = 0;
    int rightBracket = 0;
    int leftBracket =0;
    for (int i = 0; i < expression.length(); i++){
      char c = expression.charAt(i);
      if (c == '('){
        leftParenthesis++;
      }
      else if (c == ')'){
        rightParenthesis++;
      }
      else if (c == '{'){
        leftBrace++;
      }
      else if (c == '}'){
        rightBrace++;
      }
      else if (c == '['){
        leftBracket++;
      } 
      else if (c == ']'){
        rightBracket++;
      }
    }
    return leftParenthesis == rightParenthesis && leftBrace == rightBrace && 
      leftBracket == rightBracket;
  }
  
  
  /** Main tester method. */
  public static void main(String[] args) {
    System.out.println(isBalanced("([3+5] * 4)")); // true
    System.out.println(isBalanced("((3+5)*(4+8)+(5*9)")); // false
    System.out.println(isBalanced("(3 + 5]")); // false
    System.out.println(isBalanced("(3 + 5 (8 - 4) - [2 *] - 5)")); // true
    System.out.println(isBalanced("((((())))))[[[{{{((()))}}}]]]"));// false
    System.out.println(isBalanced("3 + 5")); // true
    System.out.println(isBalanced("")); // true
  }
  
}
