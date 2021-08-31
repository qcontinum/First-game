import javax.swing.*;

class App {
    public static int GameState = 0;
    public static void initWindow(int GameState) {
        // create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Can't Stop, Won't Stop, GameStop");

        // when we close the window, stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (GameState == 0) {
            //go to start window
            StartWindow Startwindow = new StartWindow(window);
            //add the Jpanel to the window
            window.add(Startwindow);
        } else if (GameState == 1) {

        // create the jpanel to draw on.
        // this also initializes the game loop
        Board board = new Board();
        // add the jpanel to the window
        window.add(board);
        // pass keyboard inputs to the jpanel
        window.addKeyListener(board);
        }
        // don't allow the user to resize the window
        window.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }

    public static void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow(GameState);
            }
        });
    }
}