
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JApplet;
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
    
    public void drawGUI(){
        frame = new JFrame();
    }
    
    public class Grid extends JPanel {
        
    }
//    
//    private void showIntro(){
//        
//    }
//    
//    public void hexagon(Graphics2D g2, int x, int y){
//        g2.draw(new Line2D.Double(x, y + 0.25, x + 0.5, y));
//    }
//    
//    @Override
//    public void paint(Graphics g){
//        Graphics2D g2 = (Graphics2D) g;
//        hexagon(g2, 1, 1);
//    }
}
