
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author 118435
 */
public class View {
    
    private JPanel panel;
    private JFrame frame;
    
    public static void main(String[] args){
        new View();
    }
    
    public View(){
    }
    
    private void showIntro(){
        
    }
    
    public void hexagon(Graphics2D g2, int x, int y){
        g2.draw(new Line2D.Double(x, y + 0.25, x + 0.5, y));
    }
}
