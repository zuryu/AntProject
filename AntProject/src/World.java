
import States.SenseDirection;
import States.Condition;
import States.LeftOrRight;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the game world of the ant game. A world is a 
 * hexagonal grid of 150 by 150.
 * 
 * @version 1 April 2015
 */
public class World {
    
    private Cell[][] cells;
    private int x;
    private int y;
    private HashMap<Integer, Position> ants;    // Holds the Position of every Ant in the world with its id as the key.
    private int redAnts;
    private int blackAnts;
    
    /**
     * Creates an empty World, which can be used to load and 
     * check the world files. 
     */
    public World(){
        ants = new HashMap<>();
        redAnts = 0;
        blackAnts = 0;
    }
    
    /**
     * Returns the position of the cell adjacent to the given one 
     * in the given direction.
     * 
     * @param p The position to check from.
     * @param direction The direction in which to get the adjacent cell.
     * @return The cell adjacent to the given cell in the given direction.
     */
    public Position adjacent_cell(Position p, int direction) throws IllegalArgumentException{
        switch (direction){
            case 0:
                return new Position(p.getX() + 1, p.getY());
            case 1:
                if (p.getY() % 2 == 0){
                    return new Position(p.getX(), p.getY() + 1);
                } else {
                    return new Position(p.getX() + 1, p.getY() + 1);
                }
            case 2:
                if (p.getY() % 2 == 0){
                    return new Position(p.getX() - 1, p.getY() + 1);
                } else {
                    return new Position(p.getX(), p.getY() + 1);
                }
            case 3:
                return new Position(p.getX() - 1, p.getY());
            case 4:
                if (p.getY() % 2 == 0){
                    return new Position(p.getX() - 1, p.getY() - 1);
                } else {
                    return new Position(p.getX(), p.getY() - 1);
                }
            case 5:
                if (p.getY() % 2 == 0){
                    return new Position(p.getX(), p.getY() - 1);
                } else {
                    return new Position(p.getX() + 1, p.getY() - 1);
                }
            default:
                throw new IllegalArgumentException("Illegal direction in adjacent_cell");
        }
    } 
    
    /**
     * Returns the position of the cell in the sensed direction given.
     * 
     * @param p The position to check from.
     * @param direction The direction the ant is facing.
     * @param sense_direction The direction being sensed.
     * @return The position of the cell in the sensed direction given. 
     */
    public Position sensed_cell(Position p, int direction, SenseDirection sense_direction) throws IllegalArgumentException{
        switch (sense_direction.name()){
            case "Here":
                return p;
            case "Ahead":
                return adjacent_cell(p, direction);
            case "LeftAhead":
                return adjacent_cell(p, turn(LeftOrRight.Left, direction));
            case "RightAhead":
                return adjacent_cell(p, turn(LeftOrRight.Right, direction));
            default:
                throw new IllegalArgumentException("Illegal senseDirection in sensed_cell");
        }
    }
    
    /**
     * Takes the file path of the world file and uses it to create 
     * the cells held by the world object.
     * 
     * @param path The path to the world file.
     */
    public void loadWorld(String path) throws IllegalArgumentException {
        try{
            
            BufferedReader reader = new BufferedReader(new FileReader(path)); 
            String line;
            
            if ((line = reader.readLine()) != null){
                x = Integer.parseInt(line);
            }
            if ((line = reader.readLine()) != null){
                y = Integer.parseInt(line);
            }
            cells = new Cell[x][y];
            for (int i = 0; i < y; i++){
                if ((line = reader.readLine()) != null){
                    ArrayList<String> words = splitLine(line);
                    if (words.size() != x){
                        throw new IllegalArgumentException("Invalid size of line in world file.");
                    }
                    for (int j = 0; j < x; j++){
                        cells[j][i] = parseCell(words.get(j));
                        if (words.get(j).equals("+") || words.get(j).equals("-")){
                            ants.put(ants.size(), new Position(j, i));
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Splits the given string by whitespace and saves each symbol of the map in an ArrayList.
     * 
     * @param line A String which is one row of the map.
     * @return An ArrayList containing the symbols of the map divided by the whitespace.
     */
    private ArrayList<String> splitLine(String line){
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        for (char letter : line.toCharArray()){
            if (!Character.isWhitespace(letter)){
                word = word.concat(Character.toString(letter));
            } else {
                if (word.length() == 1){
                    words.add(word);
                }
                word = "";
            }
        }
        if (word.length() == 1){
            words.add(word);
        }
        return words;
    }
    
    /**
     * Returns a new cell constructed using the information contained in the given String.
     * 
     * @param cell A String representing the contents of the cell; Should only be one character.
     * @return A new Cell object that matches the state described by the given String.
     */
    private Cell parseCell(String cell) throws IllegalArgumentException {
        switch (cell){
            case "#":
                return new Cell(true);
            case ".":
                return new Cell(0);
            case "+":
                Ant ant = new Ant(AntColor.Red, ants.size());
                redAnts++;
                return new Cell(ant);
            case "-":
                Ant ant2 = new Ant(AntColor.Black, ants.size());
                blackAnts++;
                return new Cell(ant2);
            case "1": case "2": case "3":
            case "4": case "5": case "6":
            case "7": case "8": case "9":
                return new Cell(Integer.parseInt(cell));
            default:
                throw new IllegalArgumentException("Illegal symbol in world file : " + cell);
        }
    }
    
    /**
     * Returns true if the cell at Position p is rocky; false otherwise.
     * 
     * @param p The Position of the cell to check.
     * @return True if the cell at Position p is rocky; false otherwise.
     */
    public boolean rocky(Position p){
        return cells[p.getX()][p.getY()].isRocky();
    }
    
    /**
     * Returns true if the cell at Position p contains an Ant; false otherwise.
     * 
     * @param p The Position of the cell to check.
     * @return True if the cell at Position p contains an Ant; false otherwise.
     */
    public boolean some_ant_is_at(Position p){
        return cells[p.getX()][p.getY()].getAnt() != null;
    }
    
    /**
     * Returns the Ant at Position p.
     * 
     * @param p The Position of the cell to get the ant from.
     * @return The Ant at Position p.
     */
    public Ant ant_at(Position p){
        return cells[p.getX()][p.getY()].getAnt();
    }
    
    /**
     * Sets the Ant at the given Position to be the given Ant.
     * 
     * @param p The position of the cell in which to put the ant.
     * @param ant The ant to put at the given position.
     */
    public void set_ant_at(Position p, Ant ant){
        cells[p.getX()][p.getY()].setAnt(ant);
        ants.replace(ant.getId(), p);
//        if (ants.containsKey(ant.getId())){
//            ants.remove(ant.getId());
//        }
//        ants.put(ant.getId(), p);
    }
    
    /**
     * Removes the Ant from the given Position.
     * 
     * @param p The Position to remove the ant from.
     */
    public void clear_ant_at(Position p){
        cells[p.getX()][p.getY()].setAnt(null);
    }
    
    /**
     * Returns the amount of food at Position p.
     * 
     * @param p The Position to check for food.
     * @return The amount of food at Position p.
     */
    public int food_at(Position p){
        return cells[p.getX()][p.getY()].getFood();
    }
    
    /**
     * Sets the amount of food at the given Position to the given value.
     * 
     * @param p The Position to set the food at.
     * @param food The amount of food to set.
     */
    public void set_food_at(Position p, int food){
        cells[p.getX()][p.getY()].setFood(food);
    }
    
    /**
     * Returns true if an anthill of the given color is at the given Position.
     * 
     * @param p The Position to check for an anthill.
     * @param color The color of the anthill to check for.
     * @return true if an anthill of the given color is at the given Position.
     */
    public boolean anthill_at(Position p, AntColor color){
        AntColor anthill = cells[p.getX()][p.getY()].isAnthill();
        return anthill == color;
    }
    
    /**
     * Sets given marker at the given Position.
     * 
     * @param p The Position in which to set the marker.
     * @param color The color of the marker.
     * @param marker The marker to set.
     */
    public void set_marker_at(Position p, AntColor color, int marker){
        cells[p.getX()][p.getY()].setMarker(marker, color);
    }
    
    /**
     * Clears the given marker at the given Position.
     * 
     * @param p The Position of the marker to clear.
     * @param color The color of the marker to clear.
     * @param marker The marker to clear.
     */
    public void clear_marker_at(Position p, AntColor color, int marker){
        cells[p.getX()][p.getY()].unsetMarker(marker, color);
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
    public boolean check_marker_at(Position p, AntColor color, int marker){
        return cells[p.getX()][p.getY()].isMarkerSet(marker, color);
    }
    
    /**
     * Returns true if a marker of the given color exists at the 
     * given Position.
     * 
     * @param p The Position to check for a marker.
     * @param color The color of the marker to check for.
     * @return True if a marker of the given color exists at the given Position.
     */
    public boolean check_any_marker_at(Position p, AntColor color){
        boolean marker = false;
        int i = 0;
        while (!marker && i < 6){
            if (check_marker_at(p, color, i)){
                marker = true;
            }
            i++;
        }
        return marker;
    }
    
    /**
     * Returns true if the given condition exists with the given cell; false otherwise.
     * 
     * @param p The Position to check for the given condition.
     * @param cond The condition to check for.
     * @param color The color of the ant checking for the condition.
     * @return True if the given condition exists with the given cell; false otherwise.
     */
    public boolean cell_matches(Position p, Condition cond, AntColor color) throws IllegalArgumentException {
        if (rocky(p)){
            return cond == Condition.Rock;
        } else {
            switch (cond.name()){
                case "Friend":
                    return some_ant_is_at(p) && (ant_at(p).getColor() == color);
                case "Foe":
                    return some_ant_is_at(p) && (ant_at(p).getColor() != color);
                case "FriendWithFood":
                    return some_ant_is_at(p) && (ant_at(p).getColor() == color) && ant_at(p).isHasFood();
                case "FoeWithFood":
                    return some_ant_is_at(p) && (ant_at(p).getColor() != color) && ant_at(p).isHasFood();
                case "Food":
                    return food_at(p) > 0;
                case "Rock":
                    return false;
                case "Marker0":
                    return check_marker_at(p, color, 0);
                case "Marker1":
                    return check_marker_at(p, color, 1);
                case "Marker2":
                    return check_marker_at(p, color, 2);
                case "Marker3":
                    return check_marker_at(p, color, 3);
                case "Marker4":
                    return check_marker_at(p, color, 4);
                case "Marker5":
                    return check_marker_at(p, color, 5);
                case "FoeMarker":
                    return check_any_marker_at(p, other_color(color));
                case "Home":
                    return anthill_at(p, color);
                case "FoeHome":
                    return anthill_at(p, other_color(color));
                default:
                    throw new IllegalArgumentException("Illegal condition in cell_matches.");
            }
        }
    }
    
    /**
     * Returns the number of ants that are adjacent to the given Position and are of the given color.
     * 
     * @param p The position to check for adjacent ants.
     * @param color The color of the ants to check for.
     * @return The number of ants that are adjacent to the given Position and are of the given color.
     */
    public int adjacent_ants(Position p, AntColor color){
        int number = 0;
        for (int i = 0; i < 6; i++){
            Position cell = adjacent_cell(p, i);
            if (some_ant_is_at(cell) && ant_at(cell).getColor() == color){
                number++;
            }
        }
        return number;
    }
    
    /**
     * Returns the color of the opponent ants.
     * 
     * @param color The color to get the opposite of 
     * @return The color of the opponent ants.
     */
    public AntColor other_color(AntColor color){
        if (color == AntColor.Black){
            return AntColor.Red;
        }
        return AntColor.Black;
    }
    
    /**
     * Returns the direction after the given turn.
     * 
     * @param leftOrRight The direction in which to turn.
     * @param direction The current direction that is being faced.
     * @return The direction after the given turn.
     */
    public int turn(LeftOrRight leftOrRight, int direction){
        if (leftOrRight.name().equals("Left")){
            return ((direction + 5) % 6);
        }
        return ((direction + 1) % 6);
    }
    
    /**
     * Returns the hashMap of all the ant IDs and their Positions.
     * 
     * @return The hashMap of all the ant IDs and their Positions.
     */
    public HashMap<Integer, Position> getAnts(){
        return ants;
    }
    
    /**
     * Returns the cells of the world.
     * 
     * @return The Cells of the World.
     */
    public Cell[][] getCells(){
        return cells;
    }
    
    /**
     * Returns the size of the world in the x direction.
     * 
     * @return The size of the world in the x direction.
     */
    public int getX(){
        return x;
    }
    
    /**
     * Returns the size of the world in the y direction.
     * 
     * @return The size of the world in the y direction.
     */
    public int getY(){
        return y;
    }
    
    /**
     * Removes the ant with the given ID from the ants list. Need to clear_ant_at(p) to remove the ant from the game.
     * 
     * @param id The ID of the ant to kill. 
     */
    public void killAnt(int id){
        AntColor color = ant_at(ants.get(id)).getColor();
        ants.remove(id);
        if (color == AntColor.Black){
            blackAnts--;
        } else {
            redAnts--;
        }
    }
    
    /**
     * Returns the number of black ants in the world.
     * 
     * @return The number of black ants in the world. 
     */
    public int getBlackAnts(){
        return blackAnts;
    }
    
    /**
     * Returns the number of red ants in the world.
     * 
     * @return The number of red ants in the world. 
     */
    public int getRedAnts(){
        return redAnts;
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
}
