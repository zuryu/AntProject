
import States.State;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the AntBrain class.
 * 
 * @author 118435
 * @version 18 March 2015
 */
public class AntBrainTest {
    
    public AntBrainTest() {
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
     * Test of loadAntBrain method, of class AntBrain.
     */
    @Test
    public void testLoadAntBrain() {
        System.out.println("loadAntBrain");
        String path = "";
        AntBrain instance = new AntBrain();
        instance.loadAntBrain(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class AntBrain.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        int stateIndex = 0;
        AntBrain instance = new AntBrain();
        State expResult = null;
        State result = instance.getState(stateIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}