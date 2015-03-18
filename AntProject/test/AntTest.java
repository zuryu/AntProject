
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
 * @version 18 March 2015
 */
public class AntTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getColor method, of class Ant.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Ant instance = null;
        Color expResult = null;
        Color result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Ant.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Ant instance = null;
        int expResult = 0;
        int result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResting method, of class Ant.
     */
    @Test
    public void testGetResting() {
        System.out.println("getResting");
        Ant instance = null;
        int expResult = 0;
        int result = instance.getResting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class Ant.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Ant instance = null;
        int expResult = 0;
        int result = instance.getDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHasFood method, of class Ant.
     */
    @Test
    public void testIsHasFood() {
        System.out.println("isHasFood");
        Ant instance = null;
        boolean expResult = false;
        boolean result = instance.isHasFood();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Ant.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Ant instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class Ant.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        int state = 0;
        Ant instance = null;
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResting method, of class Ant.
     */
    @Test
    public void testSetResting() {
        System.out.println("setResting");
        int resting = 0;
        Ant instance = null;
        instance.setResting(resting);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDirection method, of class Ant.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        int direction = 0;
        Ant instance = null;
        instance.setDirection(direction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHasFood method, of class Ant.
     */
    @Test
    public void testSetHasFood() {
        System.out.println("setHasFood");
        boolean hasFood = false;
        Ant instance = null;
        instance.setHasFood(hasFood);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
