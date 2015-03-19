
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * unmark command.
 * 
 * @author 118435
 * @version 19 March 2015
 */
public class Unmark extends State {
    
    private int marker;         // The marker to remove.
    private int state;          // The state to move onto next.

    /**
     * Creates the Unmark object with the given variables. 
     * Inherits from State.
     * 
     * @param marker The marker to remove.
     * @param state The state to move onto next.
     */
    public Unmark(int marker, int state){
    }
    
    /**
     * Returns the marker to be removed.
     * 
     * @return The marker to be removed.
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
