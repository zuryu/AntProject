
/**
 * This class represents the random number generator for the ant game.
 * 
 * @author 118435
 * @version 22 March 2015
 */
public class Randomint {
    
    private int s0;
    private int s4;
    
    public Randomint(int seed){
        s0 = seed;
        s4 = nextS(nextS(nextS(nextS(s0))));
    }   
    
    public int randomint(int n){
        int number = nextX() % n;
        s0 = nextS(s0);
        s4 = nextS(s4);
        return number;
    }
    
    public final int nextS(int s){
        return (s * 22695477 + 1);
    }
    
    public int nextX(){
        return ((s4 / 65536) % 16384);
    }
}
