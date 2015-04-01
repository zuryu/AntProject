
import States.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents an AntBrain in the ant game. It reads the input
 * ant brain file and holds the states described in that file. It can 
 * return a state based on its index.
 * 
 * @version 1 April 2015
 */
public class AntBrain {
    
    private ArrayList<State> states;     // An arraylist holding all of the States of the AntBrain.
    
    /**
     * Creates an empty AntBrain, which can be used to load and check the 
     * user provided ant brains.
     * 
     * @param path The file path to the user provided ant brain.
     */
    public AntBrain(String path){
        states = new ArrayList<>();
        loadAntBrain(path);
    }
    
    /**
     * Takes the file path of the user provided ant brain, reads the file 
     * and creates a State class for each line of the file.
     * 
     * @param path The file path to the user provided ant brain.
     */
    public final void loadAntBrain(String path) throws IllegalArgumentException {
        try{
            states = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(path)); 
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null){
                State state = checkState(line);
                i++;
                if (i > 9999){
                    throw new IllegalArgumentException("Too many lines in Ant Brain.");
                }
                if (state != null){
                    states.add(state);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        }
        antBrainCheck();
    }
    
    /**
     * Checks what type of state the line of text describes and returns the 
     * correct State object.
     * 
     * @param line The string of text describing the State.
     * @return The State object described by the given string.
     */
    private State checkState(String line) throws IllegalArgumentException {
        
        ArrayList<String> words = splitWords(line);

        if (words.size() <= 0){
            return null;
        }
        
        switch (words.get(0)){
            case "Sense":
                return checkSense(words);
            case "Mark":
                return checkMark(words);
            case "Unmark":
                return checkUnmark(words);
            case "PickUp":
                return checkPickUp(words);
            case "Drop":
                return checkDrop(words);
            case "Turn":
                return checkTurn(words);
            case "Move":
                return checkMove(words);
            case "Flip":
                return checkFlip(words);
            default:
                throw new IllegalArgumentException("Unrecognised state in AntBrain.");
        }
    }
    
    /**
     * Splits a String by the whitespace.
     * 
     * @param line The String to split.
     * @return An arrayList holding all the words in the String.
     */
    private ArrayList<String> splitWords(String line){
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        for (char letter : line.toCharArray()){
            if (Character.isLetterOrDigit(letter)){
                word = word.concat(Character.toString(letter));
            } else {
                if (!word.isEmpty()){
                    words.add(word);
                }
                word = "";
            }
        }
        
        if (!word.isEmpty()){
            words.add(word);
        }
        
        return words;
    }
    
    /**
     * Returns the State object based on the given index.
     * 
     * @param stateIndex The index of the State to return.
     * @return The State at the given index.
     */
    public State getState(int stateIndex){
        return states.get(stateIndex);
    }
    
    /**
     * Takes a String representing a Sense instruction and 
     * returns a new Sense (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The sense instruction from the ant brain file.
     * @return A Sense object representing the sense instruction.
     */
    private Sense checkSense(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 5){
            throw new IllegalArgumentException("Not enough arguments for Sense instruction.");
        }
        
        if (words.get(4).equals("Marker") && words.size() < 6){
            throw new IllegalArgumentException("Marker index not given in Sense instruction.");
        }
        
        SenseDirection direction;
        Condition cond;
        int state1;
        int state2;
        switch (words.get(1)){
            case "Here":
                direction = SenseDirection.Here;
                break;
            case "Ahead":
                direction = SenseDirection.Ahead;
                break;
            case "LeftAhead":
                direction = SenseDirection.LeftAhead;
                break;
            case "RightAhead":
                direction = SenseDirection.RightAhead;
                break;
            default:
                throw new IllegalArgumentException("Illegal SenseDirection in Sense instruction.");
        }
        
        switch (words.get(4)){
            case "Friend":
                cond = Condition.Friend;
                break;
            case "Foe":
                cond = Condition.Foe;
                break;
            case "FriendWithFood":
                cond = Condition.FriendWithFood;
                break;
            case "FoeWithFood":
                cond = Condition.FoeWithFood;
                break;
            case "Food":
                cond = Condition.Food;
                break;
            case "Rock":
                cond = Condition.Rock;
                break;
            case "Marker":
                switch (words.get(5)){
                    case "0":
                        cond = Condition.Marker0;
                        break;
                    case "1":
                        cond = Condition.Marker1;
                        break;
                    case "2":
                        cond = Condition.Marker2;
                        break;
                    case "3":
                        cond = Condition.Marker3;
                        break;
                    case "4":
                        cond = Condition.Marker4;
                        break;
                    case "5":
                        cond = Condition.Marker5;
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal marker index in sense instruction.");
                }
                break;
            case "FoeMarker":
                cond = Condition.FoeMarker;
                break;
            case "Home":
                cond = Condition.Home;
                break;
            case "FoeHome":
                cond = Condition.FoeHome;
                break;
            default:
                throw new IllegalArgumentException("Illegal condition in sense instruction.");
        }
        
        state1 = Integer.parseInt(words.get(2));
        state2 = Integer.parseInt(words.get(3));
        
        return new Sense(direction, state1, state2, cond);
    }
    
    /**
     * Takes a String representing a Mark instruction and 
     * returns a new Mark (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The mark instruction from the ant brain file.
     * @return A Mark object representing the mark instruction.
     */
    private Mark checkMark(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 3){
            throw new IllegalArgumentException("Not enough arguments for Mark instruction.");
        }
        
        int marker = Integer.parseInt(words.get(1));
        int state = Integer.parseInt(words.get(2));
        
        return new Mark(marker, state);
    }
    
    /**
     * Takes a String representing an Unmark instruction and 
     * returns a new Unmark (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The unmark instruction from the ant brain file.
     * @return An Unmark object representing the unmark instruction.
     */
    private Unmark checkUnmark(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 3){
            throw new IllegalArgumentException("Not enough arguments for Unmark instruction.");
        }
        
        int marker = Integer.parseInt(words.get(1));
        int state = Integer.parseInt(words.get(2));
        
        return new Unmark(marker, state);
    }
    
    /**
     * Takes a String representing a PickUp instruction and 
     * returns a new Pickup (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The pickup instruction from the ant brain file.
     * @return A PickUp object representing the pickup instruction.
     */
    private PickUp checkPickUp(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 3){
            throw new IllegalArgumentException("Not enough arguments for PickUp instruction.");
        }
        
        int state1 = Integer.parseInt(words.get(1));
        int state2 = Integer.parseInt(words.get(2));
        
        return new PickUp(state1, state2);
    }
    
    /**
     * Takes a String representing a Drop instruction and 
     * returns a new Drop (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The drop instruction from the ant brain file.
     * @return A Drop object representing the drop instruction.
     */
    private Drop checkDrop(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 2){
            throw new IllegalArgumentException("Not enough arguments for Drop instruction.");
        }
        
        int state = Integer.parseInt(words.get(1));
        
        return new Drop(state);
    }
    
    /**
     * Takes a String representing a Turn instruction and 
     * returns a new Turn (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The turn instruction from the ant brain file.
     * @return A Turn object representing the turn instruction.
     */
    private Turn checkTurn(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 3){
            throw new IllegalArgumentException("Not enough arguments for Turn instruction.");
        }
        
        LeftOrRight leftOrRight;
        switch (words.get(1)){
            case "Left":
                leftOrRight = LeftOrRight.Left;
                break;
            case "Right":
                leftOrRight = LeftOrRight.Right;
                break;
            default:
                throw new IllegalArgumentException("Illegal LeftOrRight in Turn instruction.");
        }
        int state = Integer.parseInt(words.get(2));
        
        return new Turn(leftOrRight, state);
    }
    
    /**
     * Takes a String representing a Move instruction and 
     * returns a new Move (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The move instruction from the ant brain file.
     * @return A Move object representing the move instruction.
     */
    private Move checkMove(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 3){
            throw new IllegalArgumentException("Not enough arguments for Move instruction.");
        }
        
        int state1 = Integer.parseInt(words.get(1));
        int state2 = Integer.parseInt(words.get(2));
        
        return new Move(state1, state2);
    }
    
    /**
     * Takes a String representing a Flip instruction and 
     * returns a new Flip (State) object if the instruction is 
     * well formed.
     * 
     * @param instruction The flip instruction from the ant brain file.
     * @return A Flip object representing the flip instruction.
     */
    private Flip checkFlip(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() < 4){
            throw new IllegalArgumentException("Not enough arguments for Flip instruction.");
        }
        
        int maxNumber = Integer.parseInt(words.get(1));
        int state1 = Integer.parseInt(words.get(2));
        int state2 = Integer.parseInt(words.get(3));
        
        return new Flip(maxNumber, state1, state2);
    }
    
    private void antBrainCheck() throws IllegalArgumentException {
        for (State state : states){
            switch (state.getClass().getName()){
                case "States.Sense":
                    Sense sense = (Sense)state;
                    if (sense.getState1() >= states.size() || sense.getState2() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain sense.");
                    }
                    break;
                case "States.Mark":
                    Mark mark = (Mark)state;
                    if (mark.getState() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain mark.");
                    }
                    break;
                case "States.Unmark":
                    Unmark unmark = (Unmark)state;
                    if (unmark.getState() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain unmark.");
                    }
                    break;
                case "States.PickUp":
                    PickUp pickUp = (PickUp)state;
                    if (pickUp.getState1() >= states.size() || pickUp.getState2() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain pickup.");
                    }                        
                    break;
                case "States.Drop":
                    Drop drop = (Drop)state;
                    if (drop.getState() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain drop.");
                    }
                    break;
                case "States.Turn":
                    Turn turn = (Turn)state;
                    if (turn.getState() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain turn.");
                    }
                    break;
                case "States.Move":
                    Move move = (Move)state;
                    if (move.getState1() >= states.size() || move.getState2() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain move.");
                    }
                    break;
                case "States.Flip":
                    Flip flip = (Flip)state;
                    if (flip.getState1() >= states.size() || flip.getState2() >= states.size()){
                        throw new IllegalArgumentException("Illegal state in AntBrain flip.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Illegal state in AntBrain");
            }           
        }
    }
}
