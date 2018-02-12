

import java.util.*;

/** Program that determines the winner in a tournament, given the results.
  * 
  * @author Annie Tang
  */
public class Bracket {
    
    /** Returns the name of the winner in the given bracket, based on results.
      * 
      * @param bracket the list of teams competing, top to bottom.
      * @param results a string containing the sequence of results for the
      * entire tournament. An 'H' denotes the higher team won, and an 'L'
      * denotes that the lower team won.
      * @return the name of the overall winner of the tournament.
      */
    public static String winner(String[] bracket, String results) {
      Queue<String> queueTeams = new LinkedList<String>();
      Queue<Integer> queueResults = new LinkedList<Integer>();
      
      for (int i = 0; i < results.length(); i++){
        if (results.charAt(i) == 'H'){
          queueResults.add(0);
        }
        else if (results.charAt(i) == 'L'){
          queueResults.add(1);
        }   
      }  

      while (!queueResults.isEmpty()){  
        for (int i = 0; i < bracket.length; i+=2){
          queueTeams.clear();
          if (bracket[i] == "bye"){
            queueTeams.add(bracket[i+1]);
          }
          else if (bracket[i+1] == "bye"){
            queueTeams.add(bracket[i]);
          }
          else {
            if (queueResults.peek().equals(0)){
              queueTeams.add(bracket[i]);
              queueResults.remove();
            }
            else if (queueResults.peek().equals(1)) {
              queueTeams.add(bracket[i+1]);
              queueResults.remove();
            }
          }
        }
      }
      return queueTeams.peek(); 
    }
          
    
    /** Tester method. */
      public static void main(String[] args) {
        
        String[] bracket1 = {"DUKE","UCLA","bye","MIT"};
        String[] bracket2 = {"A","B","C","bye","D","E","F","bye"};
        String[] bracket4 = {"STANFORD", "bye", "STANFORD", "bye"};

        System.out.println(winner(bracket1, "HL")); // should print MIT
        System.out.println(winner(bracket2, "LHHLH")); // should print B
        System.out.println(winner(bracket4, "L")); // should print STANFORD
        
        // Feel free to add more test cases here
    }
    
}

