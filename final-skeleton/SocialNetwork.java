
import java.util.*;

/** Program that determines the most connected person in a social network.
  * 
  * @author Annie Tang
  */
public class SocialNetwork {
    
    /** Returns the number of 2-friends of the most connected person in the
      * given social network.
      * 
      * Person A is the 2-friend of B if they are both friends, or if there is
      * a person C who is friends with both A and B.
      * 
      * @param friends an adjacency matrix representation of the social network
      * @return an integer representing the number of 2-friends of the most
      * connected person in the network.
      */
    public static int mostConnected(String[] friends) {
      int max = 0;
      for (int i = 0; i < friends.length; i++){
        if (connections(friends, i) > max){
          max = connections(friends,i);
        }
      }
      return max;
    }
   
    /** Private static helper method to evaluate if two people are 2-friends.
      * 
      * @param friends an adjacency matrix representation of the social network.
      * @param person1 the first person of the two.
      * @param person2 the second person of the two.
      * @return true if the two people are 2-friends.
      */
    private static boolean twoFriend(String[] friends, int person1, int person2){
      if (friends[person1].charAt(person2) == 'Y'){
        return true;
      }
      for (int i = 0; i < friends.length; i++){
        if (friends[person1].charAt(i) == 'Y' && friends[person2].charAt(i) == 'Y'){
          return true;
        }
      }
      return false;
    }
    
    /** Private static helper method to count the number of 2-friends a given 
      * person has.
      * 
      * @param friends an adjacency matrix representation of the social network.
      * @param person the person whose number of 2-friends we want.
      * @return the number of 2-friends of a given person.
      */
    private static int connections(String[] friends, int person){
      int connections = 0;
      for (int i = 0; i < friends.length; i++){
        if (twoFriend(friends, i, person) && person != i){
          connections++;
        }
      }
      return connections;
    }
    
    /** Main tester method. */
    public static void main(String[] args) {
        String[] friends1 = 
           {"NNN",
            "NNN",
            "NNN"};
        
        String[] friends2 = 
           {"NYY",
            "YNY",
            "YYN"};
        
        String[] friends3 = 
           {"NYNNN",
            "YNYNN", 
            "NYNYN", 
            "NNYNY", 
            "NNNYN"};
        
        String[] friends4 = 
           {"NNNNYNNNNN",
            "NNNNYNYYNN",
            "NNNYYYNNNN",
            "NNYNNNNNNN",
            "YYYNNNNNNY",
            "NNYNNNNNYN",
            "NYNNNNNYNN",
            "NYNNNNYNNN",
            "NNNNNYNNNN",
            "NNNNYNNNNN"};
        
        System.out.println(mostConnected(friends1)); // 0
        System.out.println(mostConnected(friends2)); // 2
        System.out.println(mostConnected(friends3)); // 4
        System.out.println(mostConnected(friends4)); // 8
    }
}
