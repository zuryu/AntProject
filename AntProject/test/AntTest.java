
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Ant class.
 * 
 * @author 118435
 * @version 21 March 2015
 */
public class AntTest {
    
    private Ant a1;
    private Ant a2;
    
    public AntTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        a1 = new Ant(Color.Black, 0);
        a2 = new Ant(Color.Red, 1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getColor method, of class Ant.
     */
    @Test
    public void testGetColor() {
        assertEquals(Color.Black, a1.getColor());
        assertEquals(Color.Red, a2.getColor());
    }

    /**
     * Test of getState method, of class Ant.
     */
    @Test
    public void testGetState() {
        assertEquals(0, a1.getState());
        assertEquals(0, a2.getState());
        
        a1.setState(90);
        a2.setState(5003);
        
        assertEquals(90, a1.getState());
        assertEquals(5003, a2.getState());
    }

    /**
     * Test of getResting method, of class Ant.
     */
    @Test
    public void testGetResting() {
        assertEquals(0, a1.getResting());
        assertEquals(0, a2.getResting());
        
        a1.setResting(12);
        a2.setResting(45);
        
        assertEquals(12, a1.getResting());
        assertEquals(45, a2.getResting());
    }

    /**
     * Test of getDirection method, of class Ant.
     */
    @Test
    public void testGetDirection() {
        assertEquals(0, a1.getDirection());
        assertEquals(0, a2.getDirection());
        
        a1.setDirection(4);
        a2.setDirection(3);
        
        assertEquals(4, a1.getDirection());
        assertEquals(3, a2.getDirection());
    }

    /**
     * Test of isHasFood method, of class Ant.
     */
    @Test
    public void testIsHasFood() {
        assertEquals(false, a1.isHasFood());
        assertEquals(false, a2.isHasFood());
        
        a1.setHasFood(true);
        
        assertEquals(true, a1.isHasFood());
        assertEquals(false, a2.isHasFood());
    }

    /**
     * Test of getId method, of class Ant.
     */
    @Test
    public void testGetId() {
        assertEquals(0, a1.getId());
        assertEquals(1, a2.getId());
    }

    /**
     * Test of setState method, of class Ant.
     */
    @Test
    public void testSetState() {
        assertEquals(0, a1.getState());
        assertEquals(0, a2.getState());
        
        a1.setState(90);
        a2.setState(5003);
        
        assertEquals(90, a1.getState());
        assertEquals(5003, a2.getState());
        
        a1.setState(0);
        a2.setState(85);
        
        assertEquals(0, a1.getState());
        assertEquals(85, a2.getState());
    }

    /**
     * Test of setResting method, of class Ant.
     */
    @Test
    public void testSetResting() {
        assertEquals(0, a1.getResting());
        assertEquals(0, a2.getResting());
        
        a1.setResting(23);
        a2.setResting(450);
        
        assertEquals(23, a1.getResting());
        assertEquals(450, a2.getResting());
        
        a1.setResting(2);
        a2.setResting(69);
        
        assertEquals(2, a1.getResting());
        assertEquals(69, a2.getResting());
    }

    /**
     * Test of setDirection method, of class Ant.
     */
    @Test
    public void testSetDirection() {
        assertEquals(0, a1.getDirection());
        assertEquals(0, a2.getDirection());
        
        a1.setDirection(1);
        a2.setDirection(4);
        
        assertEquals(1, a1.getDirection());
        assertEquals(4, a2.getDirection());
        
        a1.setDirection(0);
        a2.setDirection(5);
        
        assertEquals(0, a1.getDirection());
        assertEquals(5, a2.getDirection());
    }

    /**
     * Test of setHasFood method, of class Ant.
     */
    @Test
    public void testSetHasFood() {
        assertEquals(false, a1.isHasFood());
        assertEquals(false, a2.isHasFood());
        
        a1.setHasFood(true);
        
        assertEquals(true, a1.isHasFood());
        assertEquals(false, a2.isHasFood());
        
        a1.setHasFood(false);
        a2.setHasFood(true);
        
        assertEquals(false, a1.isHasFood());
        assertEquals(true, a2.isHasFood());
    }
    
}
