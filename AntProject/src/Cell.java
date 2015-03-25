
/**
 * This class represents a cell in the world of the ant game.
 * 
 * @author 118435
 * @version 25 March 2015
 */
public class Cell {
    
    private Ant ant;                    // The ant that is in the Cell; null if there is no ant in the Cell.
    private boolean rocky;              // True if the Cell is rocky; false otherwise.
    private int food;               // The amount of food in the Cell - Default no food.
    private boolean[] redMarkers;       // Each cell of the array represents a marker 0 to 5; the boolean values show if those markers are set.
    private boolean[] blackMarkers;
    private Color anthill;              // Set to a Color if the Cell is part of that color anthill or null otherwise.

    /**
     * Used to create anthill spaces.
     * 
     * @param ant The ant to put on the anthill space.
     */
    public Cell(Ant ant) {
        this.ant = ant;
        anthill = ant.getColor();
        rocky = false;
        food = 0;
        
        redMarkers = new boolean[6];
        blackMarkers = new boolean[6];
    }

    /**
     * Used to create rocky or clear cells with no food.
     * 
     * @param rocky If the Cell is rocky or clear.
     */
    public Cell(boolean rocky) { 
        this.rocky = rocky;
        anthill = null;
        food = 0;
        ant = null;
        
        redMarkers = new boolean[6];
        blackMarkers = new boolean[6];
    }
    
    /**
     * Used to create clear cell with the given amount of food.
     * 
     * @param food The amount of food to put in the Cell.
     */
    public Cell(int food) { 
        this.food = food;
        rocky = false;
        ant = null;
        anthill = null;
        
        redMarkers = new boolean[6];
        blackMarkers = new boolean[6];
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
    
    /**
     * Returns the color of the anthill on the cell or null if the cell is not an anthill.
     * 
     * @return The color of the anthill on the cell or null if the cell is not an anthill.
     */
    public Color isAnthill(){
        return anthill;
    }
    
    /**
     * Returns true if the Cell contains a marker of the given type and given color.
     * 
     * @param marker The index of the marker to check for.
     * @param color The color of the marker to check for.
     * @return true if the Cell contains a marker of the given type and given color.
     */
    public boolean isMarkerSet(int marker, Color color){
        
        boolean set = false;
        
        if (color == Color.Red){
            set = redMarkers[marker];
        } else {
             set = blackMarkers[marker];
        }
        
        return set;
    }
    
    /**
     * Sets the given marker in the Cell.
     * 
     * @param marker The marker to set.
     * @param color The color of the marker to set.
     */
    public void setMarker(int marker, Color color){
        if (color == Color.Red){
            redMarkers[marker] = true;
        } else {
            blackMarkers[marker] = true;
        }
    }
    
    /**
     * Unsets the given marker in the Cell.
     * 
     * @param marker The marker to unset.
     * @param color The color of the marker to unset.
     */
    public void unsetMarker(int marker, Color color){
        if (color == Color.Red){
            redMarkers[marker] = false;
        } else {
            blackMarkers[marker] = false;
        }
    }
}
