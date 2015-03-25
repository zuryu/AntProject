
import java.util.HashMap;
import States.*;
import java.util.Random;

/**
 * This class represents the ant game. It ties all of the other classes together 
 * and handles the score etc.
 * 
 * @author 118435
 * @version 23 March 2015
 */
public class Game {
    
    private AntBrain red;                       // The AntBrain for the red ants.
    private AntBrain black;                     // The AntBrain for the black ants.
    private World world;                        // The world.
    private HashMap<Integer, Position> ants;    // Holds the Position of every Ant in the world with its id as the key.
    
    private Random rand;
    
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
        rand = new Random();
    }
    
    /**
     * Creates the game object and loads the given world and ant brains.
     * 
     * @param world The path to the world file.
     * @param ant1 The path to the first ant brain.
     * @param ant2 The path to the second ant brain.
     */
    public Game(String world, String ant1, String ant2){
    }
    
    /**
     * Returns the instruction at the given State for the given color of ant.
     * 
     * @param color The color of the ant using the instruction.
     * @param state The instruction to retrieve.
     * @return The instruction at the given State for the given color of ant.
     */
    public State get_instruction(Color color, int state){
        if (color == Color.Black){
            return black.getState(state);
        } else {
            return red.getState(state);
        }
    }
    
    /**
     * Loads the given world and ant brain files and starts a new game.
     * 
     * @param world The path to the world file.
     * @param ant1 The path to the first ant brain file.
     * @param ant2 The path to the second ant brain file.
     */
    public void newGame(String world, String ant1, String ant2){
        this.world = new World();
        this.world.loadWorld(world);
        red = new AntBrain(ant1);
        black = new AntBrain(ant2);
    }
    
    /**
     * Returns a pseudo-random integer between 1 and n - 1. 
     * 
     * @param n The maximum number to be returned plus 1.
     * @return A pseudo-random integer between 1 and n - 1. 
     */
    public int randomint(int n){
        return rand.nextInt(n);
    }
    
    /**
     * Take the turn of the ant with the given id.
     * 
     * @param id The ID of the ant to take the turn for.
     */
    public void step(int id){
        if (ant_is_alive(id)){
            Position p = find_ant(id);
            Ant a = world.ant_at(p);
            if (a.getResting() > 0){
                a.setResting(a.getResting() - 1);
            } else {
                State state = get_instruction(a.getColor(), a.getState());
                switch (state.getClass().getName()){
                    case "Sense":
                        Sense sense = (Sense)state;
                        Position pos = world.sensed_cell(p, a.getDirection(), sense.getDirection());
                        int newState;
                        if (world.cell_matches(pos, sense.getCondition(), a.getColor())){
                            newState = sense.getState1();
                        } else {
                            newState = sense.getState2();
                        }
                        a.setState(newState);
                        break;
                    case "Mark":
                        Mark mark = (Mark)state;
                        world.set_marker_at(p, a.getColor(), mark.getMarker());
                        a.setState(mark.getState());
                        break;
                    case "Unmark":
                        Unmark unmark = (Unmark)state;
                        world.clear_marker_at(p, a.getColor(), unmark.getMarker());
                        a.setState(unmark.getState());
                        break;
                    case "PickUp":
                        PickUp pickUp = (PickUp)state;
                        if (a.isHasFood() || world.food_at(p) == 0){
                            a.setState(pickUp.getState2());
                        } else {
                            world.set_food_at(p, world.food_at(p) - 1);
                            a.setHasFood(true);
                            a.setState(pickUp.getState1());
                        }
                        break;
                    case "Drop":
                        Drop drop = (Drop)state;
                        if (a.isHasFood()){
                            world.set_food_at(p, world.food_at(p) + 1);
                            a.setHasFood(false);
                        }
                        a.setState(drop.getState());
                        break;
                    case "Turn":
                        Turn turn = (Turn)state;
                        a.setDirection(world.turn(turn.getLeftOrRight(), a.getDirection()));
                        a.setState(turn.getState());
                        break;
                    case "Move":
                        Move move = (Move)state;
                        Position newPos = world.adjacent_cell(p, a.getDirection());
                        if (world.rocky(newPos) || world.some_ant_is_at(newPos)){
                            a.setState(move.getState2());
                        } else {
                            world.clear_ant_at(p);
                            world.set_ant_at(newPos, a);
                            a.setState(move.getState1());
                            a.setResting(14);
                            check_for_surrounded_ants(newPos);
                        }
                        break;
                    case "Flip":
                        Flip flip = (Flip)state;
                        int st;
                        if (randomint(flip.getMaxNumber()) == 0){
                            st = flip.getState1();
                        } else {
                            st = flip.getState2();
                        }
                        a.setState(st);
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal state in step : Ant: " + a.getId());
                }
            }
        }
    }
    
    /**
     * Returns true if the Ant with the given id is alive; false otherwise.
     * 
     * @param id The ID of the ant to check.
     * @return True if the Ant with the given id is alive; false otherwise.
     */
    public boolean ant_is_alive(int id){
        return ants.containsKey(id);
    }
    
    /**
     * Returns the Position of the Ant with the given id.
     * 
     * @param id The ID of the ant to find.
     * @return The Position of the Ant with the given id.
     */
    public Position find_ant(int id){
        return ants.get(id);
    }
    
    /**
     * Kills (removes) the ant from the given position.
     * 
     * @param p The Position to kill the ant at. 
     */
    public void kill_ant_at(Position p){
        if (world.some_ant_is_at(p)){
            int id = world.ant_at(p).getId();
            world.clear_ant_at(p);
            ants.remove(id);
        }
    }
    
    /**
     * Checks if the ant at the given position is surrounded and kills it if it is.
     * 
     * @param p The Position to check for an ant.
     */
    public void check_for_surrounded_ant_at(Position p){
        if (world.some_ant_is_at(p)){
            Ant a = world.ant_at(p);
            if (world.adjacent_ants(p, world.other_color(a.getColor())) >= 5){
                kill_ant_at(p);
                int foodHeld = 0;
                if (a.isHasFood()){
                    foodHeld = 1;
                }
                world.set_food_at(p, world.food_at(p) + 3 + foodHeld);
            }
        }
    }
    
    /**
     * Checks if the ant at the given position is surrounded or checks if it has now surrounded another ant.
     * 
     * @param p The Position to check for an ant.
     */
    public void check_for_surrounded_ants(Position p){
        check_for_surrounded_ant_at(p);
        for (int i = 0; i < 6; i++){
            check_for_surrounded_ant_at(world.adjacent_cell(p, i));
        }
    }
    
    /**
     * Returns the world of the ant game; for testing only.
     * 
     * @return The world of the ant game.
     */
    public World getWorld(){
        return world;
    }
}
