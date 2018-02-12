
import java.util.*;

/** Program that decodes a message in Morse, given the code book.
  * 
  * @author Annie Tang
  */
public class Morse {

    /** Returns the decrypted version of message based on code.
      * 
      * @param code the code book mapping Morse sequences to letters.
      * @param message the secret message to be decoded.
      * @return the decrypted message.
      */
    public static String decode(Map<String, String> code, String message) {
      String decryptedMessage = "";
      String[] words = message.split(" ");
      for (String i : words){
        if (!code.containsKey(i)){
          decryptedMessage += "?";
        }
        else{
          decryptedMessage += code.get(i);
        }
      }
      return decryptedMessage;
    }
    
    /** Tester method */
    public static void main(String[] args) {
        
        // Example 1
        Map<String, String> code1 = new HashMap<String, String>();
        code1.put("---", "O");
        code1.put("...", "S");
        System.out.println(decode(code1, "... --- ...")); // should print SOS
        
        // Example 2
        Map<String, String> code2 = new HashMap<String, String>();
        code2.put("---", "O");
        System.out.println(decode(code2, "... --- ...")); // should print ?O?
        
        // Example 3
        Map<String, String> code3 = new HashMap<String, String>();
        code3.put("--", "A");
        code3.put("-.", "B");
        code3.put("...-", "N");
        code3.put("--..", "I");
        code3.put("-.-.-.", "F");
        // should print BAFF?IN?
        System.out.println(decode(code3, 
                                  "-. -- -.-.-. -.-.-. --- --.. ...- .-..--"));

        // Feel free to add more test cases here   
    }
}