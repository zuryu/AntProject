
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Cell class.
 * 
 * @version 25 March 2015
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
        a1 = new Ant(AntColor.Black, 7);
        c1 = new Cell(a1);
        c2 = new Cell(true);
        c3 = new Cell(5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * A test for bad inputs in the Cell class.
     */
    @Test
    public void testFails(){
                
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
        c3.setAnt(a1);
        
        assertEquals(null, c1.getAnt());
        assertEquals(a1, c3.getAnt());
        assertEquals(null, c2.getAnt());
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
        c3.setAnt(a1);
        
        assertEquals(null, c1.getAnt());
        assertEquals(a1, c3.getAnt());
        assertEquals(null, c2.getAnt());
        
        c1.setAnt(a1);
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
        assertEquals(AntColor.Black, c1.isAnthill());
        assertEquals(null, c2.isAnthill());
        assertEquals(null, c3.isAnthill());
    }

    /**
     * Test of isMarkerSet method, of class Cell.
     */
    @Test
    public void testIsMarkerSet() {
        assertEquals(false, c3.isMarkerSet(0, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(1, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(2, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(3, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(4, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(5, AntColor.Red));
        
        assertEquals(false, c3.isMarkerSet(0, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(1, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(2, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(3, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(4, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(5, AntColor.Black));
        
        c3.setMarker(0, AntColor.Red);
        c3.setMarker(4, AntColor.Red);
        c3.setMarker(5, AntColor.Red);
        
        c3.setMarker(1, AntColor.Black);
        c3.setMarker(2, AntColor.Black);
        
        assertEquals(true, c3.isMarkerSet(0, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(1, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(2, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(3, AntColor.Red));
        assertEquals(true, c3.isMarkerSet(4, AntColor.Red));
        assertEquals(true, c3.isMarkerSet(5, AntColor.Red));
        
        assertEquals(false, c3.isMarkerSet(0, AntColor.Black));
        assertEquals(true, c3.isMarkerSet(1, AntColor.Black));
        assertEquals(true, c3.isMarkerSet(2, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(3, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(4, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(5, AntColor.Black));
        
        c3.unsetMarker(0, AntColor.Red);
        c3.unsetMarker(4, AntColor.Red);
        c3.unsetMarker(5, AntColor.Red);
        
        c3.unsetMarker(1, AntColor.Black);
        c3.unsetMarker(2, AntColor.Black);
        
        assertEquals(false, c3.isMarkerSet(0, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(1, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(2, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(3, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(4, AntColor.Red));
        assertEquals(false, c3.isMarkerSet(5, AntColor.Red));
        
        assertEquals(false, c3.isMarkerSet(0, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(1, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(2, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(3, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(4, AntColor.Black));
        assertEquals(false, c3.isMarkerSet(5, AntColor.Black));
    }    
}
