
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * mark command.
 * 
 * @author 118435
 * @version 19 March 2015
 */
public class Mark extends State {
    
    private int marker;         // The marker to put down.
    private int state;          // The state to move onto next.

    /**
     * Creates the Mark object with the given variables. 
     * Inherits from State.
     * 
     * @param marker The marker to put down.
     * @param state The state to move onto next.
     */
    public Mark(int marker, int state){
    }
    
    /**
     * Returns the marker to be placed.
     * 
     * @return The marker to be placed.
     */
    public int getMarker() {
        return marker;
    }

    /**
     * Returns the state to move on to.
     * 
     * @return The state to move on to.
     */
    public int getState() {
        return state;
    }
}
