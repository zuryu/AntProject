
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;


/**
 *
 * @author 118345
 */
public class Hexagon {
    
    public Hexagon(int x, int y, Graphics2D g2){
        g2.draw(new Line2D.Double(new Point(x+10,y+10), new Point(x, y)));
    }
}
