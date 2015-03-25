
import States.SenseDirection;
import States.Condition;
import States.LeftOrRight;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the game world of the ant game. A world is a 
 * hexagonal grid of 150 by 150.
 * 
 * @author 118435
 * @version 25 March 2015
 */
public class World {
    
    private Cell[][] cells;
    private int x;
    private int y;
    
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
    public Position sensed_cell(Position p, int direction, SenseDirection sense_direction){
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
    public void loadWorld(String path){
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
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AntBrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<String> splitLine(String line){
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        for (char letter : line.toCharArray()){
            if (Character.isLetterOrDigit(letter)){
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
    
    private Cell parseCell(String cell){
        switch (cell){
            case "#":
                return new Cell(true);
            case ".":
                return new Cell(0);
            case "+":
                Ant ant  = new Ant()
                return new Cell()
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
    public boolean anthill_at(Position p, Color color){
        Color anthill = cells[p.getX()][p.getY()].isAnthill();
        return anthill == color;
    }
    
    /**
     * Sets given marker at the given Position.
     * 
     * @param p The Position in which to set the marker.
     * @param color The color of the marker.
     * @param marker The marker to set.
     */
    public void set_marker_at(Position p, Color color, int marker){
        cells[p.getX()][p.getY()].setMarker(marker, color);
    }
    
    /**
     * Clears the given marker at the given Position.
     * 
     * @param p The Position of the marker to clear.
     * @param color The color of the marker to clear.
     * @param marker The marker to clear.
     */
    public void clear_marker_at(Position p, Color color, int marker){
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
    public boolean check_marker_at(Position p, Color color, int marker){
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
    public boolean check_any_marker_at(Position p, Color color){
        boolean marker = false;
        int i = 0;
        while (!marker && i < 6){
            if (check_marker_at(p, color, i)){
                marker = true;
            }
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
    public boolean cell_matches(Position p, Condition cond, Color color){
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
    public int adjacent_ants(Position p, Color color){
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
    public Color other_color(Color color){
        if (color == Color.Black){
            return Color.Red;
        }
        return Color.Black;
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
}
