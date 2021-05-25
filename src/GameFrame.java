import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private static final String FRAME_TITLE = "Conway's Game of Life";

    private GamePanel gamePanel;

    private JButton startButton;
    private JButton pauseButton;
    private JButton clearButton;

    public GameFrame() {
        initFrame();
        initComponents();
    }

    private void initComponents() {
        Container pane = getContentPane();

        Container buttonPane = new Container();
        buttonPane.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        buttonPane.add(startButton);
        pauseButton = new JButton("Pause");
        buttonPane.add(pauseButton);
        clearButton = new JButton("Clear");
        buttonPane.add(clearButton);

        gamePanel = new GamePanel();

        pane.add(buttonPane, BorderLayout.PAGE_START);
        pane.add(gamePanel, BorderLayout.CENTER);
    }

    private void initFrame() {
        setSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(GameFrame.FRAME_TITLE);
        setResizable(false);
    }

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
