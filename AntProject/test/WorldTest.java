
import States.Condition;
import States.LeftOrRight;
import States.SenseDirection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the World class.
 * 
 * @author 118435
 * @version 23 March 2015
 */
public class WorldTest {
    
    private World world;
    
    public WorldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        world = new World();
        world.loadWorld("WorldFiles/tiny.world");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of adjacent_cell method, of class World.
     */
    @Test
    public void testAdjacent_cell() {
        assertEquals(5, world.adjacent_cell(new Position(6, 6), 4).getX());
        assertEquals(2, world.adjacent_cell(new Position(3, 2), 2).getX());
        assertEquals(2, world.adjacent_cell(new Position(1, 4), 0).getX());
        
        assertEquals(5, world.adjacent_cell(new Position(6, 6), 4).getY());
        assertEquals(3, world.adjacent_cell(new Position(3, 2), 2).getY());
        assertEquals(4, world.adjacent_cell(new Position(1, 4), 0).getY());
    }

    /**
     * Test of sensed_cell method, of class World.
     */
    @Test
    public void testSensed_cell() {
        assertEquals(4, world.sensed_cell(new Position(4, 7), 5, SenseDirection.Here).getX());
        assertEquals(2, world.sensed_cell(new Position(1, 0), 0, SenseDirection.Ahead).getX());
        assertEquals(3, world.sensed_cell(new Position(3, 3), 3, SenseDirection.LeftAhead).getX());
        assertEquals(1, world.sensed_cell(new Position(2, 4), 1, SenseDirection.RightAhead).getX());
        
        assertEquals(7, world.sensed_cell(new Position(4, 7), 5, SenseDirection.Here).getY());
        assertEquals(0, world.sensed_cell(new Position(1, 0), 0, SenseDirection.Ahead).getY());
        assertEquals(4, world.sensed_cell(new Position(3, 3), 3, SenseDirection.LeftAhead).getY());
        assertEquals(5, world.sensed_cell(new Position(2, 4), 1, SenseDirection.RightAhead).getY());
    }

    /**
     * Test of loadWorld method, of class World.
     */
    @Test
    public void testLoadWorld() {
        world.loadWorld("WorldFiles/1.world");
    }

    /**
     * Test of rocky method, of class World.
     */
    @Test
    public void testRocky() {
        assertEquals(true, world.rocky(new Position(1, 0)));
        assertEquals(false, world.rocky(new Position(1, 1)));
    }

    /**
     * Test of some_ant_is_at method, of class World.
     */
    @Test
    public void testSome_ant_is_at() {
        assertEquals(true, world.some_ant_is_at(new Position(5, 5)));
        assertEquals(true, world.some_ant_is_at(new Position(6, 3)));
        
        assertEquals(false, world.some_ant_is_at(new Position(3, 1)));
        assertEquals(false, world.some_ant_is_at(new Position(3, 8)));
    }

    /**
     * Test of ant_at method, of class World.
     */
    @Test
    public void testAnt_at() {
        Ant a = new Ant(Color.Red, 12);
        world.set_ant_at(new Position(3, 1), a);
        assertEquals(a, world.ant_at(new Position(3, 1)));
        
        Ant b = new Ant(Color.Black, 3);
        world.set_ant_at(new Position(4, 8), b);
        assertEquals(b, world.ant_at(new Position(4, 8)));
    }

    /**
     * Test of set_ant_at method, of class World.
     */
    @Test
    public void testSet_ant_at() {
        assertEquals(false, world.some_ant_is_at(new Position(3, 1)));
        Ant a = new Ant(Color.Red, 12);
        world.set_ant_at(new Position(3, 1), a);
        assertEquals(a, world.ant_at(new Position(3, 1)));
        
        assertEquals(false, world.some_ant_is_at(new Position(4, 8)));
        Ant b = new Ant(Color.Black, 3);
        world.set_ant_at(new Position(4, 8), b);
        assertEquals(b, world.ant_at(new Position(4, 8)));
    }

    /**
     * Test of clear_ant_at method, of class World.
     */
    @Test
    public void testClear_ant_at() {
        assertEquals(true, world.some_ant_is_at(new Position(4, 5)));
        world.clear_ant_at(new Position(4, 5));
        assertEquals(false, world.some_ant_is_at(new Position(4, 5)));
    }

    /**
     * Test of food_at method, of class World.
     */
    @Test
    public void testFood_at() {
        assertEquals(9, world.food_at(new Position(1, 1)));
        assertEquals(3, world.food_at(new Position(2, 8)));
        assertEquals(0, world.food_at(new Position(3, 8)));
    }

    /**
     * Test of set_food_at method, of class World.
     */
    @Test
    public void testSet_food_at() {
        assertEquals(0, world.food_at(new Position(3, 8)));
        world.set_food_at(new Position(3, 8), 7);
        assertEquals(7, world.food_at(new Position(3, 8)));
    }

    /**
     * Test of anthill_at method, of class World.
     */
    @Test
    public void testAnthill_at() {
        assertEquals(true, world.anthill_at(new Position(4, 3), Color.Black));
        assertEquals(false, world.anthill_at(new Position(4, 3), Color.Red));
        
        assertEquals(true, world.anthill_at(new Position(5, 5), Color.Red));
        assertEquals(false, world.anthill_at(new Position(5, 5), Color.Black));
        
        assertEquals(false, world.anthill_at(new Position(0, 0), Color.Red));
        assertEquals(false, world.anthill_at(new Position(0, 0), Color.Black));
    }

    /**
     * Test of set_marker_at method, of class World.
     */
    @Test
    public void testSet_marker_at() {
        assertEquals(false, world.check_marker_at(new Position(4, 5), Color.Red, 3));
        world.set_marker_at(new Position(4, 5), Color.Red, 3);
        assertEquals(true, world.check_marker_at(new Position(4, 5), Color.Red, 3));
    }

    /**
     * Test of clear_marker_at method, of class World.
     */
    @Test
    public void testClear_marker_at() {
        world.set_marker_at(new Position(6, 1), Color.Black, 2);
        assertEquals(true, world.check_marker_at(new Position(6, 1), Color.Black, 2));
        world.clear_marker_at(new Position(6, 1), Color.Black, 2);
        assertEquals(false, world.check_marker_at(new Position(6, 1), Color.Black, 2));
    }

    /**
     * Test of check_marker_at method, of class World.
     */
    @Test
    public void testCheck_marker_at() {
        world.set_marker_at(new Position(5, 2), Color.Black, 0);
        assertEquals(true, world.check_marker_at(new Position(5, 2), Color.Black, 0));
        world.clear_marker_at(new Position(5, 2), Color.Black, 0);
        assertEquals(false, world.check_marker_at(new Position(5, 2), Color.Black, 0));
    }

    /**
     * Test of check_any_marker_at method, of class World.
     */
    @Test
    public void testCheck_any_marker_at() {
        world.set_marker_at(new Position(6, 1), Color.Black, 2);
        assertEquals(true, world.check_any_marker_at(new Position(6, 1), Color.Black));
        assertEquals(false, world.check_any_marker_at(new Position(6, 1), Color.Red));
        world.clear_marker_at(new Position(6, 1), Color.Black, 2);
        assertEquals(false, world.check_any_marker_at(new Position(6, 1), Color.Black));
    }

    /**
     * Test of cell_matches method, of class World.
     */
    @Test
    public void testCell_matches() {
        assertEquals(true, world.cell_matches(new Position(7, 1), Condition.Food, Color.Red));
        assertEquals(true, world.cell_matches(new Position(7, 2), Condition.Home, Color.Black));
        assertEquals(true, world.cell_matches(new Position(4, 5), Condition.FoeHome, Color.Black));
        assertEquals(true, world.cell_matches(new Position(5, 5), Condition.Friend, Color.Red));
        assertEquals(true, world.cell_matches(new Position(7, 0), Condition.Rock, Color.Red));
        
        assertEquals(false, world.cell_matches(new Position(7, 0), Condition.Food, Color.Red));
        assertEquals(false, world.cell_matches(new Position(7, 2), Condition.Home, Color.Red));
        assertEquals(false, world.cell_matches(new Position(4, 5), Condition.FoeHome, Color.Red));
        assertEquals(false, world.cell_matches(new Position(5, 5), Condition.Friend, Color.Black));
        assertEquals(false, world.cell_matches(new Position(7, 1), Condition.Rock, Color.Red));
    }

    /**
     * Test of adjacent_ants method, of class World.
     */
    @Test
    public void testAdjacent_ants() {
        assertEquals(6, world.adjacent_ants(new Position(4, 6), Color.Red));
        assertEquals(4, world.adjacent_ants(new Position(5, 4), Color.Black));
    }

    /**
     * Test of other_color method, of class World.
     */
    @Test
    public void testOther_color() {
        assertEquals(Color.Black, world.other_color(Color.Red));
        assertEquals(Color.Red, world.other_color(Color.Black));
    }
    
    /**
     * Test of turn method, of class World.
     */
    @Test
    public void testTurn() {
        assertEquals(5, world.turn(LeftOrRight.Left, 0));
        assertEquals(3, world.turn(LeftOrRight.Left, 4));
        
        assertEquals(0, world.turn(LeftOrRight.Right, 5));
        assertEquals(4, world.turn(LeftOrRight.Right, 3));
    }
}
