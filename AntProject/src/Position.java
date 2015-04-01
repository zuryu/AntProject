
/**
 * This class represents the coordinates of the cells on a hexagonal grid.
 * 
 * @version 18 March 2015
 */
public class Position {
    public int x;   // The x coordinate.
    public int y;   // The y coordinate.
    
    /**
     * Creates a new Position object with the given coordinates.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the Position.
     * 
     * @return The x coordinate of the Position.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the Position.
     * 
     * @return The y coordinate of the Position.
     */
    public int getY() {
        return y;
    }
    
    
}
