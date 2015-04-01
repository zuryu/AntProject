
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * drop command.
 * 
 * @version 22 March 2015
 */
public class Drop extends State {
    
    private int state;      // The state to move onto after the drop.

    /**
     * Creates the Drop object with the given variables. 
     * Inherits from State.
     * 
     * @param state The state to move onto after the drop.
     */
    public Drop(int state){
        this.state = state;
    }
    
    /**
     * Returns the state to move onto after the drop.
     * 
     * @return The state to move onto after the drop.
     */
    public int getState() {
        return state;
    }
}
