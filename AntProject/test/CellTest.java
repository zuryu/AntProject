
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Cell class.
 * 
 * @author 118435
 * @version 18 March 2015
 */
public class CellTest {
    
    public CellTest() {
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
     * Test of getAnt method, of class Cell.
     */
    @Test
    public void testGetAnt() {
        System.out.println("getAnt");
        Cell instance = null;
        Ant expResult = null;
        Ant result = instance.getAnt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnt method, of class Cell.
     */
    @Test
    public void testSetAnt() {
        System.out.println("setAnt");
        Ant ant = null;
        Cell instance = null;
        instance.setAnt(ant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFood method, of class Cell.
     */
    @Test
    public void testGetFood() {
        System.out.println("getFood");
        Cell instance = null;
        int expResult = 0;
        int result = instance.getFood();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFood method, of class Cell.
     */
    @Test
    public void testSetFood() {
        System.out.println("setFood");
        int food = 0;
        Cell instance = null;
        instance.setFood(food);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRocky method, of class Cell.
     */
    @Test
    public void testIsRocky() {
        System.out.println("isRocky");
        Cell instance = null;
        boolean expResult = false;
        boolean result = instance.isRocky();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
