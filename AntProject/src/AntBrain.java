
import States.*;

/**
 * This class represents an AntBrain in the ant game. It reads the input
 * ant brain file and holds the states described in that file. It can 
 * return a state based on its index.
 * 
 * @author mlg28
 * @version 16 March 2015
 */
public class AntBrain {
    
    private State[] states;     // An array holding all of the States 
                                //of the AntBrain.
    
    /**
     * Creates an empty AntBrain, which can be used to load and check the 
     * user provided ant brains.
     */
    public AntBrain(){
    }
    
    /**
     * Takes the file path of the user provided ant brain, reads the file 
     * and creates a State class for each line of the file.
     * 
     * @param path The file path to the user provided ant brain.
     */
    public void loadAntBrain(String path){
    }
    
    /**
     * Returns the State object based on the given index.
     * 
     * @param stateIndex The index of the State to return.
     * @return The State at the given index.
     */
    public State getState(int stateIndex){
        return null;
    }
    
    /**
     * Takes a String representing a Sense instruction and 
     * returns a new Sense (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The sense instruction from the ant brain file.
     * @return A Sense object representing the sense instruction.
     */
    private Sense checkSense(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing a Mark instruction and 
     * returns a new Mark (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The mark instruction from the ant brain file.
     * @return A Mark object representing the mark instruction.
     */
    private Mark checkMark(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing an Unmark instruction and 
     * returns a new Unmark (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The unmark instruction from the ant brain file.
     * @return An Unmark object representing the unmark instruction.
     */
    private Unmark checkUnmark(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing a PickUp instruction and 
     * returns a new Pickup (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The pickup instruction from the ant brain file.
     * @return A PickUp object representing the pickup instruction.
     */
    private PickUp checkPickUp(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing a Drop instruction and 
     * returns a new Drop (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The drop instruction from the ant brain file.
     * @return A Drop object representing the drop instruction.
     */
    private Drop checkDrop(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing a Turn instruction and 
     * returns a new Turn (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The turn instruction from the ant brain file.
     * @return A Turn object representing the turn instruction.
     */
    private Turn checkTurn(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing a Move instruction and 
     * returns a new Move (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The move instruction from the ant brain file.
     * @return A Move object representing the move instruction.
     */
    private Move checkMove(String instruction){
        return null;
    }
    
    /**
     * Takes a String representing a Flip instruction and 
     * returns a new Flip (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The flip instruction from the ant brain file.
     * @return A Flip object representing the flip instruction.
     */
    private Flip checkFlip(String instruction){
        return null;
    }
}
