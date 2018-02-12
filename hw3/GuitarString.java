/** An implementation of a vibrating guitar string.
  *
  * Time spent: 3 hours
  * 
  * @author Annie Tang
  */

import java.util.*;

public class GuitarString {
  
  private Queue<Double> guitarString;
  private int n;
  public final static double ENERGY_DECAY_FACTOR = 0.996 ;
  public int count = 0;
  
  /** Constructs a guitar string of given frequency.
    * 
    * @param frequency the frequency 
    * @throws IllegalArgumentException if frequency is nonpositive or if ring 
    * buffer is less than 2
    */
  public GuitarString(double frequency){
    this.n =  (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
    if (frequency <= 0){
      throw new IllegalArgumentException("frequency must be positive!");
    }
    else if (n < 2){
      throw new IllegalArgumentException("ring buffer must be at least 2!");
    }
    this.guitarString = new LinkedList<Double>();
    for (int i = 0; i < this.n; i++){
      this.guitarString.add(0.0);
    }
  }
  
  /** Constructs a guitar string with values from given array.
    * 
    * @throws IllegalArgumentException if initial array < 2
   */
  public GuitarString(double[] init){
    if (init.length < 2){
      throw new IllegalArgumentException("initial array must have at least"  
                                           + "2 elements");
    }
    this.guitarString  = new LinkedList<Double>();
    for (int i = 0; i < init.length; i++){
      this.guitarString.add(init[i]);
    }
  }
  
  /** Replaces elements in the ring buffer with random values to simulate a 
    * guitar string.
    * 
   */
  public void pluck(){
    for (int i = 0; i < this.n; i++){
      this.guitarString.remove();
      this.guitarString.add(Math.random() - 0.5);
    }
  }
  
  /** Applies the Karplus-Strong update once.
    */
  public void tic(){
    double firstSample = this.guitarString.peek();
    this.guitarString.remove();
    double secondSample = this.guitarString.peek();
    double average = (firstSample + secondSample)/2;
    this.guitarString.add(average*ENERGY_DECAY_FACTOR);
    this.count++;
  }

  /** Returns the current sample.
    * 
    * @return current sample
    */
  public double sample(){
    return this.guitarString.peek();
  }
  
  /** Returns the number of times the tic method has been called.
    * 
    * @return method tic called times
    */
  public int time(){
    return this.count;
  }
}