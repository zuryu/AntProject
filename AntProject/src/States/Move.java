
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * move command.
 * 
 * @author 118435
 * @version 16 March 2015
 */
public class Move {
    
    private int state1;     // The state to move into if the move is successful.
    private int state2;     // The state to move into if the move fails.

    /**
     * Returns the state to move into if the move is successful.
     * 
     * @return The state to move into if the move is successful.
     */
    public int getState1() {
        return state1;
    }

    /**
     * Returns the state to move into if the move fails.
     * 
     * @return The state to move into if the move fails. 
     */
    public int getState2() {
        return state2;
    }
    
}
