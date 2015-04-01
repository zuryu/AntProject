
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * turn command.
 * 
 * @version 22 March 2015
 */
public class Turn extends State {
    
    private LeftOrRight leftOrRight;    // The direction in which to turn.
    private int state;                  // The state to go into after the turn.

    /**
     * Creates the Turn object with the given variables. 
     * Inherits from State.
     * 
     * @param leftOrRight The direction in which to turn.
     * @param state The state to go into after the turn.
     */
    public Turn(LeftOrRight leftOrRight, int state){
        this.leftOrRight = leftOrRight;
        this.state = state;
    }
    
    /**
     * Returns the direction in which to turn.
     * 
     * @return The direction in which to turn.
     */
    public LeftOrRight getLeftOrRight() {
        return leftOrRight;
    }

    /**
     * Returns the state to move into after the turn.
     * 
     * @return The state to move into after the turn. 
     */
    public int getState() {
        return state;
    }
}
