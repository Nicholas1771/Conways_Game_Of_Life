import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//This class is responsible for creating and running the gamestate, and creating the view
public class Game {

    //JFrame
    private GameFrame frame;
    //manager for the game state
    private GameStateManager gameStateManager;

    //constructor
    public Game () {
        frame = new GameFrame();
        gameStateManager = new GameStateManager();

        //initialize all the listeners for the buttons and mouse clicks
        initListeners();

        //time used to track time between last tick
        long time = 0;
        //delay between ticks
        int millisDelay = 100;
        //infinite while loop to continuously tick
        while (true) {
            //if more time than the specified delay has passed, tick and reset time
            if (System.currentTimeMillis() - time > millisDelay) {
                //reset time
                time = System.currentTimeMillis();
                //perform game tick
                gameStateManager.tick();
                //update the view with the updated gamestate
                frame.getGamePanel().setGameState(gameStateManager.getGameState());
            }
            //repaint the view
            frame.getGamePanel().repaint();
        }
    }

    //Initialize view button listeners and mouse click listener pointing to the correct methods
    private void initListeners () {
        frame.getPauseButton().addActionListener(e -> pause());
        frame.getStartButton().addActionListener(e -> start());
        frame.getClearButton().addActionListener(e -> clear());
        //used to toggle a clicked cell from dead to alive, or alive to dead
        frame.getGamePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //call clicked with the mouse x and y
                clicked(e.getX(), e.getY());
            }
        });
    }

    //pause button pressed, pause the gamestate
    private void pause() {
        gameStateManager.pause();
    }

    //start button pressed, start the gamestate
    private void start() {
        gameStateManager.start();
    }

    //clear button pressed, reset gamestate
    private void clear() {
        gameStateManager.newGameState();
    }

    //mouse was clicked, toggle the cell coresponding to the mouse x and y position
    private void clicked(int x, int y) {
        //width of the square* cell
        int w = frame.getGamePanel().getCellSize();
        //toggle the correct cell by dividing the mouse x and y by the cell width to get the correct cell position to toggle
        gameStateManager.toggleCell(x / w, y / w);
    }
}
