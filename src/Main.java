import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    private GameFrame frame;
    private GameState state;

    public Main() {
        frame = new GameFrame();
        state = new GameState();
        frame.getPauseButton().addActionListener(e -> pause());
        frame.getStartButton().addActionListener(e -> start());
        frame.getClearButton().addActionListener(e -> clear());
        frame.getGamePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                toggleCell(e.getX(), e.getY());
            }
        });
        long time = 0;
        int millisDelay = 1000;
        while (true) {
            if (System.currentTimeMillis() - time > millisDelay) {
                time = System.currentTimeMillis();
                state.tick();
                frame.getGamePanel().setGameState(state);
            }
            frame.getGamePanel().repaint();
        }
    }

    private void pause() {
        state.pause();
    }

    private void start() {
        state.start();
    }

    private void clear() {
        state.init();
    }

    private void toggleCell(int x, int y) {
        int w = frame.getGamePanel().getCellSize();
        int h = frame.getGamePanel().getCellSize();
        state.toggleCell(x / w, y / h);
    }

    public static void main(String[] args) {
        new Main();
    }
}
