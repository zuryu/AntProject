
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ArrayList<String> antBrains;
    private HashMap<String, Integer> antBrains2;
    private ArrayList<String> worlds;
    private JLabel winner;
    private Tournament tournament;
    private String world1;
    private String ant1;
    private String ant2;
    private JButton nextButton;
    private ArrayList<Game> games;
    private Game nextGame;
    
    /**
     * Starts the ant game.
     * 
     * @param args None required.
     */
    public static void main(String[] args){
        View myView = new View();
    }
    
    /**
     * Creates a new user interface for the ant game.
     */
    public View(){
        games = new ArrayList<>();
        tournament = new Tournament();
        winner = new JLabel();
        antBrains = new ArrayList<>();
        antBrains2 = new HashMap<>();
        worlds = new ArrayList<>();
        update = new Update();  
        running = false;
        loadNew = false;
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File("WorldFiles"));
        try {
            game = new Game("WorldFiles/1.world", "AntFiles/sample.ant", "AntFiles/sample.ant");
        } catch (IllegalArgumentException | UnsupportedOperationException e){
            JOptionPane.showMessageDialog(frame, "Invalid world or ant files.");
        }
        grid = new Grid();
        drawGUI();
        loadFiles();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        update.update();
    }
    
    /**
     * Create a dialogue to load ant and world files for the game.
     */
    public final void loadFiles(){
        final JTextField worldText = new JTextField("");
        final JTextField ant1Text = new JTextField("");
        final JTextField ant2Text = new JTextField("");
        final JButton worldButton = new JButton("Choose world");
        final JButton ant1Button = new JButton("Choose Red Ants");
        JButton ant2Button = new JButton("Choose Black Ants");
        JButton loadButton = new JButton("Load");
        JButton tournamentButton = new JButton("Add AntBrains to tournament");
        JButton tournamentStart = new JButton("Start tournament");
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
                    antBrains.clear();
                    antBrains2.clear();
                    worlds.clear();
                    games.clear();
                    try {
                        game.resetGame(worldText.getText(), ant1Text.getText(), ant2Text.getText());
                    } catch (IllegalArgumentException | UnsupportedOperationException f){
                        JOptionPane.showMessageDialog(frame, "Invalid world or ant files.");
                    }
                    worldText.setText("");
                    ant1Text.setText("");
                    ant2Text.setText("");
                    load.setVisible(false);
                    
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
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid world or ant files.\nWorld files have '.world' extension.\nAnt files have '.ant' extension.");
                }
            }
        });
        
        tournamentButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFile(ant1Text.getText(), false)){
                    antBrains.add(ant1Text.getText());
                    ant1Text.setText("");
                }
                if (checkFile(ant2Text.getText(), false)){
                    antBrains.add(ant2Text.getText());
                    ant2Text.setText("");
                }
                if (checkFile(worldText.getText(), true)){
                    worlds.add(worldText.getText());
                    worldText.setText("");
                }
            }
        });
        
        tournamentStart.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                runTournament();
                if (!games.isEmpty()){
                    nextButton.setEnabled(true);
                    load.setVisible(false);
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
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(loadButton);
        buttons.add(Box.createRigidArea(new Dimension(20, 0)));
        buttons.add(tournamentButton);
        buttons.add(Box.createRigidArea(new Dimension(20, 0)));
        buttons.add(tournamentStart);
        
        loadPanel.add(buttons);
        loadPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        load.getContentPane().add(loadPanel);
        load.pack();
        load.setVisible(true);
    }
    
    /**
     * Checks if a given file is a valid ant or world file. This does not 
     * read the file to check it's syntax.
     * 
     * @param path The path of the file to check.
     * @param world True if the file is a world file; false otherwise.
     * @return True if the file is a valid world or ant file; false otherwise.
     */
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
    
    /**
     * Draws the main graphical user interface of the game.
     */
    public final void drawGUI(){
        frame = new JFrame("Ant Game");
        panel = new JPanel(new BorderLayout());
        redScore = new JLabel("0");
        blackScore = new JLabel("0");
        redNumber = new JLabel("0");
        blackNumber = new JLabel("0");
        gameButton = new JButton("Load");
        rounds = new JLabel("0");
        nextButton = new JButton("Next game");
        
        redScore.setFont(new Font("SanSerif", 1, 20));
        blackScore.setFont(new Font("SanSerif", 1, 20));
        redNumber.setFont(new Font("SanSerif", 1, 20));
        blackNumber.setFont(new Font("SanSerif", 1, 20));
        gameButton.setFont(new Font("SanSerif", 1, 20));
        rounds.setFont(new Font("SanSerif", 1, 20));
        nextButton.setFont(new Font("SanSerif", 1, 20));
        
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
        
        nextButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (nextGame == null && !games.isEmpty()){
                    nextButton.setEnabled(true);
                    nextGame = games.get(0);
                    ant1 = nextGame.getRedPath();
                    ant2 = nextGame.getBlackPath();
                    world1 = nextGame.getWorldPath();
                }
                if (nextGame != null){
                    tournament.tournament();
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
        JLabel tournLabel = new JLabel("Tournament");
        
        title.setFont(new Font("SanSerif", 1, 40));
        roundLabel.setFont(new Font("SanSerif", 1, 20));
        scoreLabel.setFont(new Font("SanSerif", 1, 20));
        numberLabel.setFont(new Font("SanSerif", 1, 20));
        redLabel1.setFont(new Font("SanSerif", 1, 20));
        blackLabel1.setFont(new Font("SanSerif", 1, 20));
        redLabel2.setFont(new Font("SanSerif", 1, 20));
        blackLabel2.setFont(new Font("SanSerif", 1, 20));
        winner.setFont(new Font("SanSerif", 1, 20));
        tournLabel.setFont(new Font("SanSerif", 1, 20));
        nextButton.setEnabled(false);
        
        // The button panel.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        titlePanel.add(title);
        
        JPanel loadButtonPanel = new JPanel();
        loadButtonPanel.setLayout(new BoxLayout(loadButtonPanel, BoxLayout.X_AXIS));
        
        buttonPanel.add(titlePanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loadButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        loadButtonPanel.add(gameButton);
        
        buttonPanel.add(loadButtonPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // The rounds labels.
        JPanel roundPanel = new JPanel();
        roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.X_AXIS));
        roundPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        roundPanel.add(roundLabel);
        roundPanel.add(Box.createHorizontalGlue());
        roundPanel.add(rounds);
        roundPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        
        // The score label.
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        
        JPanel scoreTextPanel = new JPanel();
        scoreTextPanel.setLayout(new BoxLayout(scoreTextPanel, BoxLayout.X_AXIS));
        scoreTextPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        scoreTextPanel.add(scoreLabel);
        scoreTextPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        scorePanel.add(scoreTextPanel);
        
        // The red score labels.
        JPanel scoreRedPanel = new JPanel();
        scoreRedPanel.setLayout(new BoxLayout(scoreRedPanel, BoxLayout.X_AXIS));
        scoreRedPanel.add(redLabel1);
        scoreRedPanel.add(Box.createRigidArea(new Dimension(75, 0)));
        scoreRedPanel.add(redScore);
        
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        
        // The black score labels.
        JPanel scoreBlackPanel = new JPanel();
        scoreBlackPanel.setLayout(new BoxLayout(scoreBlackPanel, BoxLayout.X_AXIS));
        scoreBlackPanel.add(blackLabel1);
        scoreBlackPanel.add(Box.createRigidArea(new Dimension(60, 0)));
        scoreBlackPanel.add(blackScore);
        
        scorePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        scorePanel.add(scoreRedPanel);
        scorePanel.add(scoreBlackPanel);
        
        // The number of ants label.
        JPanel numPanel = new JPanel();
        numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.Y_AXIS));
        JPanel numTextPanel = new JPanel();
        numTextPanel.setLayout(new BoxLayout(numTextPanel, BoxLayout.X_AXIS));
        numTextPanel.add(Box.createRigidArea(new Dimension(23, 0)));
        numTextPanel.add(numberLabel);
        numTextPanel.add(Box.createRigidArea(new Dimension(23, 0)));
        numPanel.add(numTextPanel);
        
        numPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        
        // The red number of ants labels.
        JPanel numRedPanel = new JPanel();
        numRedPanel.setLayout(new BoxLayout(numRedPanel, BoxLayout.X_AXIS));
        numRedPanel.add(redLabel2);
        numRedPanel.add(Box.createRigidArea(new Dimension(55, 0)));
        numRedPanel.add(redNumber);
        
        // The black number of ants labels.
        JPanel numBlackPanel = new JPanel();
        numBlackPanel.setLayout(new BoxLayout(numBlackPanel, BoxLayout.X_AXIS));
        numBlackPanel.add(blackLabel2);
        numBlackPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        numBlackPanel.add(blackNumber);
        
        numPanel.add(numRedPanel);
        numPanel.add(numBlackPanel);
        
        JPanel tournPanel = new JPanel();
        tournPanel.setLayout(new BoxLayout(tournPanel, BoxLayout.Y_AXIS));
        tournPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        
        JPanel tournTextPanel = new JPanel();
        tournTextPanel.setLayout(new BoxLayout(tournTextPanel, BoxLayout.X_AXIS));
        tournTextPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        tournTextPanel.add(tournLabel);
        tournTextPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        JPanel tournButtonPanel = new JPanel();
        tournButtonPanel.setLayout(new BoxLayout(tournButtonPanel, BoxLayout.X_AXIS));
        tournButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        tournButtonPanel.add(nextButton);
        tournButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        tournPanel.add(tournTextPanel);
        tournPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        tournPanel.add(tournButtonPanel);
        tournPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel winnerPanel = new JPanel();
        winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.X_AXIS));
        winnerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        winnerPanel.add(winner);
        winnerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        buttonPanel.add(roundPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(scorePanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(numPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(tournPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonPanel.add(winnerPanel);
        
        buttonPanel.setPreferredSize(new Dimension(300, 500)); 
        
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.add(grid, BorderLayout.WEST);
        
        frame.getContentPane().add(panel);
    }
    
    /**
     * Prepares all of the games for the tournament.
     */
    public synchronized void runTournament(){
        for (String world1 : worlds){
            for (int i = 0; i < antBrains.size() - 1; i++) {
                for(int j = i + 1; j < antBrains.size(); j++){
                    if (antBrains.get(i) != antBrains.get(j)){
                        try {
                            games.add(new Game(world1, antBrains.get(i), antBrains.get(j)));
                            games.add(new Game(world1, antBrains.get(j), antBrains.get(i)));
                        } catch (IllegalArgumentException | UnsupportedOperationException e){
                            JOptionPane.showMessageDialog(frame, "Invalid world or ant files.");
                        }                        
                    }
                }
            }
        }
    }
    
    /**
     * Draws the hexagonal grid.
     */
    public class Grid extends JPanel {
        
        /**
         * Creates the grid and sets the size of the grid to 900 by 605.
         */
        public Grid(){
            super.setPreferredSize(new Dimension(900, 605));
        }
        
        /**
         * Paints the grid.
         * 
         * @param g A Graphics object.
         */
        @Override
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            
            for(int y = 0; y < game.getWorld().getY(); y++){
                for(int x = 0; x < game.getWorld().getX(); x++){
                    drawCell(x, y, g2);
                }
            }
        }
        
        /**
         * Draw the hexagonal cell at the given coordinate.
         * 
         * @param x The x coordinate of the cell.
         * @param y The y coordinate of the cell.
         * @param g2 A Graphics2D object.
         */
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
                } else if (cell.getFood() > 0){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.yellow, x * 6 + 6, y * 4 + 3, Color.yellow)); 
                } else if (cell.isAnthill() == AntColor.Black){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.BLACK, x * 6 + 6, y * 4 + 3, Color.black)); 
                } else if (cell.isAnthill() == AntColor.Red){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.orange, x * 6 + 6, y * 4 + 3, Color.orange)); 
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
                } else if (cell.getFood() > 0){
                    g2.setPaint(new GradientPaint(x * 6, y * 4 + 3, Color.yellow, x * 6 + 6, y * 4 + 3, Color.yellow)); 
                } else if (cell.isAnthill() == AntColor.Black){
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.BLACK, x * 6 + 9, y * 4 + 3, Color.black)); 
                } else if (cell.isAnthill() == AntColor.Red){
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.orange, x * 6 + 9, y * 4 + 3, Color.orange)); 
                } else {
                    g2.setPaint(new GradientPaint(x * 6 + 3, y * 4 + 3, Color.green, x * 6 + 9, y * 4 + 3, Color.green)); 
                }               
            }
            g2.fill(path);
        }
    }
    
    /**
     * This class updates the game by taking the turns of all of the ants for
     * 300000 turns.
     */
    public class Update extends Thread {
        
        /**
         * Takes the turns for the ants for 300000 turns.
         * 
         * @return An empty string for use with the SwingWorker class.
         */
        public synchronized String update(){
            nextButton.setEnabled(false);
            winner.setText("");
            for (int j = 1; j < 300001; j++){grid.repaint();
                if (loadNew && running){
                    loadNew = false;
                    return "";
                } else {
                    loadNew = false;
                }
                running = true;
                rounds.setText(Integer.toString(j));
                try {
                    game.newGame();
                } catch (IllegalArgumentException | UnsupportedOperationException e){
                    JOptionPane.showMessageDialog(frame, "Invalid world or ant files.");
                    return "";
                }
                redNumber.setText(game.getWorld().getRedAnts() + "");
                blackNumber.setText(game.getWorld().getBlackAnts() + "");
                blackScore.setText(game.getBlackScore() + "");
                redScore.setText(game.getRedScore() + "");
                grid.repaint();
                frame.repaint();
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                }
            }
            running = false;
            if (game.getBlackScore() > game.getRedScore()){
                winner.setText("Black wins!");
            } else if (game.getRedScore() > game.getBlackScore()){
                winner.setText("Red wins!");
            } else {
                winner.setText("Draw!");
            }
            return "";
        }
    }
    
    /**
     * This class runs one round of the tournament and calculates the final winner.
     */
    public class Tournament extends Thread {
        
        /**
         * Runs one game in the tournament and calculates the final winner of the tournament.
         */
        public synchronized void tournament() {
            nextButton.setEnabled(false);
            if(!antBrains2.containsKey(ant1)){
                antBrains2.put(ant1, 0);
            }
            if(!antBrains2.containsKey(ant2)){
                antBrains2.put(ant2, 0);
            }

            if (checkFile(world1, true) && checkFile(ant1, false) && checkFile(ant2, false)){
                loadNew = true; 
                try {
                    game.resetGame(world1, ant1, ant2);
                } catch (IllegalArgumentException | UnsupportedOperationException e){
                    JOptionPane.showMessageDialog(frame, "Invalid world or ant files.");
                    return;
                }
                load.setVisible(false);
                grid.repaint();

                SwingWorker<String, Object> worker = new SwingWorker<String, Object>() {
                    @Override
                    protected String doInBackground() throws Exception { 
                        return update.update();
                    }

                    @Override
                    protected void done() {                        
                        grid.repaint();
                        if (games.isEmpty() || antBrains.isEmpty() || antBrains2.isEmpty()){
                            return;
                        }
                        if (game.getBlackScore() > game.getRedScore()){
                            antBrains2.replace(ant2, antBrains2.get(ant2) + 2);
                        } else if (game.getRedScore() > game.getBlackScore()){
                            antBrains2.replace(ant1, antBrains2.get(ant1) + 2);
                        } else {
                            antBrains2.replace(ant2, antBrains2.get(ant2) + 1);
                            antBrains2.replace(ant1, antBrains2.get(ant1) + 1);
                        }
                        games.remove(nextGame);
                        if (!games.isEmpty()){
                            nextButton.setEnabled(true);
                            nextGame = games.get(0);
                            ant1 = nextGame.getRedPath();
                            ant2 = nextGame.getBlackPath();
                            world1 = nextGame.getWorldPath();
                        } else {
                            nextGame = null;
                            nextButton.setEnabled(false);
                            
                            String winner = "";
                            int max = 0;
                            for (String ant : antBrains){
                                File fileName = new File(ant);
                                if (antBrains2.get(ant) == max){
                                    winner = winner.concat("\n" + fileName.getName());
                                } else if (antBrains2.get(ant) > max){
                                    winner = fileName.getName();
                                    max = antBrains2.get(ant);
                                }
                            }
                            antBrains.clear();
                            antBrains2.clear();
                            worlds.clear();
                            JOptionPane.showMessageDialog(frame, "Winner(s):\n" + winner);
                        }
                    }
                };      
                worker.execute();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid world or ant files.\nWorld files have '.world' extension.\nAnt files have '.ant' extension.");
            }
        }
    }
}


