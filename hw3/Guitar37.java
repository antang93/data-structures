/** An implementation of a 37-string Guitar.
  * 
  * Time spent: 2 hours
  * 
  * @author Annie Tang
  */

public class Guitar37 implements Guitar {

  // keyboard layout
  public static final String KEYBOARD = 
    "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
  
  private GuitarString[] string37;
  public static final double CONCERT_A = 440.0;
  public static final int NUM_STRING = 37;
  
  /**Create an array of 37 guitar strings for the 37 keys.
   */
  public Guitar37() {
    this.string37 = new GuitarString[NUM_STRING];
    for (int i = 0; i < NUM_STRING - 1; i++){
      double concert = CONCERT_A * Math.pow(2, (i-24)/12.0);
      this.string37[i] = new GuitarString(concert);
    }
  }
  
  /** Returns true if specified key is one of the 37 keys.
    * 
    * @return true if it is one of the 37 keys
   */
  public boolean hasString(char string){
    return KEYBOARD.contains(Character.toString(string));
  }
  
  /** Simulates plucking a string.
    * 
    * @throws IllegalArgumentException if key pressed is not one of the 37 keys
    */
  public void pluck(char string) {
    if (hasString(string) == false){
      throw new IllegalArgumentException("Invalid key!");
    }
    this.string37[KEYBOARD.indexOf(string)].pluck();
  }
  
  /** Plays the 37 keys.
   */
  public void play() {
    // compute the superposition of samples
    double sample = 0.0;
    for (int i = 0; i < NUM_STRING - 1; i++){
      sample = sample + this.string37[i].sample();
    }
    // send the result to the sound card
    StdAudio.play(sample);
  }
  
  /** Applies the Karplus-Strong update.
   */
  public void tic() {
    for (int i = 0; i < NUM_STRING - 1; i++){
      this.string37[i].tic();
    }
  }
}
