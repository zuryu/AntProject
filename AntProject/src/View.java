
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.geom.Line2D;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author 118435
 */
public class View {
    
    private JPanel panel;
    private JFrame frame;
    private Game game;
    
    public static void main(String[] args){
        new View();
    }
    
    public View(){
        game = new Game("WorldFiles/tiny.world", "AntFiles/testAnt2.ant", "AntFiles/testAnt2.ant");     // Change; get values from user.
        drawGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void drawGUI(){
        frame = new JFrame();
        panel = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(3, 0));
        buttonPanel.add(new JLabel("Ant Game"));
        buttonPanel.add(new JButton("New Game"));
        panel.add(buttonPanel, BorderLayout.WEST);
        
        panel.add(new Grid(), BorderLayout.CENTER);
        frame.add(panel);
    }
    
    public class Grid extends JPanel {
        
        @Override
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            game.getWorld().getCells()[0][0].drawCell(0, 0, g2);
            
//            for(int y = 0; y < game.getWorld().getY(); y++){
//                for(int x = 0; x < game.getWorld().getX(); x++){
//                    game.getWorld().getCells()[x][y].drawCell(x, y, g2);
//                }
//            }
        }
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
