import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class StartWindow extends JPanel{
        // controls the size of the board
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 12;
    public static final int COLUMNS = 18;
    //size of the buttons
    public static final int ButtonHeigth = 50;
    public static final int ButtonWidth = 100;
    ArrayList<String> data = new ArrayList<String>();


    public StartWindow(JFrame frame) {


        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        setBackground(new Color(0, 0, 0));
        //create the start and quite button
        ButtonCollum(frame);
        //Show the highscores
//        WriteHighScores();
    }

    //add button collum
    public void ButtonCollum(JFrame frame) {
        //add start button
        startButton(frame);

        //add quit button
        quitButton(frame);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        WriteHighScores(g);
    }

    public static void startButton(JFrame frame){

        JButton startButton = new JButton("Start game");
        startButton.setBounds((TILE_SIZE * COLUMNS)/2-(ButtonWidth/2),50,ButtonWidth,ButtonHeigth);
        //add button in the frame
        frame.add(startButton);

        //define function of startButton
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int GameState = 1;
                App.initWindow(GameState);
                }
        });
    }
    public static void quitButton(JFrame frame){
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds((TILE_SIZE * COLUMNS)/2-(ButtonWidth/2),150,ButtonWidth,ButtonHeigth);
        //add button in the frame
        frame.add(quitButton);
        //define function of startButton
        quitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
    }

    public void WriteHighScores(Graphics g){
        //read highscores
        HighScore highscore = new HighScore();
        data = highscore.ReadScore();
        //create area to write
        String text = "HighScores";
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(100, 200, 100));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
        // draw the score in the bottom center of the screen
        // https://stackoverflow.com/a/27740330/4655368
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        // the text will be contained within this rectangle.
        // here I've sized it to be the entire bottom row of board tiles
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        // determine the x coordinate for the text
        int x = rect.x + 30;//(rect.width - metrics.stringWidth(text)) / 2;
        // determine the y coordinate for the text
        // (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y-(TILE_SIZE * ROWS-100); //+((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // draw the string
        g2d.drawString(text, x, y);
        for (int i=0;i<data.size();i++){
            y = WriteScore(g, x, y, data.get(i));
        }
    }

    public Integer WriteScore(Graphics g, int x, int y, String score){
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(100, 200, 100));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
        // draw the score in the bottom center of the screen
        // https://stackoverflow.com/a/27740330/4655368
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        // the text will be contained within this rectangle.
        // here I've sized it to be the entire bottom row of board tiles
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        // determine the x coordinate for the text
        y = y +50;
        // draw the string
        g2d.drawString(score, x, y);
        return y;
    }

}
