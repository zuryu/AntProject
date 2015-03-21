
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
 * @version 21 March 2015
 */
public class CellTest {
    
    private Cell c1;
    private Cell c2;
    private Cell c3;
    private Ant a1;
    
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
        c1 = new Cell(a1);
        c2 = new Cell(true, 0);
        c3 = new Cell(false, 5);
        a1 = new Ant(Color.Black, 7);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * A test for bad inputs in the Cell class.
     */
    @Test
    public void testFails(){
        
        //Try to put food or an ant in a rocky Cell.
        try {
            c1 = new Cell(true, 5);
            fail("Rocky Cell created with food.");
        } catch (IllegalArgumentException e){
        }
        
        try {
            c2.setFood(10);
            fail("Tried to add food to a rocky Cell.");
        } catch (UnsupportedOperationException e){
        }
        
        try {
            c2.setAnt(a1);
            fail("Tried to add an ant to a rocky Cell.");
        } catch (UnsupportedOperationException e){
        }   
    }
    
    /**
     * Test of getAnt method, of class Cell.
     */
    @Test
    public void testGetAnt() {
        assertEquals(a1, c1.getAnt());
        assertEquals(null, c2.getAnt());
        assertEquals(null, c3.getAnt());
        
        c1.setAnt(null);
        c2.setAnt(a1);
        
        assertEquals(null, c1.getAnt());
        assertEquals(a1, c2.getAnt());
        assertEquals(null, c3.getAnt());
    }

    /**
     * Test of setAnt method, of class Cell.
     */
    @Test
    public void testSetAnt() {
        assertEquals(a1, c1.getAnt());
        assertEquals(null, c2.getAnt());
        assertEquals(null, c3.getAnt());
        
        c1.setAnt(null);
        c2.setAnt(a1);
        
        assertEquals(null, c1.getAnt());
        assertEquals(a1, c2.getAnt());
        assertEquals(null, c3.getAnt());
        
        c1.setAnt(a1);
        c2.setAnt(null);
        c3.setAnt(a1);
        
        assertEquals(a1, c1.getAnt());
        assertEquals(null, c2.getAnt());
        assertEquals(a1, c3.getAnt());
    }

    /**
     * Test of getFood method, of class Cell.
     */
    @Test
    public void testGetFood() {
        assertEquals(0, c1.getFood());
        assertEquals(0, c2.getFood());
        assertEquals(5, c3.getFood());

        c3.setFood(13);
        
        assertEquals(0, c1.getFood());
        assertEquals(0, c2.getFood());
        assertEquals(13, c3.getFood());
    }

    /**
     * Test of setFood method, of class Cell.
     */
    @Test
    public void testSetFood() {
        assertEquals(0, c1.getFood());
        assertEquals(0, c2.getFood());
        assertEquals(5, c3.getFood());

        c3.setFood(24);
        
        assertEquals(0, c1.getFood());
        assertEquals(0, c2.getFood());
        assertEquals(24, c3.getFood());
        
        c3.setFood(2);
        
        assertEquals(0, c1.getFood());
        assertEquals(0, c2.getFood());
        assertEquals(2, c3.getFood());
    }

    /**
     * Test of isRocky method, of class Cell.
     */
    @Test
    public void testIsRocky() {
        assertEquals(false, c1.isRocky());
        assertEquals(true, c2.isRocky());
        assertEquals(false, c3.isRocky());
    }

    /**
     * Test of isAnthill method, of class Cell.
     */
    @Test
    public void testIsAnthill() {
        assertEquals(true, c1.isAnthill());
        assertEquals(false, c2.isAnthill());
        assertEquals(false, c3.isAnthill());
    }

    /**
     * Test of isMarkerSet method, of class Cell.
     */
    @Test
    public void testIsMarkerSet() {
        assertEquals(false, c3.isMarkerSet(0, Color.Red));
        assertEquals(false, c3.isMarkerSet(1, Color.Red));
        assertEquals(false, c3.isMarkerSet(2, Color.Red));
        assertEquals(false, c3.isMarkerSet(3, Color.Red));
        assertEquals(false, c3.isMarkerSet(4, Color.Red));
        assertEquals(false, c3.isMarkerSet(5, Color.Red));
        
        assertEquals(false, c3.isMarkerSet(0, Color.Black));
        assertEquals(false, c3.isMarkerSet(1, Color.Black));
        assertEquals(false, c3.isMarkerSet(2, Color.Black));
        assertEquals(false, c3.isMarkerSet(3, Color.Black));
        assertEquals(false, c3.isMarkerSet(4, Color.Black));
        assertEquals(false, c3.isMarkerSet(5, Color.Black));
        
        c3.setMarker(0, Color.Red);
        c3.setMarker(4, Color.Red);
        c3.setMarker(5, Color.Red);
        
        c3.setMarker(1, Color.Black);
        c3.setMarker(2, Color.Black);
        
        assertEquals(true, c3.isMarkerSet(0, Color.Red));
        assertEquals(false, c3.isMarkerSet(1, Color.Red));
        assertEquals(false, c3.isMarkerSet(2, Color.Red));
        assertEquals(false, c3.isMarkerSet(3, Color.Red));
        assertEquals(true, c3.isMarkerSet(4, Color.Red));
        assertEquals(true, c3.isMarkerSet(5, Color.Red));
        
        assertEquals(false, c3.isMarkerSet(0, Color.Black));
        assertEquals(true, c3.isMarkerSet(1, Color.Black));
        assertEquals(true, c3.isMarkerSet(2, Color.Black));
        assertEquals(false, c3.isMarkerSet(3, Color.Black));
        assertEquals(false, c3.isMarkerSet(4, Color.Black));
        assertEquals(false, c3.isMarkerSet(5, Color.Black));
        
        c3.unsetMarker(0, Color.Red);
        c3.unsetMarker(4, Color.Red);
        c3.unsetMarker(5, Color.Red);
        
        c3.unsetMarker(1, Color.Black);
        c3.unsetMarker(2, Color.Black);
        
        assertEquals(false, c3.isMarkerSet(0, Color.Red));
        assertEquals(false, c3.isMarkerSet(1, Color.Red));
        assertEquals(false, c3.isMarkerSet(2, Color.Red));
        assertEquals(false, c3.isMarkerSet(3, Color.Red));
        assertEquals(false, c3.isMarkerSet(4, Color.Red));
        assertEquals(false, c3.isMarkerSet(5, Color.Red));
        
        assertEquals(false, c3.isMarkerSet(0, Color.Black));
        assertEquals(false, c3.isMarkerSet(1, Color.Black));
        assertEquals(false, c3.isMarkerSet(2, Color.Black));
        assertEquals(false, c3.isMarkerSet(3, Color.Black));
        assertEquals(false, c3.isMarkerSet(4, Color.Black));
        assertEquals(false, c3.isMarkerSet(5, Color.Black));
    }    
}
