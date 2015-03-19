
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * flip command.
 * 
 * @author 118435
 * @version 19 March 2015
 */
public class Flip extends State {
    
    private int maxNumber;      // The maximum possible value of the random number generator.
    private int state1;         // The state to move into if the random number is equal to 0.
    private int state2;         // The state to move into if the random number is anything other than 0.

    /**
     * Returns the maximum possible value of the random number generator.
     * 
     * @return The maximum possible value of the random number generator. 
     */
    public int getMaxNumber() {
        return maxNumber;
    }

    /**
     * Returns the state to move into if the random number is equal to 0.
     * 
     * @return The state to move into if the random number is equal to 0.
     */
    public int getState1() {
        return state1;
    }

    /**
     * Returns the state to move into if the random number is 
     * anything other than 0.
     * 
     * @return The state to move into if the random number is 
     * anything other than 0.
     */
    public int getState2() {
        return state2;
    }
}
