
import States.Condition;
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
 * @version 18 March 2015
 */
public class WorldTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of adjacent_cell method, of class World.
     */
    @Test
    public void testAdjacent_cell() {
        System.out.println("adjacent_cell");
        Position p = null;
        int direction = 0;
        World instance = new World();
        Position expResult = null;
        Position result = instance.adjacent_cell(p, direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sensed_cell method, of class World.
     */
    @Test
    public void testSensed_cell() {
        System.out.println("sensed_cell");
        Position p = null;
        int direction = 0;
        SenseDirection sense_direction = null;
        World instance = new World();
        Position expResult = null;
        Position result = instance.sensed_cell(p, direction, sense_direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadWorld method, of class World.
     */
    @Test
    public void testLoadWorld() {
        System.out.println("loadWorld");
        String path = "";
        World instance = new World();
        instance.loadWorld(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rocky method, of class World.
     */
    @Test
    public void testRocky() {
        System.out.println("rocky");
        Position p = null;
        World instance = new World();
        boolean expResult = false;
        boolean result = instance.rocky(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of some_ant_is_at method, of class World.
     */
    @Test
    public void testSome_ant_is_at() {
        System.out.println("some_ant_is_at");
        Position p = null;
        World instance = new World();
        boolean expResult = false;
        boolean result = instance.some_ant_is_at(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ant_at method, of class World.
     */
    @Test
    public void testAnt_at() {
        System.out.println("ant_at");
        Position p = null;
        World instance = new World();
        Ant expResult = null;
        Ant result = instance.ant_at(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_ant_at method, of class World.
     */
    @Test
    public void testSet_ant_at() {
        System.out.println("set_ant_at");
        Position p = null;
        Ant ant = null;
        World instance = new World();
        instance.set_ant_at(p, ant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear_ant_at method, of class World.
     */
    @Test
    public void testClear_ant_at() {
        System.out.println("clear_ant_at");
        Position p = null;
        World instance = new World();
        instance.clear_ant_at(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kill_ant_at method, of class World.
     */
    @Test
    public void testKill_ant_at() {
        System.out.println("kill_ant_at");
        Position p = null;
        World instance = new World();
        instance.kill_ant_at(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of food_at method, of class World.
     */
    @Test
    public void testFood_at() {
        System.out.println("food_at");
        Position p = null;
        World instance = new World();
        int expResult = 0;
        int result = instance.food_at(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_food_at method, of class World.
     */
    @Test
    public void testSet_food_at() {
        System.out.println("set_food_at");
        Position p = null;
        int food = 0;
        World instance = new World();
        instance.set_food_at(p, food);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of anthill_at method, of class World.
     */
    @Test
    public void testAnthill_at() {
        System.out.println("anthill_at");
        Position p = null;
        Color color = null;
        World instance = new World();
        boolean expResult = false;
        boolean result = instance.anthill_at(p, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_marker_at method, of class World.
     */
    @Test
    public void testSet_marker_at() {
        System.out.println("set_marker_at");
        Position p = null;
        Color color = null;
        int marker = 0;
        World instance = new World();
        instance.set_marker_at(p, color, marker);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear_marker_at method, of class World.
     */
    @Test
    public void testClear_marker_at() {
        System.out.println("clear_marker_at");
        Position p = null;
        Color color = null;
        int marker = 0;
        World instance = new World();
        instance.clear_marker_at(p, color, marker);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check_marker_at method, of class World.
     */
    @Test
    public void testCheck_marker_at() {
        System.out.println("check_marker_at");
        Position p = null;
        Color color = null;
        int marker = 0;
        World instance = new World();
        boolean expResult = false;
        boolean result = instance.check_marker_at(p, color, marker);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check_any_marker_at method, of class World.
     */
    @Test
    public void testCheck_any_marker_at() {
        System.out.println("check_any_marker_at");
        Position p = null;
        Color color = null;
        World instance = new World();
        boolean expResult = false;
        boolean result = instance.check_any_marker_at(p, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cell_matches method, of class World.
     */
    @Test
    public void testCell_matches() {
        System.out.println("cell_matches");
        Position p = null;
        Condition cond = null;
        Color color = null;
        World instance = new World();
        boolean expResult = false;
        boolean result = instance.cell_matches(p, cond, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjacent_ants method, of class World.
     */
    @Test
    public void testAdjacent_ants() {
        System.out.println("adjacent_ants");
        Position p = null;
        Color color = null;
        World instance = new World();
        int expResult = 0;
        int result = instance.adjacent_ants(p, color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check_for_surrounded_ant_at method, of class World.
     */
    @Test
    public void testCheck_for_surrounded_ant_at() {
        System.out.println("check_for_surrounded_ant_at");
        Position p = null;
        World instance = new World();
        instance.check_for_surrounded_ant_at(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check_for_surrounded_ants method, of class World.
     */
    @Test
    public void testCheck_for_surrounded_ants() {
        System.out.println("check_for_surrounded_ants");
        Position p = null;
        World instance = new World();
        instance.check_for_surrounded_ants(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of other_color method, of class World.
     */
    @Test
    public void testOther_color() {
        System.out.println("other_color");
        Color color = null;
        World instance = new World();
        Color expResult = null;
        Color result = instance.other_color(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
