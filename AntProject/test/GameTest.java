
import States.LeftOrRight;
import States.*;
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
 * @version 21 March 2015
 */
public class GameTest {
    private Game game;
    private Game game2;
    
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
        game = new Game("../WorldFiles/testWorld1.", "../AntFiles/testAnt2.ant", "../AntFiles/testAnt2.ant");
        game2 = new Game();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get_instruction method, of class Game.
     */
    @Test
    public void testGet_instruction() {
        State state = game.get_instruction(Color.Red, 3);
        assertEquals(state.getClass(), PickUp.class);
        PickUp pickUp = (PickUp)state;
        assertEquals(62, pickUp.getState1());
        assertEquals(3, pickUp.getState2());
        
        State state2 = game.get_instruction(Color.Red, 0);
        assertEquals(state2.getClass(), Sense.class);
        Sense sense = (Sense)state2;
        assertEquals(SenseDirection.RightAhead, sense.getDirection());
        assertEquals(8, sense.getState1());
        assertEquals(3, sense.getState2());
        assertEquals(Condition.Home, sense.getCondition());
        
        State state3 = game.get_instruction(Color.Black, 7);
        assertEquals(state3.getClass(), Flip.class);
        Flip flip = (Flip)state3;
        assertEquals(12, flip.getMaxNumber());
        assertEquals(0, flip.getState1());
        assertEquals(46, flip.getState2());
        
        State state4 = game.get_instruction(Color.Black, 5);
        assertEquals(state4.getClass(), Turn.class);
        Turn turn = (Turn)state4;
        assertEquals(LeftOrRight.Right, turn.getLeftOrRight());
        assertEquals(43, turn.getState());
    }

    /**
     * Test of newGame method, of class Game.
     */
    @Test
    public void testNewGame() {
        game2.newGame("../WorldFiles/testWorld1.", "../AntFiles/testAnt2.ant", "../AntFiles/testAnt2.ant");
    }

    /**
     * Test of randomint method, of class Game.
     */
    @Test
    public void testRandomint() {
        int[] expected = new int[] {7193, 2932, 10386, 5575, 100, 15976, 430, 9740, 9449, 1636, 11030, 9848, 13965, 16051, 14483, 
                                    6708, 5184, 15931, 7014, 461, 11371, 5856, 2136, 9139, 1684, 15900, 10236, 13297, 1364, 6876, 
                                    15687, 14127, 11387, 13469, 11860, 15589, 14209, 16327, 7024, 3297, 3120, 842, 12397, 9212, 5520, 
                                    4983, 7205, 7193, 4883, 7712, 6732, 7006, 10241, 1012, 15227, 9910, 14119, 15124, 6010, 13191, 
                                    5820, 14074, 5582, 5297, 10387, 4492, 14468, 7879, 8839, 12668, 5436, 8081, 4900, 10723, 10360, 
                                    1218, 11923, 3870, 12071, 3574, 12232, 15592, 12909, 9711, 6638, 2488, 12725, 16145, 9746, 9053, 
                                    5881, 3867, 10512, 4312, 8529, 1576, 15803, 5498, 12730, 7397};
        for (int i = 0; i < 100; i++){
            assertEquals(expected[i], );
        }
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

    /**
     * Test of main method, of class Game.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Game.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
