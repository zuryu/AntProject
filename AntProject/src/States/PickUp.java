
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * pickup command.
 * 
 * @version 22 March 2015
 */
public class PickUp extends State {
    
    private int state1;     // The state to move on to if the pick up is successful.
    private int state2;     // The state to move on to if the pick up fails.

    /**
     * Creates the PickUp object with the given variables. 
     * Inherits from State.
     * 
     * @param state1 The state to move on to if the pick up is successful.
     * @param state2 The state to move on to if the pick up fails. 
     */
    public PickUp(int state1, int state2){
        this.state1 = state1;
        this.state2 = state2;
    }
    
    /**
     * Returns the state to move on to if the pick up is successful.
     * 
     * @return The state to move on to if the pick up is successful.
     */
    public int getState1() {
        return state1;
    }

    /**
     * Returns the state to move on to if the pick up fails.
     * 
     * @return The state to move on to if the pick up fails.
     */
    public int getState2() {
        return state2;
    }    
}
