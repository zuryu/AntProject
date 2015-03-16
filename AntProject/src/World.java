
/**
 * This class represents the game world of the ant game. A world is a 
 * hexagonal grid of 150 by 150.
 * 
 * @author mlg28
 * @version 16 March 2015
 */
public class World {
    
    /**
     * Creates an empty World, which can be used to load and 
     * check the world files. 
     */
    public World(){
    }
    
    /**
     * Returns the position of the cell adjacent to the given one 
     * in the given direction.
     * 
     * @param p The position to check from.
     * @param direction The direction in which to get the adjacent cell.
     * @return The cell adjacent to the given cell in the given direction.
     */
    public Position adjacent_cell(Position p, int direction){
        return null;
    } 
    
    /**
     * Returns the position of the cell in the sensed direction given.
     * 
     * @param p The position to check from.
     * @param direction The direction the ant is facing.
     * @param sense_direction The direction being sensed.
     * @return The position of the cell in the sensed direction given. 
     */
    public Position sensed_cell(Position p, int direction, SenseDirection sense_direction){
        return null;
    }
    
    /**
     * Takes the file path of the world file and uses it to create 
     * the cells held by the world object.
     * 
     * @param path The path to the world file.
     */
    public void loadWorld(String path){
    }
    
    /**
     * Returns true if the cell at Position p is rocky; false otherwise.
     * 
     * @param p The Position of the cell to check.
     * @return True if the cell at Position p is rocky; false otherwise.
     */
    public boolean rocky(Position p){
        return true;
    }
    
    /**
     * Returns true if the cell at Position p contains an Ant; false otherwise.
     * 
     * @param p The Position of the cell to check.
     * @return True if the cell at Position p contains an Ant; false otherwise.
     */
    public boolean some_ant_is_at(Position p){
        return true;
    }
    
    /**
     * Returns the Ant at Position p.
     * 
     * @param p The Position of the cell to get the ant from.
     * @return The Ant at Position p.
     */
    public Ant ant_at(Position p){
        return null;
    }
    
    /**
     * Sets the Ant at the given Position to be the given Ant.
     * 
     * @param p The position of the cell in which to put the ant.
     * @param ant The ant to put at the given position.
     */
    public void set_ant_at(Position p, Ant ant){
    }
    
    /**
     * Removes the Ant from the given Position.
     * 
     * @param p The Position to remove the ant from.
     */
    public void clear_ant_at(Position p){
    }
    
    /**
     * Kills (removes) the ant from the given position.
     * 
     * @param p The Position to kill the ant at. 
     */
    public void kill_ant_at(Position p){
    }
    
    /**
     * Returns the amount of food at Position p.
     * 
     * @param p The Position to check for food.
     * @return The amount of food at Position p.
     */
    public int food_at(Position p){
        return 0;
    }
    
    /**
     * Sets the amount of food at the given Position to the given value.
     * 
     * @param p The Position to set the food at.
     * @param food The amount of food to set.
     */
    public void set_food_at(Position p, int food){
    }
    
    /**
     * Returns true if an anthill of the given color is at the given Position.
     * 
     * @param p The Position to check for an anthill.
     * @param color The color of the anthill to check for.
     * @return true if an anthill of the given color is at the given Position.
     */
    public boolean anthill_at(Position p, Color color){
        return true;
    }
    
    /**
     * Sets given marker at the given Position.
     * 
     * @param p The Position in which to set the marker.
     * @param color The color of the marker.
     * @param marker The marker to set.
     */
    public void set_marker_at(Position p, Color color, int marker){
    }
    
    /**
     * Clears the given marker at the given Position.
     * 
     * @param p The Position of the marker to clear.
     * @param color The color of the marker to clear.
     * @param marker The marker to clear.
     */
    public void clear_marker_at(Position p, Color color, int marker){
    }
    
    /**
     * Returns true if the given marker exists at the given Position;
     * false otherwise.
     * 
     * @param p The Position to check for the marker.
     * @param color The color of the marker to check for.
     * @param marker The marker to check for.
     * @return true if the given marker exists at the given Position;
     * false otherwise.
     */
    public boolean check_marker_at(Position p, Color color, int marker){
        return true;
    }
}
