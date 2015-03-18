
/**
 * This class represents a cell in the world of the ant game.
 * 
 * @author 118435
 * @version 18 March 2015
 */
public class Cell {
    
    private Ant ant;                    // The ant that is in the Cell; null if there is no ant in the Cell.
    private boolean rocky;              // True if the Cell is rocky; false otherwise.
    private int food;                   // The amount of food in the Cell.
    private boolean[] redMarkers;       // Each cell of the array represents a marker 0 to 5; the boolean values show if those markers are set.
    private boolean[] blackMarkers;
    private Color anthill;              // Set to a Color if the Cell is part of that color anthill or null otherwise.

    /**
     * Used to create anthill spaces.
     * 
     * @param ant The ant to put on the anthill space.
     */
    public Cell(Ant ant) {
    }

    /**
     * Used to create rocky or clear cells with the given amount of food.
     * 
     * @param rocky If the Cell is rocky or clear.
     * @param food The amount of food to put in the Cell.
     */
    public Cell(boolean rocky, int food) { 
    }
    
    /**
     * Returns the Ant in the Cell or null if there is no Ant in the Cell.
     * 
     * @return The Ant in the Cell or null if there is no Ant in the Cell. 
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Sets the Ant at the Cell to be the given Ant.
     * 
     * @param ant The Ant to put in the Cell.
     */
    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    /**
     * Returns the amount of food in the Cell.
     * 
     * @return The amount of food in the Cell.
     */
    public int getFood() {
        return food;
    }

    /**
     * Sets the amount of food in the Cell.
     * 
     * @param food The amount of food the Cell should be holding.
     */
    public void setFood(int food) {
        this.food = food;
    }
    
    /**
     * Returns true if the Cell is rocky; false otherwise.
     * 
     * @return True if the Cell is rocky; false otherwise.
     */
    public boolean isRocky(){
        return rocky;
    }
}