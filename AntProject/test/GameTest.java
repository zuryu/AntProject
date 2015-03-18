
import States.LeftOrRight;
import States.State;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Game class.
 * 
 * @author 118435
 * @version 18 March 2015
 */
public class GameTest {
    
    public GameTest() {
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
     * Test of get_instruction method, of class Game.
     */
    @Test
    public void testGet_instruction() {
        System.out.println("get_instruction");
        Color color = null;
        int state = 0;
        Game instance = new Game();
        State expResult = null;
        State result = instance.get_instruction(color, state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newGame method, of class Game.
     */
    @Test
    public void testNewGame() {
        System.out.println("newGame");
        String world = "";
        String ant1 = "";
        String ant2 = "";
        Game instance = new Game();
        instance.newGame(world, ant1, ant2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomint method, of class Game.
     */
    @Test
    public void testRandomint() {
        System.out.println("randomint");
        int n = 0;
        Game instance = new Game();
        int expResult = 0;
        int result = instance.randomint(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step method, of class Game.
     */
    @Test
    public void testStep() {
        System.out.println("step");
        int id = 0;
        Game instance = new Game();
        instance.step(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ant_is_alive method, of class Game.
     */
    @Test
    public void testAnt_is_alive() {
        System.out.println("ant_is_alive");
        int id = 0;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.ant_is_alive(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find_ant method, of class Game.
     */
    @Test
    public void testFind_ant() {
        System.out.println("find_ant");
        int id = 0;
        Game instance = new Game();
        Position expResult = null;
        Position result = instance.find_ant(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of turn method, of class Game.
     */
    @Test
    public void testTurn() {
        System.out.println("turn");
        LeftOrRight leftOrRight = null;
        int direction = 0;
        Game instance = new Game();
        int expResult = 0;
        int result = instance.turn(leftOrRight, direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
