;
import States.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the AntBrain class.
 * 
 * @version 23 March 2015
 */
public class AntBrainTest {
    private AntBrain ab;
    
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
        ab = new AntBrain("AntFiles/testAnt1.ant");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadAntBrain method, of class AntBrain.
     */
    @Test
    public void testLoadAntBrain() {
        
        ab.loadAntBrain("AntFiles/testAnt1.ant");
        assertEquals(true, ab.getState(0).getClass().equals(Sense.class));
        Sense sense = (Sense)ab.getState(0);
        assertEquals(SenseDirection.Ahead, sense.getDirection());
        assertEquals(1, sense.getState1());
        assertEquals(3, sense.getState2());
        assertEquals(Condition.Food, sense.getCondition());
        
        assertEquals(true, ab.getState(1).getClass().equals(Move.class));
        Move move = (Move)ab.getState(1);
        assertEquals(2, move.getState1());
        assertEquals(0, move.getState2());
        
        
        assertEquals(true, ab.getState(2).getClass().equals(PickUp.class));
        PickUp pickUp = (PickUp)ab.getState(2);
        assertEquals(8, pickUp.getState1());
        assertEquals(0, pickUp.getState2());
        
        assertEquals(true, ab.getState(3).getClass().equals(Flip.class));
        Flip flip = (Flip)ab.getState(3);
        assertEquals(3, flip.getMaxNumber());
        assertEquals(4, flip.getState1());
        assertEquals(5, flip.getState2());
        
        assertEquals(true, ab.getState(4).getClass().equals(Turn.class));
        Turn turn = (Turn)ab.getState(4);
        assertEquals(LeftOrRight.Left, turn.getLeftOrRight());
        assertEquals(0, turn.getState());
        
        assertEquals(true, ab.getState(5).getClass().equals(Flip.class));
        Flip flip2 = (Flip)ab.getState(5);
        assertEquals(2, flip2.getMaxNumber());
        assertEquals(6, flip2.getState1());
        assertEquals(7, flip2.getState2());
        
        assertEquals(true, ab.getState(6).getClass().equals(Turn.class));
        Turn turn2 = (Turn)ab.getState(6);
        assertEquals(LeftOrRight.Right, turn2.getLeftOrRight());
        assertEquals(0, turn2.getState());
        
        assertEquals(true, ab.getState(7).getClass().equals(Move.class));
        Move move2 = (Move)ab.getState(7);
        assertEquals(0, move2.getState1());
        assertEquals(3, move2.getState2());
        
        assertEquals(true, ab.getState(8).getClass().equals(Sense.class));
        Sense sense2 = (Sense)ab.getState(8);
        assertEquals(SenseDirection.Ahead, sense2.getDirection());
        assertEquals(9, sense2.getState1());
        assertEquals(11, sense2.getState2());
        assertEquals(Condition.Home, sense2.getCondition());
        
        assertEquals(true, ab.getState(9).getClass().equals(Move.class));
        Move move3 = (Move)ab.getState(9);
        assertEquals(10, move3.getState1());
        assertEquals(8, move3.getState2());
        
        assertEquals(true, ab.getState(10).getClass().equals(Drop.class));
        Drop drop = (Drop)ab.getState(10);
        assertEquals(0, drop.getState());
        
        assertEquals(true, ab.getState(11).getClass().equals(Flip.class));
        Flip flip3 = (Flip)ab.getState(11);
        assertEquals(3, flip3.getMaxNumber());
        assertEquals(12, flip3.getState1());
        assertEquals(13, flip3.getState2());
        
        assertEquals(true, ab.getState(12).getClass().equals(Turn.class));
        Turn turn3 = (Turn)ab.getState(12);
        assertEquals(LeftOrRight.Left, turn3.getLeftOrRight());
        assertEquals(8, turn3.getState());
        
        assertEquals(true, ab.getState(13).getClass().equals(Flip.class));
        Flip flip4 = (Flip)ab.getState(13);
        assertEquals(2, flip4.getMaxNumber());
        assertEquals(14, flip4.getState1());
        assertEquals(15, flip4.getState2());
        
        assertEquals(true, ab.getState(14).getClass().equals(Turn.class));
        Turn turn4 = (Turn)ab.getState(14);
        assertEquals(LeftOrRight.Right, turn4.getLeftOrRight());
        assertEquals(8, turn4.getState());
        
        assertEquals(true, ab.getState(15).getClass().equals(Move.class));
        Move move4 = (Move)ab.getState(15);
        assertEquals(8, move4.getState1());
        assertEquals(11, move4.getState2());
    }
    
    /**
     * Test of loadAntBrain method with the Sense command, of class AntBrain.
     */
    @Test
    public void testSense() {
        
        ab.loadAntBrain("AntFiles/SenseTest.ant");
        for (int i = 0; i < 19; i++){
            assertEquals(true, ab.getState(i).getClass().equals(Sense.class));
            Sense sense = (Sense)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(SenseDirection.Ahead, sense.getDirection());
                    break;
                case 1:
                    assertEquals(SenseDirection.Here, sense.getDirection());
                    break;
                case 2:
                    assertEquals(SenseDirection.RightAhead, sense.getDirection());
                    break;
                case 3:
                    assertEquals(SenseDirection.LeftAhead, sense.getDirection());
                    break;
                case 4:
                    assertEquals(Condition.Friend, sense.getCondition());
                    break;
                case 5:
                    assertEquals(Condition.Foe, sense.getCondition());
                    break;
                case 6:
                    assertEquals(Condition.FriendWithFood, sense.getCondition());
                    break;
                case 7:
                    assertEquals(Condition.FoeWithFood, sense.getCondition());
                    break;
                case 8:
                    assertEquals(Condition.Food, sense.getCondition());
                    break;
                case 9:
                    assertEquals(Condition.Rock, sense.getCondition());
                    break;
                case 10:
                    assertEquals(Condition.Marker0, sense.getCondition());
                    break;
                case 11:
                    assertEquals(Condition.Marker1, sense.getCondition());
                    break;
                case 12:
                    assertEquals(Condition.Marker2, sense.getCondition());
                    break;
                case 13:
                    assertEquals(Condition.Marker3, sense.getCondition());
                    break;
                case 14:
                    assertEquals(Condition.Marker4, sense.getCondition());
                    break;
                case 15:
                    assertEquals(Condition.Marker5, sense.getCondition());
                    break;
                case 16:
                    assertEquals(Condition.FoeMarker, sense.getCondition());
                    break;
                case 17:
                    assertEquals(Condition.Home, sense.getCondition());
                    break;
                case 18:
                    assertEquals(Condition.FoeHome, sense.getCondition());
                    break;
                default:
                    fail("Out of range in test Sense.");
            }
        }
    }
    
    /**
     * Test of loadAntBrain method with the Mark command method, of class AntBrain.
     */
    @Test
    public void testMark() {
        ab.loadAntBrain("AntFiles/MarkTest.ant");
        for (int i = 0; i < 6; i++){
            assertEquals(true, ab.getState(i).getClass().equals(Mark.class));
            Mark mark = (Mark)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(0, mark.getMarker());
                    assertEquals(5, mark.getState());
                    break;
                case 1:
                    assertEquals(1, mark.getMarker());
                    assertEquals(4, mark.getState());
                    break;
                case 2:
                    assertEquals(2, mark.getMarker());
                    assertEquals(3, mark.getState());
                    break;
                case 3:
                    assertEquals(3, mark.getMarker());
                    assertEquals(2, mark.getState());
                    break;
                case 4:
                    assertEquals(4, mark.getMarker());
                    assertEquals(0, mark.getState());
                    break;
                case 5:
                    assertEquals(5, mark.getMarker());
                    assertEquals(1, mark.getState());
                    break;
                default:
                    fail("Out of range in test Mark.");
            }
        }
    }
    
         /**
     * Test of loadAntBrain method with the Unmark command method, of class AntBrain.
     */
    @Test
    public void testUnmark() {
        ab.loadAntBrain("AntFiles/UnmarkTest.ant");
        for (int i = 0; i < 6; i++){
            assertEquals(true, ab.getState(i).getClass().equals(Unmark.class));
            Unmark unmark = (Unmark)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(0, unmark.getMarker());
                    assertEquals(1, unmark.getState());
                    break;
                case 1:
                    assertEquals(1, unmark.getMarker());
                    assertEquals(2, unmark.getState());
                    break;
                case 2:
                    assertEquals(2, unmark.getMarker());
                    assertEquals(4, unmark.getState());
                    break;
                case 3:
                    assertEquals(3, unmark.getMarker());
                    assertEquals(5, unmark.getState());
                    break;
                case 4:
                    assertEquals(4, unmark.getMarker());
                    assertEquals(1, unmark.getState());
                    break;
                case 5:
                    assertEquals(5, unmark.getMarker());
                    assertEquals(0, unmark.getState());
                    break;
                default:
                    fail("Out of range in test Unmark.");
            }
        }
    }
         
    /**
     * Test of loadAntBrain method with the PickUp command method, of class AntBrain.
     */
    @Test
    public void testPickUp() {
        ab.loadAntBrain("AntFiles/PickUpTest.ant");
        for(int i = 0; i < 3; i++){
           assertEquals(true, ab.getState(i).getClass().equals(PickUp.class));
            PickUp pickUp = (PickUp)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(0, pickUp.getState1());
                    assertEquals(1, pickUp.getState2());
                    break;
                case 1:
                    assertEquals(1, pickUp.getState1());
                    assertEquals(2, pickUp.getState2());
                    break;
                case 2:
                    assertEquals(0, pickUp.getState1());
                    assertEquals(1, pickUp.getState2());
                    break;
                default:
                    fail("Out of range in test PickUp.");
            }
        }
    }
    
    /**
     * Test of loadAntBrain method with the Drop command method, of class AntBrain.
     */
    @Test
    public void testDrop() {
        ab.loadAntBrain("AntFiles/DropTest.ant");
        for(int i = 0; i < 3; i++){
           assertEquals(true, ab.getState(i).getClass().equals(Drop.class));
            Drop drop = (Drop)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(0, drop.getState());
                    break;
                case 1:
                    assertEquals(2, drop.getState());
                    break;
                case 2:
                    assertEquals(1, drop.getState());
                    break;
                default:
                    fail("Out of range in test Drop.");
            }
        }
    }
    
    /**
     * Test of loadAntBrain method with the Turn command method, of class AntBrain.
     */
    @Test
    public void testTurn() {
        ab.loadAntBrain("AntFiles/TurnTest.ant");
        for(int i = 0; i < 2; i++){
            assertEquals(true, ab.getState(i).getClass().equals(Turn.class));
            Turn turn = (Turn)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(LeftOrRight.Left, turn.getLeftOrRight());
                    assertEquals(0, turn.getState());
                    break;
                case 1:
                    assertEquals(LeftOrRight.Right, turn.getLeftOrRight());
                    assertEquals(1, turn.getState());
                    break;
                default:
                    fail("Out of range in test Turn.");
            }
        }
    }
    
    /**
     * Test of loadAntBrain method with the Move command method, of class AntBrain.
     */
    @Test
    public void testMove() {
        ab.loadAntBrain("AntFiles/MoveTest.ant");
        for(int i = 0; i < 2; i++){
            assertEquals(true, ab.getState(i).getClass().equals(Move.class));
            Move move = (Move)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(1, move.getState1());
                    assertEquals(0, move.getState2());
                    break;
                case 1:
                    assertEquals(1, move.getState1());
                    assertEquals(0, move.getState2());
                    break;
                default:
                    fail("Out of range in test Move.");
            }
        }
    }
    
    /**
     * Test of loadAntBrain method with the Flip command method, of class AntBrain.
     */
    @Test
    public void testFlip() {
        ab.loadAntBrain("AntFiles/FlipTest.ant");
        for(int i = 0; i < 2; i++){
            assertEquals(true, ab.getState(i).getClass().equals(Flip.class));
            Flip flip = (Flip)ab.getState(i);
            switch (i){
                case 0:
                    assertEquals(10, flip.getMaxNumber());
                    assertEquals(0, flip.getState1());
                    assertEquals(1, flip.getState2());
                    break;
                case 1:
                    assertEquals(2, flip.getMaxNumber());
                    assertEquals(0, flip.getState1());
                    assertEquals(1, flip.getState2());
                    break;
                default:
                    fail("Out of range in test Flip.");
            }
        }
    }

    /**
     * Test of getState method, of class AntBrain.
     */
    @Test
    public void testGetState() {
        ab.loadAntBrain("AntFiles/testAnt2.ant");

        assertEquals(true, ab.getState(0).getClass().equals(Sense.class));
        Sense sense = (Sense)ab.getState(0);
        assertEquals(SenseDirection.RightAhead, sense.getDirection());
        assertEquals(6, sense.getState1());
        assertEquals(3, sense.getState2());
        assertEquals(Condition.Home, sense.getCondition());
       
        assertEquals(true, ab.getState(1).getClass().equals(Mark.class));
        Mark mark = (Mark)ab.getState(1);
        assertEquals(3, mark.getMarker());
        assertEquals(2, mark.getState());
        
        assertEquals(true, ab.getState(2).getClass().equals(Unmark.class));
        Unmark unmark = (Unmark)ab.getState(2);
        assertEquals(4, unmark.getMarker());
        assertEquals(5, unmark.getState());
        
        assertEquals(true, ab.getState(3).getClass().equals(PickUp.class));
        PickUp pickUp = (PickUp)ab.getState(3);
        assertEquals(1, pickUp.getState1());
        assertEquals(3, pickUp.getState2());
        
        assertEquals(true, ab.getState(4).getClass().equals(Drop.class));
        Drop drop = (Drop)ab.getState(4);
        assertEquals(0, drop.getState());
        
        assertEquals(true, ab.getState(5).getClass().equals(Turn.class));
        Turn turn = (Turn)ab.getState(5);
        assertEquals(LeftOrRight.Right, turn.getLeftOrRight());
        assertEquals(3, turn.getState());
        
        assertEquals(true, ab.getState(6).getClass().equals(Move.class));
        Move move = (Move)ab.getState(6);
        assertEquals(2, move.getState1());
        assertEquals(5, move.getState2());
        
        assertEquals(true, ab.getState(7).getClass().equals(Flip.class));
        Flip flip = (Flip)ab.getState(7);
        assertEquals(12, flip.getMaxNumber());
        assertEquals(0, flip.getState1());
        assertEquals(4, flip.getState2());
    }
    
}
