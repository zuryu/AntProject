
/**
 * This class represents an ant in the ant game.
 * 
 * @author 118435
 * @version 18 March 2015
 */
public class Ant {
    
    private Color color;        // The color of the ant.
    private int state;          // The current state of the ant.
    private int resting;        // The number of turns the ant is resting for.
    private int direction;      // The direction the ant is facing.
    private boolean hasFood;    // If the ant is holding food or not.
    private int id;             // The id of the ant.

    /**
     * Creates an Ant of the given color with the given ID.
     * 
     * @param color The color of the ant.
     * @param id The ID of the ant.
     */
    public Ant(Color color, int id) {
        this.color = color;
        this.id = id;
        state = 0;
        resting = 0;
        direction = 0;
        hasFood = false;
    }
    
    /**
     * Returns the color of the ant.
     * 
     * @return The color of the ant.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the current state of the ant.
     * 
     * @return The current state of the ant.
     */
    public int getState() {
        return state;
    }

    /**
     * Returns the number of turns the ant is resting for.
     * 
     * @return The number of turns the ant is resting for.
     */
    public int getResting() {
        return resting;
    }

    /**
     * Returns the direction the ant is facing.
     * 
     * @return The direction the ant is facing.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Return whether the ant is holding food or not.
     * 
     * @return True if the ant is holding food; false otherwise. 
     */
    public boolean isHasFood() {
        return hasFood;
    }

    /**
     * Returns the ID of the ant.
     * 
     * @return The ID of the ant.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the current state of the ant to the given value.
     * 
     * @param state The state to put the ant into. 
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Sets the resting value of the ant to the given value.
     * 
     * @param resting The new value of resting. 
     */
    public void setResting(int resting) {
        this.resting = resting;
    }

    /**
     * Sets the direction of the ant to the given value.
     * 
     * @param direction The new direction of the ant.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Sets the has food value of the ant to the given value.
     * 
     * @param hasFood The new value for if the ant is holding food. 
     */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }
    
    
}
