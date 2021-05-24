import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private static final String FRAME_TITLE = "Conway's Game of Life";

    private GamePanel gamePanel;

    public GameFrame() {
        initFrame();
        gamePanel = new GamePanel(new Grid(200, 200));
        add(gamePanel);
    }

    private void initFrame() {
        setSize(new Dimension(1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(GameFrame.FRAME_TITLE);
        setResizable(false);
    }
}
