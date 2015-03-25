
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
 * @version 23 March 2015
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
        game = new Game("WorldFiles/tiny.world", "AntFiles/testAnt2.ant", "AntFiles/testAnt2.ant");
        game2 = new Game("WorldFiles/tiny.world", "AntFiles/testAnt2.ant", "AntFiles/testAnt2.ant");
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
        game2.newGame("WorldFiles/tiny.world", "AntFiles/testAnt2.ant", "AntFiles/testAnt2.ant");
    }

    /**
     * Test of step method, of class Game.
     */
    @Test
    public void testStep() {
        game.step(0);
    }

    /**
     * Test of ant_is_alive method, of class Game.
     */
    @Test
    public void testAnt_is_alive() {
        assertEquals(true, game.ant_is_alive(0));
        game.kill_ant_at(game.find_ant(0));
        assertEquals(false, game.ant_is_alive(0));
    }

    /**
     * Test of find_ant method, of class Game.
     */
    @Test
    public void testFind_ant() {
        assertEquals(4, game.find_ant(30).x);
        assertEquals(7, game.find_ant(30).y);
    } 
    
    /**
     * Test of kill_ant_at method, of class World.
     */
    @Test
    public void testKill_ant_at() {
        int id = game.getWorld().ant_at(new Position(5, 2)).getId();
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(5, 2)));
        assertEquals(true, game.ant_is_alive(id));
        game.kill_ant_at(new Position(5, 2));
        assertEquals(false, game.getWorld().some_ant_is_at(new Position(5, 2)));
        assertEquals(false, game.ant_is_alive(id));
    }
    
     /**
     * Test of check_for_surrounded_ant_at method, of class World.
     */
    @Test
    public void testCheck_for_surrounded_ant_at() {
        game.getWorld().clear_ant_at(new Position(4, 6));
        game.getWorld().clear_ant_at(new Position(5, 4));
        game.getWorld().set_ant_at(new Position(4, 6), new Ant(Color.Black, 5));
        game.getWorld().set_ant_at(new Position(5, 4), new Ant(Color.Red, 6));
        
        
        assertEquals(6, game.getWorld().adjacent_ants(new Position(4, 6), Color.Red));
        assertEquals(4, game.getWorld().adjacent_ants(new Position(5, 4), Color.Black));
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(4, 6)));
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(5, 4)));
        
        game.check_for_surrounded_ant_at(new Position(4, 6));
        game.check_for_surrounded_ant_at(new Position(5, 4));
        
        assertEquals(false, game.getWorld().some_ant_is_at(new Position(4, 6)));
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(5, 4)));
    }

    /**
     * Test of check_for_surrounded_ants method, of class World.
     */
    @Test
    public void testCheck_for_surrounded_ants() {
        game.getWorld().clear_ant_at(new Position(4, 6));
        game.getWorld().clear_ant_at(new Position(5, 4));
        game.getWorld().set_ant_at(new Position(4, 6), new Ant(Color.Black, 5));
        game.getWorld().set_ant_at(new Position(5, 4), new Ant(Color.Red, 6));
        
        assertEquals(6, game.getWorld().adjacent_ants(new Position(4, 6), Color.Red));
        assertEquals(4, game.getWorld().adjacent_ants(new Position(5, 4), Color.Black));
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(4, 6)));
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(5, 4)));
        
        game.check_for_surrounded_ants(new Position(3, 5));
        game.check_for_surrounded_ants(new Position(4, 5));
        
        assertEquals(false, game.getWorld().some_ant_is_at(new Position(4, 6)));
        assertEquals(true, game.getWorld().some_ant_is_at(new Position(5, 4)));
    }
}
