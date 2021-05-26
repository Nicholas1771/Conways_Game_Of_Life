import javax.swing.*;
import java.awt.*;

//This class is the JFrame
public class GameFrame extends JFrame {

    //Frame title, width, and height
    private static final String FRAME_TITLE = "Conway's Game of Life";
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    //gamepanel draws the cells
    private GamePanel gamePanel;

    //container holds the buttons
    private Container buttonPane;

    //Buttons
    private JButton startButton;
    private JButton pauseButton;
    private JButton clearButton;

    //constructor
    public GameFrame() {
        initFrame();
        initComponents();
        addComponents();
    }

    //initialize the JFrame values
    private void initFrame() {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(GameFrame.FRAME_TITLE);
        setResizable(false);
    }

    //initialize the buttons, button pane, and gamepanel
    private void initComponents() {
        buttonPane = new Container();
        //flow layout for the buttons
        buttonPane.setLayout(new FlowLayout());
        //start button
        startButton = new JButton("Start");
        buttonPane.add(startButton);
        //pause button
        pauseButton = new JButton("Pause");
        buttonPane.add(pauseButton);
        //clear button
        clearButton = new JButton("Clear");
        buttonPane.add(clearButton);

        gamePanel = new GamePanel();
    }

    //add components to the frame
    public void addComponents() {
        Container pane = getContentPane();
        pane.add(buttonPane, BorderLayout.PAGE_START);
        pane.add(gamePanel, BorderLayout.CENTER);
    }

    //getter methods for gamepanel and buttons
    public GamePanel getGamePanel () {
        return gamePanel;
    }

    public JButton getStartButton () {
        return startButton;
    }

    public JButton getPauseButton () {
        return pauseButton;
    }

    public JButton getClearButton () {
        return clearButton;
    }
}
