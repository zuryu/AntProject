
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
 * @author 118435
 * @version 18 March 2015
 */
public class AntBrain {
    
    private ArrayList<State> states;     // An array holding all of the States of the AntBrain.
    
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
    public final void loadAntBrain(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path)); 
            String line;
            while ((line = reader.readLine()) != null){
                checkState(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Checks what type of state the line of text describes and returns the 
     * correct State object.
     * 
     * @param line The string of text describing the State.
     * @return The State object described by the given string.
     */
    private State checkState(String line){
        
        ArrayList<String> words = splitWords(line);

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
     * 
     * @param line
     * @return 
     */
    public ArrayList<String> splitWords(String line){
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
    private Sense checkSense(ArrayList<String> words){
        if (instruction.length < 5){
            throw new IllegalArgumentException("Not enough arguments for Sense instruction.");
        }
        
        if (instruction[4].equals("Marker") && instruction.length < 6){
            throw new IllegalArgumentException("Marker index not given in Sense instruction or too many arguments given.");
        }
        
        SenseDirection direction;
        Condition cond;
        int state1;
        int state2;
        switch (instruction[1]){
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
        
        switch (instruction[4]){
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
                switch (instruction[5]){
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
        
        state1 = Integer.parseInt(instruction[2]);
        state2 = Integer.parseInt(instruction[3]);
        
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
    private Mark checkMark(ArrayList<String> words){
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
    private Unmark checkUnmark(ArrayList<String> words){
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
    private PickUp checkPickUp(ArrayList<String> words){
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
    private Drop checkDrop(ArrayList<String> words){
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
    private Turn checkTurn(ArrayList<String> words){
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
    private Move checkMove(ArrayList<String> words){
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
    private Flip checkFlip(ArrayList<String> words){
        return null;
    }
}
