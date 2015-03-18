
package States;

/**
 * This class represents the State of an ant brain when it is using the 
 * sense command.
 * 
 * @author 118435
 * @version 16 March 2015
 */
public class Sense extends State {
    
    private SenseDirection direction;   // The direction in which to sense.
    private int state1;                 // The state to go into if the condition is true.
    private int state2;                 // The state to go into if the condition is false.
    private Condition condition;        // The condition to check for in the given direction.

    /**
     * Creates the Sense object with the given variables. 
     * Inherits from State.
     * 
     * @param direction The direction in which to sense.
     * @param state1 The state to go into if the condition is true.
     * @param state2 The state to go into if the condition is false.
     * @param condition The condition to check for in the given direction.
     */
    public Sense(SenseDirection direction, int state1, int state2, Condition condition){
    }
    
    /**
     * Returns the direction in which the ant is sensing.
     * 
     * @return The direction in which the ant is sensing.
     */
    public SenseDirection getDirection() {
        return direction;
    }

    /**
     * Returns the state to go into if the condition is true.
     * 
     * @return The state to go into if the condition is true.
     */
    public int getState1() {
        return state1;
    }

    /**
     * Returns the state to go into if the condition is false.
     * 
     * @return The state to go into if the condition is false. 
     */
    public int getState2() {
        return state2;
    }

    /**
     * Returns the condition that is being checked.
     * 
     * @return The condition that is being checked. 
     */
    public Condition getCondition() {
        return condition;
    }   
}
