
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.io.File;
import javax.swing.*;

/**
 * This class creates the user interface for the ant game.
 */
public class View extends Thread {
    
    private JPanel panel;
    private JFrame frame;
    private JLabel redScore;
    private JLabel blackScore;
    private JLabel redNumber;
    private JLabel blackNumber;
    private JButton gameButton;
    private JLabel rounds;
    private Game game;
    private Grid grid;
    private JFileChooser fc;
    private boolean loadNew;
    private JDialog load;
    private Update update;
    private boolean running;
    
    public static void main(String[] args){
        View myView = new View();
    }
    
    public View(){
        update = new Update();  
        running = false;
        loadNew = false;
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File("WorldFiles"));
        game = new Game("WorldFiles/1.world", "AntFiles/sample.ant", "AntFiles/sample.ant");     // Change; get values from user.
        grid = new Grid();
        drawGUI();
        loadFiles();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        update.update();
    }
    
    public final void loadFiles(){
        final JTextField worldText = new JTextField("");
        final JTextField ant1Text = new JTextField("");
        final JTextField ant2Text = new JTextField("");
        final JButton worldButton = new JButton("Choose world");
        final JButton ant1Button = new JButton("Choose Red Ants");
        JButton ant2Button = new JButton("Choose Black Ants");
        JButton loadButton = new JButton("Load");
        ActionListener al = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int opened = fc.showOpenDialog(frame);
                if (opened == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    if (e.getSource() == worldButton){
                        worldText.setText(file.getPath());
                    } else if (e.getSource() == ant1Button){
                        ant1Text.setText(file.getPath());
                    } else {
                        ant2Text.setText(file.getPath());
                    }
                    fc.setCurrentDirectory(file);
                } 
            }
        };
        
        worldText.setPreferredSize(new Dimension(300, 20));
        ant1Text.setPreferredSize(new Dimension(300, 20));
        ant2Text.setPreferredSize(new Dimension(300, 20));
        
        worldButton.setPreferredSize(new Dimension(200, 50));
        ant1Button.setPreferredSize(new Dimension(200, 50));
        ant2Button.setPreferredSize(new Dimension(200, 50));
        
        load = new JDialog(frame, "New game/Load");
        JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.Y_AXIS));
        loadPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        worldButton.addActionListener(al);
        ant1Button.addActionListener(al);
        ant2Button.addActionListener(al);
        loadButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFile(worldText.getText(), true) && checkFile(ant1Text.getText(), false) && checkFile(ant2Text.getText(), false)){
                    loadNew = true; 
                    //load.setVisible(false);
                    game.resetGame(worldText.getText(), ant1Text.getText(), ant2Text.getText());
//                    grid = new Grid();
                    load.dispose();
                    
                    
                    grid.repaint();
                    frame.repaint();
                    
                    SwingWorker<String, Object> worker = new SwingWorker<String, Object>() {
                        @Override
                        protected String doInBackground() throws Exception { 
                                          
                            return update.update();
                        }
                        
                        @Override
                        protected void done() {                        
                            grid.repaint();
                        }
                    };      
                    worker.execute();
                    
//                    frame.pack();
                    //update();
//                    SwingUtilities.invokeLater(new Runnable(){
//
//                        @Override
//                        public void run() {
//                           update.update();
//                        }
//                    });
                    //update.update();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid world or ant files.\nWorld files have '.world' extension.\nAnt files have '.ant' extension.");
                }
            }
        });
        
        JPanel worldLoad = new JPanel();
        worldLoad.setLayout(new BoxLayout(worldLoad, BoxLayout.X_AXIS));
        worldLoad.add(Box.createRigidArea(new Dimension(10, 0)));
        worldLoad.add(worldText);
        worldLoad.add(Box.createRigidArea(new Dimension(20, 0)));
        worldLoad.add(worldButton);
        worldLoad.add(Box.createRigidArea(new Dimension(10, 0)));
        
        JPanel ant1Load = new JPanel();
        ant1Load.setLayout(new BoxLayout(ant1Load, BoxLayout.X_AXIS));
        ant1Load.add(Box.createRigidArea(new Dimension(10, 0)));
        ant1Load.add(ant1Text);
        ant1Load.add(Box.createRigidArea(new Dimension(20, 0)));
        ant1Load.add(ant1Button);
        ant1Load.add(Box.createRigidArea(new Dimension(10, 0)));
        
        JPanel ant2Load = new JPanel();
        ant2Load.setLayout(new BoxLayout(ant2Load, BoxLayout.X_AXIS));
        ant2Load.add(Box.createRigidArea(new Dimension(10, 0)));
        ant2Load.add(ant2Text);
        ant2Load.add(Box.createRigidArea(new Dimension(20, 0)));
        ant2Load.add(ant2Button);
        ant2Load.add(Box.createRigidArea(new Dimension(10, 0)));
        
        loadPanel.add(worldLoad);
        loadPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loadPanel.add(ant1Load);
        loadPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loadPanel.add(ant2Load);
        loadPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loadPanel.add(loadButton);
        loadPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        load.getContentPane().add(loadPanel);
        load.pack();
        load.setVisible(true);
    }
    
    public boolean checkFile(String path, boolean world){
        File file = new File(path);
        if (file.exists() && !file.isDirectory()){
            if (world && path.endsWith(".world")){
                return true;
            } else if (!world && path.endsWith(".ant")){
                return true;
            }
        }
        return false;
    }
    
    public final void drawGUI(){
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        redScore = new JLabel("0");
        blackScore = new JLabel("0");
        redNumber = new JLabel("0");
        blackNumber = new JLabel("0");
        gameButton = new JButton("New Game");
        rounds = new JLabel("0");
        
        redScore.setFont(new Font("SanSerif", 1, 20));
        blackScore.setFont(new Font("SanSerif", 1, 20));
        redNumber.setFont(new Font("SanSerif", 1, 20));
        blackNumber.setFont(new Font("SanSerif", 1, 20));
        gameButton.setFont(new Font("SanSerif", 1, 20));
        rounds.setFont(new Font("SanSerif", 1, 20));
        
        gameButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (load != null){
                    load.setVisible(true);
                } else {
                    loadFiles();
                }
            }
        });
        
        JLabel title = new JLabel("Ant Game");
        JLabel roundLabel = new JLabel("Round");
        JLabel scoreLabel = new JLabel("Score");
        JLabel numberLabel = new JLabel("Number of Ants");
        JLabel redLabel1 = new JLabel("Red");
        JLabel blackLabel1 = new JLabel("Black");
        JLabel redLabel2 = new JLabel("Red");
        JLabel blackLabel2 = new JLabel("Black");
        
        title.setFont(new Font("SanSerif", 1, 20));
        roundLabel.setFont(new Font("SanSerif", 1, 20));
        scoreLabel.setFont(new Font("SanSerif", 1, 20));
        numberLabel.setFont(new Font("SanSerif", 1, 20));
        redLabel1.setFont(new Font("SanSerif", 1, 20));
        blackLabel1.setFont(new Font("SanSerif", 1, 20));
        redLabel2.setFont(new Font("SanSerif", 1, 20));
        blackLabel2.setFont(new Font("SanSerif", 1, 20));
        
        // The button panel.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(title);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(gameButton);
        
        // The rounds labels.
        JPanel roundPanel = new JPanel();
        roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.X_AXIS));
        roundPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        roundPanel.add(roundLabel);
        roundPanel.add(Box.createHorizontalGlue());
        roundPanel.add(rounds);
        roundPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        // The score label.
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.add(scoreLabel);
        
        // The red score labels.
        JPanel scoreRedPanel = new JPanel();
        scoreRedPanel.setLayout(new BoxLayout(scoreRedPanel, BoxLayout.X_AXIS));
        scoreRedPanel.add(redLabel1);
        scoreRedPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        scoreRedPanel.add(redScore);
        
        // The black score labels.
        JPanel scoreBlackPanel = new JPanel();
        scoreBlackPanel.setLayout(new BoxLayout(scoreBlackPanel, BoxLayout.X_AXIS));
        scoreBlackPanel.add(blackLabel1);
        scoreBlackPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        scoreBlackPanel.add(blackScore);
        
        scorePanel.add(scoreRedPanel);
        scorePanel.add(scoreBlackPanel);
        
        // The number of ants label.
        JPanel numPanel = new JPanel();
        numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.Y_AXIS));
        numPanel.add(numberLabel);
        
        // The red number of ants labels.
        JPanel numRedPanel = new JPanel();
        numRedPanel.setLayout(new BoxLayout(numRedPanel, BoxLayout.X_AXIS));
        numRedPanel.add(redLabel2);
        numRedPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        numRedPanel.add(redNumber);
        
        // The black number of ants labels.
        JPanel numBlackPanel = new JPanel();
        numBlackPanel.setLayout(new BoxLayout(numBlackPanel, BoxLayout.X_AXIS));
        numBlackPanel.add(blackLabel2);
        numBlackPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        numBlackPanel.add(blackNumber);
        
        numPanel.add(numRedPanel);
        numPanel.add(numBlackPanel);
        
        
        
        buttonPanel.add(roundPanel);
        buttonPanel.add(scorePanel);
        buttonPanel.add(numPanel);
        buttonPanel.setPreferredSize(new Dimension(200, 500)); 
        
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.add(grid, BorderLayout.WEST);
        
        frame.getContentPane().add(panel);
    }
    
    public class Grid extends JPanel {
        
        public Grid(){
            super.setPreferredSize(new Dimension(900, 605));
        }
        
        @Override
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            
            for(int y = 0; y < game.getWorld().getY(); y++){
                for(int x = 0; x < game.getWorld().getX(); x++){
                    drawCell(x, y, g2);
                }
            }
        }
        
        public void drawCell(int x, int y, Graphics2D g2){
            
            Path2D.Double path = new Path2D.Double();
            Cell cell = game.getWorld().getCells()[x][y];
            
            if (y % 2 == 0){
                path.moveTo(x * 6, (y * 4) + 2);
                path.lineTo((x * 6) + 3, y * 4);
                path.lineTo((x * 6) + 6, (y * 4) + 2);
                path.lineTo((x * 6) + 6, (y * 4) + 4);
                path.lineTo((x * 6) + 3, (y * 4) + 6);
                path.lineTo(x * 6, (y * 4) + 4);
                path.lineTo(x * 6, (y * 4) + 2);
                path.closePath();

                if (cell.isRocky()){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.GRAY, x * 6 + 6, y * 4 + 3, Color.GRAY));  
                } else if (cell.getAnt() != null){
                    if (cell.getAnt().getColor() == AntColor.Black){
                        g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.blue, x * 6 + 9, y * 4 + 3, Color.blue));
                    } else {
                        g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.red, x * 6 + 9, y * 4 + 3, Color.red));
                    } 
                } else if (cell.isAnthill() == AntColor.Black){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.BLACK, x * 6 + 6, y * 4 + 3, Color.black)); 
                } else if (cell.isAnthill() == AntColor.Red){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.orange, x * 6 + 6, y * 4 + 3, Color.orange)); 
                } else if (cell.getFood() > 0){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.yellow, x * 6 + 6, y * 4 + 3, Color.yellow)); 
                } else {
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.green, x * 6 + 6, y * 4 + 3, Color.green)); 
                }
            } else {
                path.moveTo(x * 6 + 3, (y * 4) + 2);
                path.lineTo((x * 6 + 3) + 3, y * 4);
                path.lineTo((x * 6 + 3) + 6, (y * 4) + 2);
                path.lineTo((x * 6 + 3) + 6, (y * 4) + 4);
                path.lineTo((x * 6 + 3) + 3, (y * 4) + 6);
                path.lineTo(x * 6 + 3, (y * 4) + 4);
                path.lineTo(x * 6 + 3, (y * 4) + 2);
                path.closePath();
                
                if (cell.isRocky()){
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.GRAY, x * 6 + 9, y * 4 + 3, Color.GRAY));  
                } else if (cell.getAnt() != null){
                    if (cell.getAnt().getColor() == AntColor.Black){
                        g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.blue, x * 6 + 9, y * 4 + 3, Color.blue));
                    } else {
                        g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.red, x * 6 + 9, y * 4 + 3, Color.red));
                    }
                } else if (cell.isAnthill() == AntColor.Black){
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.BLACK, x * 6 + 9, y * 4 + 3, Color.black)); 
                } else if (cell.isAnthill() == AntColor.Red){
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.orange, x * 6 + 9, y * 4 + 3, Color.orange)); 
                } else if (cell.getFood() > 0){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.yellow, x * 6 + 6, y * 4 + 3, Color.yellow)); 
                } else {
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.green, x * 6 + 9, y * 4 + 3, Color.green)); 
                }               
            }
            g2.fill(path);
        }
    }
    
    public class Update extends Thread {
        
        public String update(){
            System.out.println("Enter loop***********************************************************");
            for (int j = 1; j < 3000; j++){grid.repaint();
                if (loadNew && running){
                    loadNew = false;
                    System.out.println("Exiting loop***********************************************************");
                    return "";
                } else {
                    loadNew = false;
                }
                running = true;
                System.out.println(j);
                rounds.setText(Integer.toString(j));
                game.newGame();
                redNumber.setText(game.getWorld().getRedAnts() + "");
                blackNumber.setText(game.getWorld().getBlackAnts() + "");
                grid.repaint();
                frame.repaint();
            }
            running = false;
            System.out.println("Exiting loop***********************************************************");
            return "";
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


