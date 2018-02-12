
/** Program that sorts and prints an Olympic medal table.
  * 
  * @author Annie Tang
  */

import java.util.*;

public class OlympicResult implements Comparable<OlympicResult> {
 
    private String country; // code for the country
    private int numGold; // number of gold medals won
    private int numSilver; // number of silvers
    private int numBronze; // number of bronzes
    
    /** Constructs a new medal table entry given a country and its record.
      * 
      * @param country a three letter code denoting the country.
      * @param numGold number of gold medals won by that country.
      * @param numSilver number of silver medals won by that country.
      * @param numBronze number of bronze medals won by that country.
      */
    public OlympicResult(String country, int numGold, int numSilver, 
                          int numBronze) {
        this.country = country;
        this.numGold = numGold;
        this.numSilver = numSilver;
        this.numBronze = numBronze;
    }
    
    
    @Override
    public String toString() {
        return country + " " + numGold + " " + numSilver + " " + numBronze;
    }
    
    
    /** Returns the outcome of comparing this country's record against the
      * other country's.
      * 
      * When determining the natural ordering, the number of gold medals is
      * considered first. The country with more gold medals appears before
      * another with fewer golds. If the countries have the same number of
      * golds, then the country with more silvers appears first. If still tied,
      * then the country with more bronzes appears first. Finally, if the two
      * countries have the exact same number of golds, silvers and bronzes, then
      * the countries are listed in alphabetical order.
      * 
      * @param theEnemy the other country whose record against which we are
      * comparing this country.
      * 
      * @return A negative integer if this country will appear before the other
      * country in the sorted medal table, and a positive integer if not. Note
      * that this function never returns 0 as duplicate entries for a country
      * are assumed to not exist.
      */
    public int compareTo(OlympicResult theEnemy) {
      if (this.numGold != theEnemy.numGold){
        return theEnemy.numGold - this.numGold; 
      }
      if (this.numSilver != theEnemy.numSilver){
        return theEnemy.numSilver - this.numSilver;
      }
      if (this.numBronze != theEnemy.numBronze){
        return theEnemy.numBronze - this.numBronze;
      }
      return this.country.compareTo(theEnemy.country);
    }
    
    
    /** Tester method. */
    public static void main(String[] args) {
        OlympicResult[] medalTable = new OlympicResult[5];
        
        medalTable[0] = new OlympicResult("ITA", 1, 0, 0);
        medalTable[1] = new OlympicResult("JPN", 0, 1, 0);
        medalTable[2] = new OlympicResult("AUS", 0, 0, 1);
        medalTable[3] = new OlympicResult("KOR", 3, 1, 0);
        medalTable[4] = new OlympicResult("UKR", 0, 0, 1);
        
        Arrays.sort(medalTable);
        
        // Output should be:
        // [KOR 3 1 0, ITA 1 0 0, JPN 0 1 0, AUS 0 0 1, UKR 0 0 1]
        System.out.println(Arrays.toString(medalTable));
        
        // Results from Sochi 2014, alpine skiing
        medalTable = new OlympicResult[10];        
        medalTable[0] = new OlympicResult("AUT", 3, 4, 2);
        medalTable[1] = new OlympicResult("CAN", 0, 0, 1); 
        medalTable[2] = new OlympicResult("CRO", 0, 1, 0); 
        medalTable[3] = new OlympicResult("FRA", 0, 1, 1); 
        medalTable[4] = new OlympicResult("GER", 1, 1, 1); 
        medalTable[5] = new OlympicResult("ITA", 0, 1, 1); 
        medalTable[6] = new OlympicResult("NOR", 1, 0, 2); 
        medalTable[7] = new OlympicResult("SLO", 2, 0, 0); 
        medalTable[8] = new OlympicResult("SUI", 2, 0, 1); 
        medalTable[9] = new OlympicResult("USA", 2, 1, 2); 
        
        Arrays.sort(medalTable);
        
        // Output should be:
        // [AUT 3 4 2, USA 2 1 2, SUI 2 0 1, SLO 2 0 0, GER 1 1 1, NOR 1 0 2, 
        //  FRA 0 1 1, ITA 0 1 1, CRO 0 1 0, CAN 0 0 1]
        System.out.println(Arrays.toString(medalTable));
    }
    
}