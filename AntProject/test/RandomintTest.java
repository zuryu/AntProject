
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Randomint class.
 * 
 * @version 22 March 2015
 */
public class RandomintTest {
    
    private Randomint rand;
    
    public RandomintTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rand = new Randomint(12345);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of randomint method, of class Randomint.
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
            assertEquals(expected[i], rand.nextX());
            rand.randomint(16384);
        }
    }

    /**
     * Test of nextS method, of class Randomint.
     */
    @Test
    public void testNextS() {
        System.out.println("nextS");
        int s = 0;
        Randomint instance = null;
        int expResult = 0;
        int result = instance.nextS(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextX method, of class Randomint.
     */
    @Test
    public void testNextX() {
        System.out.println("nextX");
        Randomint instance = null;
        int expResult = 0;
        int result = instance.nextX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
