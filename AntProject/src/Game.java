
import States.LeftOrRight;
import java.util.HashMap;
import States.*;

/**
 * This class represents the ant game. It ties all of the other classes together 
 * and handles the score etc.
 * 
 * @author 118435
 * @version 18 March 2015
 */
public class Game {
    
    private AntBrain red;                       // The AntBrain for the red ants.
    private AntBrain black;                     // The AntBrain for the black ants.
    private World world;                        // The world.
    private HashMap<Integer, Position> ants;    // Holds the Position of every Ant in the world with its id as the key.
    
    /**
     * Starts the ant game.
     * 
     * @param args Command-line arguments; none required. 
     */
    public static void main(String[] args){
        new Game();
    }
    
    /**
     * Creates the game object and starts a game.
     */
    public Game(){
    }
    
    /**
     * Returns the instruction at the given State for the given color of ant.
     * 
     * @param color The color of the ant using the instruction.
     * @param state The instruction to retrieve.
     * @return The instruction at the given State for the given color of ant.
     */
    public State get_instruction(Color color, int state){
        return null;
    }
    
    /**
     * Loads the given world and ant brain files and starts a new game.
     * 
     * @param world The path to the world file.
     * @param ant1 The path to the first ant brain file.
     * @param ant2 The path to the second ant brain file.
     */
    public void newGame(String world, String ant1, String ant2){
    }
    
    /**
     * Returns a pseudo-random integer between 1 and n - 1. 
     * 
     * @param n The maximum number to be returned plus 1.
     * @return A pseudo-random integer between 1 and n - 1. 
     */
    public int randomint(int n){
        return 0;
    }
    
    /**
     * Take the turn of the ant with the given id.
     * 
     * @param id The ID of the ant to take the turn for.
     */
    public void step(int id){
    }
    
    /**
     * Returns true if the Ant with the given id is alive; false otherwise.
     * 
     * @param id The ID of the ant to check.
     * @return True if the Ant with the given id is alive; false otherwise.
     */
    public boolean ant_is_alive(int id){
        return true;
    }
    
    /**
     * Returns the Position of the Ant with the given id.
     * 
     * @param id The ID of the ant to find.
     * @return The Position of the Ant with the given id.
     */
    public Position find_ant(int id){
        return null;
    }
    
    /**
     * Returns the direction after the given turn.
     * 
     * @param leftOrRight The direction in which to turn.
     * @param direction The current direction that is being faced.
     * @return The direction after the given turn.
     */
    public int turn(LeftOrRight leftOrRight, int direction){
        return 0;
    }
}
